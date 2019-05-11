package br.com.andreikeda.cpfcnpjgenerator.document

import br.com.andreikeda.cpfcnpjgenerator.document.`object`.DocumentObject
import br.com.andreikeda.cpfcnpjgenerator.document.contract.DocumentContract
import br.com.andreikeda.cpfcnpjgenerator.document.presenter.DocumentPresenterImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`

/**
 * @author cin_alima
 * @since 10/05/19 21:18
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
@RunWith(JUnit4::class)
class DocumentUnitTest {

    private val INVALID_CNPJ = "290440231"
    private val VALID_CNPJ = "03152145000104"
    private val INVALID_CPF = "99238133"
    private val VALID_CPF = "04704228150"
    private val FEEDBACK_MESSAGE = "SUCCESS"

    @Mock
    private lateinit var mView: DocumentContract.View
    private lateinit var mPresenter: DocumentContract.Presenter

    @Mock
    private lateinit var document: DocumentObject

    @Before
    fun setup() {
        mView = Mockito.mock(DocumentContract.View::class.java)
        document = Mockito.mock(DocumentObject::class.java)
    }

    @Test
    fun `test valid cpf`() {
        `when`(document.generateCpf()).thenReturn(VALID_CPF)
        val cpf = document.generateCpf()

        Assert.assertEquals(VALID_CPF, cpf)
    }

    @Test
    fun `test invalid cpf`() {
        `when`(document.generateCpf()).thenReturn(INVALID_CPF)
        val cpf = document.generateCpf()

        Assert.assertEquals(INVALID_CPF, cpf)
    }

    @Test
    fun `test valid cnpj`() {
        `when`(document.generateCnpj()).thenReturn(VALID_CNPJ)
        val cnpj = document.generateCnpj()

        Assert.assertEquals(VALID_CNPJ, cnpj)
    }

    @Test
    fun `test invalid cnpj`() {
        `when`(document.generateCnpj()).thenReturn(INVALID_CNPJ)
        val cnpj = document.generateCnpj()

        Assert.assertEquals(INVALID_CNPJ, cnpj)
    }

    @Test
    fun `test generate cpf flow`() {
        mPresenter = DocumentPresenterImpl(mView, document.getCpfString())
        mPresenter.onGenerateButtonClicked()

        `when`(document.generateCpf()).thenReturn(VALID_CPF)
        val cpf = document.generateCpf()

        mView.showDocumentGenerated(cpf)
        Mockito.verify(mView).showDocumentGenerated(cpf)
        Mockito.verifyNoMoreInteractions(mView)
    }

    @Test
    fun `test generate cpf flow and validate`() {
        mPresenter = DocumentPresenterImpl(mView, document.getCpfString())
        mPresenter.onGenerateButtonClicked()

        `when`(document.generateCpf()).thenReturn(VALID_CPF)
        val cpf = document.generateCpf()

        mView.showDocumentGenerated(cpf)
        Mockito.verify(mView).showDocumentGenerated(cpf)

        mPresenter.onValidateButtonClicked(cpf)
        mView.showFeedbackMessage(FEEDBACK_MESSAGE)
        Mockito.verify(mView).showFeedbackMessage(FEEDBACK_MESSAGE)
        Mockito.verifyNoMoreInteractions(mView)
    }

    @Test
    fun `test input cpf and validate`() {
        mPresenter = DocumentPresenterImpl(mView, document.getCpfString())
        mPresenter.onValidateButtonClicked(VALID_CPF)

        mView.showFeedbackMessage(FEEDBACK_MESSAGE)
        Mockito.verify(mView).showFeedbackMessage(FEEDBACK_MESSAGE)
        Mockito.verifyNoMoreInteractions(mView)
    }

    @Test
    fun `test generate cnpj flow`() {
        mPresenter = DocumentPresenterImpl(mView, document.getCnpjString())
        mPresenter.onGenerateButtonClicked()

        `when`(document.generateCnpj()).thenReturn(VALID_CNPJ)
        val cnpj = document.generateCnpj()

        mView.showDocumentGenerated(cnpj)
        Mockito.verify(mView).showDocumentGenerated(cnpj)
        Mockito.verifyNoMoreInteractions(mView)
    }

    @Test
    fun `test generate cnpj flow and validate`() {
        mPresenter = DocumentPresenterImpl(mView, document.getCnpjString())
        mPresenter.onGenerateButtonClicked()

        `when`(document.generateCnpj()).thenReturn(VALID_CNPJ)
        val cnpj = document.generateCnpj()

        mView.showDocumentGenerated(cnpj)
        Mockito.verify(mView).showDocumentGenerated(cnpj)

        mPresenter.onValidateButtonClicked(cnpj)
        mView.showFeedbackMessage(FEEDBACK_MESSAGE)
        Mockito.verify(mView).showFeedbackMessage(FEEDBACK_MESSAGE)
        Mockito.verifyNoMoreInteractions(mView)
    }

    @Test
    fun `test input cnpj and validate`() {
        mPresenter = DocumentPresenterImpl(mView, document.getCnpjString())
        mPresenter.onValidateButtonClicked(VALID_CNPJ)

        mView.showFeedbackMessage(FEEDBACK_MESSAGE)
        Mockito.verify(mView).showFeedbackMessage(FEEDBACK_MESSAGE)
        Mockito.verifyNoMoreInteractions(mView)
    }
}