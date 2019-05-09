package br.com.andreikeda.cpfcnpjgenerator.main.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.andreikeda.cpfcnpjgenerator.R
import br.com.andreikeda.cpfcnpjgenerator.main.contract.MainContract
import br.com.andreikeda.cpfcnpjgenerator.main.presenter.MainPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private var mPresenter: MainContract.Presenter? = null

    override fun goToCpfScreen() {
    }

    override fun goToCnpjScreen() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    override fun onStart() {
        super.onStart()

        mPresenter = MainPresenterImpl(this)
    }

    override fun onStop() {
        mPresenter?.unregister()

        super.onStop()
    }

    private fun initUI() {
        btn_cnpj.setOnClickListener { mPresenter?.onCnpjClicked() }
        btn_cpf.setOnClickListener { mPresenter?.onCpfClicked() }
    }
}
