package br.com.andreikeda.cpfcnpjgenerator.main.presenter

import br.com.andreikeda.cpfcnpjgenerator.document.`object`.DocumentObject
import br.com.andreikeda.cpfcnpjgenerator.main.contract.MainContract

/**
 * @author cin_alima
 * @since 09/05/19 13:04
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
class MainPresenterImpl(var view: MainContract.View?) : MainContract.Presenter {
    override fun onActivityStarted() {
        view?.setCnpjText(DocumentObject.getCnpjString())
        view?.setCpfText(DocumentObject.getCpfString())
    }

    override fun onCpfClicked() {
        view?.goToCpfScreen()
    }

    override fun onCnpjClicked() {
        view?.goToCnpjScreen()
    }

    override fun unregister() {
        view = null
    }
}