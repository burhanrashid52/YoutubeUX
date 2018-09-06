package com.burhanrashid52.player.dashboard

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.registerIdlingResources
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.burhanrashid52.player.R
import com.burhanrashid52.player.home.HomeAdapter
import com.burhanrashid52.player.home.HomeFragment
import com.burhanrashid52.player.library.LibraryFragment
import com.burhanrashid52.player.player.VideoDetailsFragment
import com.burhanrashid52.player.player.VideoPlayerFragment
import com.burhanrashid52.player.subscriptions.SubscriptionFragment
import com.burhanrashid52.player.trending.TrendingFragment
import com.burhanrashid52.player.useractivity.UserActivityFragment
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardActivityTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(DashboardActivity::class.java)
    private lateinit var mDashboardActivity: DashboardActivity

    @Before
    fun setUp() {
        mDashboardActivity = rule.activity
    }

    @Test
    fun testDashboardLaunchedAndHomeFragmentAttachedToDashboard() {
        assertNotNull(mDashboardActivity)
        assertNotNull(mDashboardActivity.findViewById(R.id.rootContainer))

        val homeFragment = mDashboardActivity.supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment
        assertNotNull(homeFragment)
    }

    @Test
    fun testBottomNavigationBarClick() {
        testBottomClickIds(R.id.navigation_trending)
        testBottomClickIds(R.id.navigation_library)
        testBottomClickIds(R.id.navigation_notifications)
        testBottomClickIds(R.id.navigation_subscription)
        testBottomClickIds(R.id.navigation_home)

    }

    @Test
    fun testMovieClickedAndMatchItToItsDetails(){
        val homeFragment=mDashboardActivity.supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment
        IdlingRegistry.getInstance().register(homeFragment.countingIdleResources)
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<HomeAdapter.HomeViewHolder>(0, click()))
        assertNotNull(mDashboardActivity.supportFragmentManager.findFragmentByTag(VideoPlayerFragment.TAG))
        assertNotNull(mDashboardActivity.supportFragmentManager.findFragmentByTag(VideoDetailsFragment.TAG))
    }

    @After
    fun tearDown() {
    }

    private fun testBottomClickIds(menuId: Int) {
        onView(withId(menuId)).perform(click())

        when (menuId) {
            R.id.navigation_home -> {
                val homeFragment = mDashboardActivity.supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment
                assertNotNull(homeFragment)
            }
            R.id.navigation_trending -> {
                val homeFragment = mDashboardActivity.supportFragmentManager.findFragmentByTag(TrendingFragment.TAG) as TrendingFragment
                assertNotNull(homeFragment)

            }
            R.id.navigation_library -> {

                val homeFragment = mDashboardActivity.supportFragmentManager.findFragmentByTag(LibraryFragment.TAG) as LibraryFragment
                assertNotNull(homeFragment)

            }
            R.id.navigation_notifications -> {

                val homeFragment = mDashboardActivity.supportFragmentManager.findFragmentByTag(UserActivityFragment.TAG) as UserActivityFragment
                assertNotNull(homeFragment)

            }
            R.id.navigation_subscription -> {
                val homeFragment = mDashboardActivity.supportFragmentManager.findFragmentByTag(SubscriptionFragment.TAG) as SubscriptionFragment
                assertNotNull(homeFragment)

            }
        }
    }
}