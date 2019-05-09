package br.com.andreikeda.cpfcnpjgenerator.main.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.andreikeda.cpfcnpjgenerator.R
import br.com.andreikeda.cpfcnpjgenerator.buildCnpjIntent
import br.com.andreikeda.cpfcnpjgenerator.buildCpfIntent
import br.com.andreikeda.cpfcnpjgenerator.main.contract.MainContract
import br.com.andreikeda.cpfcnpjgenerator.main.presenter.MainPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private var mPresenter: MainContract.Presenter? = null

    override fun goToCpfScreen() {
        startActivity(buildCpfIntent())
    }

    override fun goToCnpjScreen() {
        startActivity(buildCnpjIntent())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    override fun onStart() {
        super.onStart()

        mPresenter = MainPresenterImpl(this)
        mPresenter?.onActivityStarted()
    }

    override fun onStop() {
        mPresenter?.unregister()
        mPresenter = null

        super.onStop()
    }

    override fun setCnpjText(text: String) {
        btn_cnpj.text = getString(R.string.btn_generate_formatted, text)
    }

    override fun setCpfText(text: String) {
        btn_cpf.text = getString(R.string.btn_generate_formatted, text)
    }

    private fun initUI() {
        btn_cnpj.setOnClickListener { mPresenter?.onCnpjClicked() }
        btn_cpf.setOnClickListener { mPresenter?.onCpfClicked() }
    }
}
