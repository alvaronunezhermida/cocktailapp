package com.alvaronunez.cocktailapp.ui

import android.content.res.Resources
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.alvaronunez.cocktailapp.R
import com.alvaronunez.cocktailapp.ui.activity.IngredientsActivity
import org.junit.Rule
import org.junit.Test

//TODO: create a fake data for tests (now is retrieving data from the api)

class IngredientsActivityTestUI {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(IngredientsActivity::class.java, false, false)

    @Test
    fun checkViewComponents() {
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.ingredientsToolbar))
            .check(matches(isDisplayed()))
        onView(withId(R.id.ingredientsRecycler))
            .check(matches(isDisplayed()))
        onView(withId(R.id.search))
            .check(matches(isDisplayed()))
        onView(withId(R.id.ingredientsRecycler))
            .check(matches(hasDescendant(withText("Light rum"))))
    }

    @Test
    fun searchViewWorks() {
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.search))
            .perform(click())
        Thread.sleep(800)
        onView(
            withId(
                Resources.getSystem().getIdentifier("search_src_text", "id", "android")
            )
        ).perform(typeText("tea"))
        Thread.sleep(800)
        onView(withId(R.id.ingredientsRecycler))
            .check(matches(hasDescendant(withText("Tea"))))
    }

    @Test
    fun clickIngredientGoesToDrinks() {
        mActivityTestRule.launchActivity(null)
        onView(withText("Light rum"))
            .perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.drinksRecycler))
            .check(matches(isDisplayed()))
    }
}
