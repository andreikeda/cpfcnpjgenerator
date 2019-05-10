package br.com.andreikeda.cpfcnpjgenerator.main

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import br.com.andreikeda.cpfcnpjgenerator.R
import br.com.andreikeda.cpfcnpjgenerator.main.view.MainActivity

/**
 * @author cin_alima
 * @since 09/05/19 18:35
 * @version $Revision: $<br/>
 *          $Id: $
 *
 */
class MainInstrumentedRobot(private val activityTestRule: ActivityTestRule<MainActivity>) {

    fun start() = apply {
        activityTestRule.launchActivity(Intent())
    }

    fun checkFeedbackTxtIsDisplayed() = apply {
        onView(withId(R.id.tv_feedback)).check(ViewAssertions.matches(isDisplayed()))
    }

    fun checkTextIsDisplayed(text: String) = apply {
        onView(withText(text)).check(ViewAssertions.matches(isDisplayed()))
    }

    fun checkGenerateBtnIsDisplayed() = apply {
        onView(withId(R.id.btn_generate)).check(ViewAssertions.matches(isDisplayed()))
    }

    fun checkValidateBtnIsDisplayed() = apply {
        onView(withId(R.id.btn_validate)).check(ViewAssertions.matches(isDisplayed()))
    }

    fun inputText(text: String) = apply {
        onView(withId(R.id.et_input)).perform(typeText(text))
    }

    fun tapBack() = apply {
        Espresso.pressBack()
    }

    fun tapCheckBox() = apply {
        onView(withId(R.id.cb_mask)).perform(click())
    }

    fun tapCnpj() = apply {
        onView(withId(R.id.btn_cnpj)).perform(click())
    }

    fun tapCpf() = apply {
        onView(withId(R.id.btn_cpf)).perform(click())
    }

    fun tapGenerate() = apply {
        onView(withId(R.id.btn_generate)).perform(click())
    }

    fun tapValidate() = apply {
        onView(withId(R.id.btn_validate)).perform(click())
    }
}
