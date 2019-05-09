package br.com.andreikeda.cpfcnpjgenerator.document.presenter

import br.com.andreikeda.cpfcnpjgenerator.document.`object`.DocumentObject
import br.com.andreikeda.cpfcnpjgenerator.document.contract.DocumentContract

/**
 * @author cin_alima
 * @since 09/05/19 13:39
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
class DocumentPresenterImpl(var view: DocumentContract.View?, var document: String?) :
    DocumentContract.Presenter {
    override fun onCheckTextWatcherChanged(isChecked: Boolean) {
        when (isChecked) {
            true -> view?.addTextWatcher()
            false -> view?.removeTextWatcher()
        }
    }

    override fun onGenerateButtonClicked() {
        document?.let { doc ->
            when (doc) {
                DocumentObject.getCpfString() -> view?.showDocumentGenerated(DocumentObject.generateCpf())
                else -> view?.showDocumentGenerated(DocumentObject.generateCnpj())
            }
        }
    }

    override fun onValidateButtonClicked(text: String) {
        document?.let { doc ->
            when (doc) {
                DocumentObject.getCpfString() -> {
                    when (DocumentObject.validateCpf(text)) {
                        true -> view?.showFeedbackMessage("Documento válido")
                        false -> view?.showFeedbackMessage("Documento inválido")
                    }
                }
                else -> {
                    when (DocumentObject.validateCnpj(text)) {
                        true -> view?.showFeedbackMessage("Document válido")
                        false -> view?.showFeedbackMessage("Documento inválido")
                    }
                }
            }
        }
    }

    override fun unregister() {
        view = null
        document = null
    }
}