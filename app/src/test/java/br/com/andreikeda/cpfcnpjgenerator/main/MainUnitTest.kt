package br.com.andreikeda.cpfcnpjgenerator.main

import br.com.andreikeda.cpfcnpjgenerator.main.contract.MainContract
import br.com.andreikeda.cpfcnpjgenerator.main.presenter.MainPresenterImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import org.mockito.Mock
import org.mockito.Mockito

/**
 * @author cin_alima
 * @since 10/05/19 21:04
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
@RunWith(JUnit4::class)
class MainUnitTest {

    @Mock
    private lateinit var mView: MainContract.View
    private lateinit var mPresenter: MainContract.Presenter

    @Before
    fun setup() {
        mView = Mockito.mock(MainContract.View::class.java)
        mPresenter = MainPresenterImpl(mView)
    }

    @Test
    fun `click cpf and go to cpf screen`() {
        mPresenter.onCpfClicked()

        Mockito.verify(mView).goToCpfScreen()
        Mockito.verifyNoMoreInteractions(mView)
    }

    @Test
    fun `click cnpj and go to cnpj screen`() {
        mPresenter.onCnpjClicked()

        Mockito.verify(mView).goToCnpjScreen()
        Mockito.verifyNoMoreInteractions(mView)
    }
}