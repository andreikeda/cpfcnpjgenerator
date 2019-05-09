package br.com.andreikeda.cpfcnpjgenerator.document.watcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * @author cin_alima
 * @since 09/05/19 17:02
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
class DocumentMask {
    companion object {
        private fun replaceChars(cpfFull: String): String {
            return cpfFull.replace(".", "").replace("-", "")
                .replace("(", "").replace(")", "")
                .replace("/", "").replace(" ", "")
                .replace("*", "")
        }

        fun mask(mask: String, editText: EditText): TextWatcher {

            val textWatcher: TextWatcher = object : TextWatcher {
                var isUpdating: Boolean = false
                var oldString: String = ""
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val str =
                        replaceChars(
                            s.toString()
                        )
                    var docWithMask = ""

                    if (count == 0)//is deleting
                        isUpdating = true

                    if (isUpdating) {
                        oldString = str
                        isUpdating = false
                        return
                    }

                    var i = 0
                    for (m: Char in mask.toCharArray()) {
                        if (m != '#' && str.length > oldString.length) {
                            docWithMask += m
                            continue
                        }
                        try {
                            docWithMask += str.get(i)
                        } catch (e: Exception) {
                            break
                        }
                        i++
                    }

                    isUpdating = true
                    editText.setText(docWithMask)
                    editText.setSelection(docWithMask.length)
                }

                override fun afterTextChanged(editable: Editable) {
                }
            }

            return textWatcher
        }
    }
}