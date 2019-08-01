package br.com.gft.marvel.feature.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.gft.marvel.R
import br.com.gft.marvel.feature.home.presentation.HomeActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    val main: IntentsTestRule<HomeActivity> = IntentsTestRule(HomeActivity::class.java, false, false)


    @get:Rule
    public val mActivityRule: ActivityTestRule<HomeActivity> = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun checkSearchTest() {
        //escrevo no campo de busca
        onView(withId(R.id.etFilter))
            .perform(typeText("am"), closeSoftKeyboard())
        //escrevo outro termo no campo de busca
        onView(withId(R.id.etFilter))
            .perform(typeText("e"), closeSoftKeyboard())
    }

    @Test
    fun recyclerViewItemClickTest() {
        //clicar o item 0
        onView(withId(R.id.rvCharacter))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(mActivityRule.activity.window.decorView)
                )
            )
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun recyclerViewScrollTest() {
        //instanciar o recyclerview
        val list = mActivityRule.activity.findViewById<RecyclerView>(R.id.rvCharacter)
        var size = list.adapter?.itemCount ?: 0

        //clicar o item 0
        onView(withId(R.id.rvCharacter))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(mActivityRule.activity.window.decorView)
                )
            )
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(size - 1))
    }
}