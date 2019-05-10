package br.com.andreikeda.cpfcnpjgenerator.main

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.andreikeda.cpfcnpjgenerator.main.view.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author cin_alima
 * @since 09/05/19 18:33
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
@RunWith(AndroidJUnit4::class)
class MainInstrumentedTest {

    private val CPF = "CPF"
    private val CNPJ = "CNPJ"

    private val INVALID_CNPJ = "290440231"
    private val VALID_CNPJ = "03152145000104"
    private val INVALID_CPF = "99238133"
    private val VALID_CPF = "04704228150"

    @get:Rule
    var activityRule = ActivityTestRule(
        MainActivity::class.java,
        false, false
    )

    lateinit var robot: MainInstrumentedRobot

    @Before
    fun setUp() {
        robot = MainInstrumentedRobot(activityRule)
    }

    @After
    fun finish() {
        activityRule.finishActivity()
    }

    @Test
    fun testGoToCpf() {
        robot
            .start()
            .tapCpf()
            .checkTextIsDisplayed(CPF)
    }

    @Test
    fun testGoToCpf_Generate_Check() {
        robot
            .start()
            .tapCpf()
            .checkTextIsDisplayed(CPF)
            .tapGenerate()
            .tapValidate()
            .checkFeedbackTxtIsDisplayed()
    }

    @Test
    fun testGoToCpf_Input_Invalid() {
        robot
            .start()
            .tapCpf()
            .checkTextIsDisplayed(CPF)
            .inputText(INVALID_CPF)
            .tapValidate()
            .checkFeedbackTxtIsDisplayed()
    }

    @Test
    fun testGoToCpf_Input_Valid() {
        robot
            .start()
            .tapCpf()
            .checkTextIsDisplayed(CPF)
            .inputText(VALID_CPF)
            .tapValidate()
            .checkFeedbackTxtIsDisplayed()
    }

    @Test
    fun testGoToCnpj() {
        robot
            .start()
            .tapCnpj()
            .checkTextIsDisplayed(CNPJ)
    }

    @Test
    fun testGoToCnpj_Generate_Check() {
        robot
            .start()
            .tapCnpj()
            .checkTextIsDisplayed(CNPJ)
            .tapGenerate()
            .tapValidate()
            .checkFeedbackTxtIsDisplayed()
    }

    @Test
    fun testGoToCnpj_Input_Invalid() {
        robot
            .start()
            .tapCnpj()
            .checkTextIsDisplayed(CNPJ)
            .inputText(INVALID_CNPJ)
            .tapValidate()
            .checkFeedbackTxtIsDisplayed()
    }

    @Test
    fun testGoToCnpj_Input_Valid() {
        robot
            .start()
            .tapCnpj()
            .checkTextIsDisplayed(CNPJ)
            .inputText(VALID_CNPJ)
            .tapValidate()
            .checkFeedbackTxtIsDisplayed()
    }
}