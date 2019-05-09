package br.com.andreikeda.cpfcnpjgenerator.document.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextWatcher
import android.view.View
import br.com.andreikeda.cpfcnpjgenerator.EXTRA_DOCUMENT
import br.com.andreikeda.cpfcnpjgenerator.MASK_CNPJ
import br.com.andreikeda.cpfcnpjgenerator.MASK_CPF
import br.com.andreikeda.cpfcnpjgenerator.R
import br.com.andreikeda.cpfcnpjgenerator.document.`object`.DocumentObject
import br.com.andreikeda.cpfcnpjgenerator.document.contract.DocumentContract
import br.com.andreikeda.cpfcnpjgenerator.document.presenter.DocumentPresenterImpl
import br.com.andreikeda.cpfcnpjgenerator.document.watcher.DocumentMask
import kotlinx.android.synthetic.main.activity_document.*

/**
 * @author cin_alima
 * @since 09/05/19 13:40
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
class DocumentActivity : AppCompatActivity(), DocumentContract.View {

    private var mPresenter: DocumentContract.Presenter? = null
    private var mWatcher: TextWatcher? = null

    private var document: String? = null

    override fun addTextWatcher() {
        val currentText = et_input.text.toString()
        et_input.apply {
            setText("")
            addTextChangedListener(mWatcher)
            setText(currentText)
        }
    }

    override fun removeTextWatcher() {
        val currentText = et_input.text.toString().replace(".", "").replace("-", "")
        et_input.apply {
            setText("")
            removeTextChangedListener(mWatcher)
            setText(currentText)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)

        document = intent.getStringExtra(EXTRA_DOCUMENT)
        document?.let { doc ->
            title = doc
        }

        mWatcher = when (document) {
            DocumentObject.getCpfString() -> DocumentMask.mask(MASK_CPF, et_input)
            else -> {
                DocumentMask.mask(MASK_CNPJ, et_input)
            }
        }


        initUI()
    }

    override fun onStart() {
        super.onStart()

        mPresenter = DocumentPresenterImpl(this, document)
        mPresenter?.onCheckTextWatcherChanged(cb_mask.isChecked)
    }

    override fun onStop() {
        mPresenter?.unregister()
        mPresenter = null

        super.onStop()
    }

    override fun hideFeedbackMessage() {
        tv_feedback.visibility = View.GONE
    }

    override fun showDocumentGenerated(text: String) {
        et_input.setText(text)
    }

    override fun showFeedbackMessage(text: String) {
        tv_feedback.visibility = View.VISIBLE
        tv_feedback.text = text
    }

    private fun initUI() {
        btn_generate.setOnClickListener { mPresenter?.onGenerateButtonClicked() }
        btn_validate.setOnClickListener { mPresenter?.onValidateButtonClicked(getInputText()) }
        cb_mask.setOnCheckedChangeListener { _, isChecked ->
            mPresenter?.onCheckTextWatcherChanged(isChecked)
        }
    }

    private fun getInputText(): String {
        return et_input.text.toString()
    }
}