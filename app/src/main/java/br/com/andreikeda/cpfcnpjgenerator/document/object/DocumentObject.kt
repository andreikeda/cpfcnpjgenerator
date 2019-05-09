package br.com.andreikeda.cpfcnpjgenerator.document.`object`

import java.util.InputMismatchException

/**
 * @author cin_alima
 * @since 09/05/19 16:15
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
object DocumentObject {

    fun generateCpf(): String {
        val n = 9
        val n1 = randomize(n)
        val n2 = randomize(n)
        val n3 = randomize(n)
        val n4 = randomize(n)
        val n5 = randomize(n)
        val n6 = randomize(n)
        val n7 = randomize(n)
        val n8 = randomize(n)
        val n9 = randomize(n)
        var d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10

        d1 = 11 - mod(d1, 11)

        if (d1 >= 10)
            d1 = 0

        var d2 =
            d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11

        d2 = 11 - mod(d2, 11)

        if (d2 >= 10)
            d2 = 0

        return "$n1$n2$n3$n4$n5$n6$n7$n8$n9$d1$d2"
    }

    fun getCpfString(): String {
        return "CPF"
    }

    fun getCnpjString(): String {
        return "CNPJ"
    }

    fun generateCnpj(): String {
        val n = 9
        val n1 = randomize(n)
        val n2 = randomize(n)
        val n3 = randomize(n)
        val n4 = randomize(n)
        val n5 = randomize(n)
        val n6 = randomize(n)
        val n7 = randomize(n)
        val n8 = randomize(n)
        val n9 = 0
        val n10 = 0
        val n11 = 0
        val n12 = 1
        var d1 =
            n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5

        d1 = 11 - mod(d1, 11)

        if (d1 >= 10)
            d1 = 0

        var d2 =
            d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6

        d2 = 11 - mod(d2, 11)

        if (d2 >= 10)
            d2 = 0

        return "$n1$n2$n3$n4$n5$n6$n7$n8$n9$n10$n11$n12$d1$d2"
    }

    fun validateCnpj(doc: String): Boolean {
        val cnpj = removeSpecialChars(doc)

        if (cnpj == "00000000000000" || cnpj == "11111111111111" || cnpj == "22222222222222" || cnpj == "33333333333333" ||
            cnpj == "44444444444444" || cnpj == "55555555555555" || cnpj == "66666666666666" || cnpj == "77777777777777" ||
            cnpj == "88888888888888" || cnpj == "99999999999999" || cnpj.length != 14
        ) {
            return false
        }

        val dig13: Char
        val dig14: Char
        var sm: Int
        var i: Int
        var r: Int
        var num: Int
        var peso: Int

        try {
            sm = 0
            peso = 2
            i = 11
            while (i >= 0) {
                num = cnpj.codePointAt(i) - 48
                sm += num * peso
                peso += 1
                if (peso == 10)
                    peso = 2
                i--
            }

            r = sm % 11
            if (r == 0 || r == 1)
                dig13 = '0'
            else
                dig13 = (11 - r + 48).toChar()

            sm = 0
            peso = 2
            i = 12
            while (i >= 0) {
                num = cnpj.codePointAt(i) - 48
                sm += num * peso
                peso += 1
                if (peso == 10)
                    peso = 2
                i--
            }

            r = sm % 11
            if (r == 0 || r == 1)
                dig14 = '0'
            else
                dig14 = (11 - r + 48).toChar()

            return dig13 == cnpj[12] && dig14 == cnpj[13]
        } catch (erro: InputMismatchException) {
            return false
        }
    }

    fun validateCpf(cpf: String): Boolean {
        val cpfClean = cpf.replace(".", "").replace("-", "")

        if (cpfClean.length != 11)
            return false

        try {
            val number = cpfClean.toLong()
        } catch (e: Exception) {
            return false
        }

        val dvCurrent10 = cpfClean.substring(9, 10).toInt()
        val dvCurrent11 = cpfClean.substring(10, 11).toInt()

        val cpfNineFirst = IntArray(9)
        var i = 9

        while (i > 0) {
            cpfNineFirst[i - 1] = cpfClean.substring(i - 1, i).toInt()
            i--
        }

        val sumProductNine = IntArray(9)
        var weight = 10
        var position = 0

        while (weight >= 2) {
            sumProductNine[position] = weight * cpfNineFirst[position]
            weight--
            position++
        }

        var dvForTenthDigit = sumProductNine.sum() % 11
        dvForTenthDigit = 11 - dvForTenthDigit
        if (dvForTenthDigit > 9)
            dvForTenthDigit = 0
        if (dvForTenthDigit != dvCurrent10)
            return false

        val cpfTenFirst = cpfNineFirst.copyOf(10)
        cpfTenFirst[9] = dvCurrent10

        val sumProductTen = IntArray(10)
        var w = 11
        var p = 0

        while (w >= 2) {
            sumProductTen[p] = w * cpfTenFirst[p]
            w--
            p++
        }

        var dvForeleventhDigit = sumProductTen.sum() % 11
        dvForeleventhDigit = 11 - dvForeleventhDigit
        if (dvForeleventhDigit > 9)
            dvForeleventhDigit = 0
        if (dvForeleventhDigit != dvCurrent11)
            return false

        return true
    }

    private fun randomize(n: Int): Int {
        return (Math.random() * n).toInt()
    }

    private fun mod(dividend: Int, div: Int): Int {
        return Math.round(dividend - Math.floor((dividend / div).toDouble()) * div)
            .toInt()
    }

    private fun removeSpecialChars(doc: String): String {
        var doc = doc
        if (doc.contains(".")) {
            doc = doc.replace(".", "")
        }
        if (doc.contains("-")) {
            doc = doc.replace("-", "")
        }
        if (doc.contains("/")) {
            doc = doc.replace("/", "")
        }
        return doc
    }
}