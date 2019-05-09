package br.com.andreikeda.cpfcnpjgenerator.cpf.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.andreikeda.cpfcnpjgenerator.R
import br.com.andreikeda.cpfcnpjgenerator.cpf.contract.CpfContract
import br.com.andreikeda.cpfcnpjgenerator.cpf.presenter.CpfPresenterImpl
import kotlinx.android.synthetic.main.activity_cpf.*

/**
 * @author cin_alima
 * @since 09/05/19 13:40
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
class CpfActivity : AppCompatActivity(), CpfContract.View {

    private var mPresenter: CpfContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cpf)

        initUI()
    }

    override fun onStart() {
        super.onStart()

        mPresenter = CpfPresenterImpl(this)
    }

    override fun onStop() {
        mPresenter?.unregister()
        mPresenter = null

        super.onStop()
    }

    override fun hideFeedbackMessage() {
        tv_cpf_feedback.visibility = View.GONE
    }

    override fun showDocumentGenerated(text: String) {
        et_cpf_input.setText(text)
    }

    override fun showFeedbackMessage(text: String) {
        tv_cpf_feedback.visibility = View.VISIBLE
        tv_cpf_feedback.text = text
    }

    private fun initUI() {
        btn_generate.setOnClickListener { mPresenter?.onGenerateButtonClicked() }
        btn_validate.setOnClickListener { mPresenter?.onValidateButtonClicked(getInputText()) }
    }

    private fun getInputText(): String {
        return et_cpf_input.text.toString()
    }
}