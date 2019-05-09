package br.com.andreikeda.cpfcnpjgenerator.cpf.contract

/**
 * @author cin_alima
 * @since 09/05/19 13:38
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
public interface CpfContract {
    interface Presenter {
        fun onGenerateButtonClicked()

        fun onValidateButtonClicked(text: String)

        fun unregister()
    }

    interface View {
        fun hideFeedbackMessage()

        fun showDocumentGenerated(text: String)

        fun showFeedbackMessage(text: String)
    }
}