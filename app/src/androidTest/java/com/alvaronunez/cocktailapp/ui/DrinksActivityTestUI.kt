package com.alvaronunez.cocktailapp.ui

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.alvaronunez.cocktailapp.R
import com.alvaronunez.cocktailapp.ui.activity.DrinksActivity
import org.junit.Rule
import org.junit.Test

//TODO: create a fake data for tests (now is retrieving data from the api)

class DrinksActivityTestUI {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(DrinksActivity::class.java, false, false)

    @Test
    fun checkViewComponents() {
        mActivityTestRule.launchActivity(
            Intent().putExtra(
                DrinksActivity.INGREDIENT_NAME,
                "Light rum"
            )
        )
        Thread.sleep(1000)
        onView(withId(R.id.drinksRecycler))
            .check(matches(isDisplayed()))
        onView(withId(R.id.drinksRecycler))
            .check(matches(hasDescendant(withText("155 Belmont"))))
    }
}
