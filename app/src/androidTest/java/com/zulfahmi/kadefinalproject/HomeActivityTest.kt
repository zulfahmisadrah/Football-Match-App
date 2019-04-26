package com.zulfahmi.kadefinalproject

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.zulfahmi.kadefinalproject.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeActivityTest{
    @Rule
    @JvmField var activityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testAppBehaviour() {
        //Memastikan prev match list tampil, scroll ke position 10, lalu klik
        Thread.sleep(3000)
        onView(withId(rvMatchList)).check(matches(isDisplayed()))
        onView(withId(rvMatchList)).perform((RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10)))
        onView(withId(rvMatchList)).perform((RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click())))

        //Halaman detail tampil, Memastikan tombol favorite tampil, klik tombol favorite, memastikan toast muncul
        Thread.sleep(2000)
        onView(withId(add_to_favorites)).check(matches(isDisplayed()))
        onView(withId(add_to_favorites)).perform(click())
        onView(withText("Added to favorite")).check(matches(isDisplayed()))

        //kembali ke home
        pressBack()

        //Memastikan bottom navigation tampil, lalu klik menu next match
        onView(withId(bottom_layout)).check(matches(isDisplayed()))
        onView(withId(teams)).perform(click())

        //Memastikan next match list tampil, sccroll ke position 5, lalu klik
        Thread.sleep(3000)
        onView(withId(rvMatchList)).check(matches(isDisplayed()))
        onView(withId(rvMatchList)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(rvMatchList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        //Halaman detail tampil, Memastikan tombol favorite tampil, klik tombol favorite, memastikan toast muncul
        Thread.sleep(2000)
        onView(withId(add_to_favorites)).check(matches(isDisplayed()))
        onView(withId(add_to_favorites)).perform(click())
        onView(withText("Added to favorite")).check(matches(isDisplayed()))

        //kembali ke home
        pressBack()

        //Memastikan bottom navigation tampil, lalu klik menu favorite
        onView(withId(bottom_layout)).check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())

        //Memastikan favorite match list tampil
        //klik position 0, memastikan tombol favorite tampil, klik tombol favorite, memastikan toast muncul
        //lalu diulang lagi untuk position 1
        for(i in 0..1) {
            Thread.sleep(2000)
            onView(withId(rvMatchList_fav)).check(matches(isDisplayed()))
            onView(withId(rvMatchList_fav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            Thread.sleep(2000)
            onView(withId(add_to_favorites)).check(matches(isDisplayed()))
            onView(withId(add_to_favorites)).perform(click())
            onView(withText("Removed from favorite")).check(matches(isDisplayed()))
            //kembali ke home
            pressBack()
        }
        //END

    }
}