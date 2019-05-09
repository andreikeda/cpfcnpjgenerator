package br.com.andreikeda.cpfcnpjgenerator.document.contract

/**
 * @author cin_alima
 * @since 09/05/19 13:38
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
public interface DocumentContract {
    interface Presenter {
        fun onCheckTextWatcherChanged(isChecked: Boolean)

        fun onGenerateButtonClicked()

        fun onValidateButtonClicked(text: String)

        fun unregister()
    }

    interface View {
        fun addTextWatcher()

        fun hideFeedbackMessage()

        fun removeTextWatcher()

        fun showDocumentGenerated(text: String)

        fun showFeedbackMessage(text: String)
    }
}