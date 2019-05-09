package br.com.andreikeda.cpfcnpjgenerator.main.contract

/**
 * @author cin_alima
 * @since 09/05/19 13:03
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
interface MainContract {
    interface Presenter {
        fun onActivityStarted()

        fun onCpfClicked()

        fun onCnpjClicked()

        fun unregister()
    }

    interface View {
        fun setCnpjText(text: String)

        fun setCpfText(text: String)

        fun goToCpfScreen()

        fun goToCnpjScreen()
    }
}