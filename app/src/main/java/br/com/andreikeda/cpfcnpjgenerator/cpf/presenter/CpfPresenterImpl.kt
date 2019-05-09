package br.com.andreikeda.cpfcnpjgenerator.cpf.presenter

import br.com.andreikeda.cpfcnpjgenerator.cpf.contract.CpfContract

/**
 * @author cin_alima
 * @since 09/05/19 13:39
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
class CpfPresenterImpl(var view: CpfContract.View?) : CpfContract.Presenter {
    override fun onGenerateButtonClicked() {
    }

    override fun onValidateButtonClicked(text: String) {
    }

    override fun unregister() {
        view = null
    }
}