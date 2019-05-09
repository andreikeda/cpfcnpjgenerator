package br.com.andreikeda.cpfcnpjgenerator.cnpj.presenter

import br.com.andreikeda.cpfcnpjgenerator.cnpj.contract.CnpjContract

/**
 * @author cin_alima
 * @since 09/05/19 16:06
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
class CnpjPresenterImpl(var view: CnpjContract.View?) : CnpjContract.Presenter {
    override fun unregister() {
        view = null
    }
}