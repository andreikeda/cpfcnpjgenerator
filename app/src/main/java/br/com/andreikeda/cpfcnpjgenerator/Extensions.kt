package br.com.andreikeda.cpfcnpjgenerator

import android.content.Context
import android.content.Intent
import br.com.andreikeda.cpfcnpjgenerator.document.`object`.DocumentObject
import br.com.andreikeda.cpfcnpjgenerator.document.view.DocumentActivity

/**
 * @author cin_alima
 * @since 09/05/19 17:06
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */

const val EXTRA_DOCUMENT = "DOCUMENT"

const val MASK_CPF = "###.###.###-##"
const val MASK_CNPJ = "##.###.###/####-##"

fun Context.buildCpfIntent(): Intent = Intent(this, DocumentActivity::class.java).apply {
    putExtra(EXTRA_DOCUMENT, DocumentObject.getCpfString())
}

fun Context.buildCnpjIntent(): Intent = Intent(this, DocumentActivity::class.java).apply {
    putExtra(EXTRA_DOCUMENT, DocumentObject.getCnpjString())
}