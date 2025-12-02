package com.example.assignmentfourpartone

import android.app.Application
import android.content.Intent
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
class LeaderboardActivityTest {

    private lateinit var activity: LeaderboardActivity
    private lateinit var parent: LinearLayout
    private lateinit var tvLeaderboardTitle: TextView
    private lateinit var recyclerViewScores: RecyclerView
    private lateinit var btnClearHistory: Button
    private lateinit var btnBackHome: Button

    @Before
    fun setup() {
        // Set the theme before creating the activity
        val activityController = Robolectric.buildActivity(LeaderboardActivity::class.java)
        activityController.create().start().resume().visible()
        activity = activityController.get()

        // Use setTheme to override with AppCompat theme
        activity.setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light_NoActionBar)

        parent = activity.findViewById(R.id.leaderboard_layout)
        tvLeaderboardTitle = activity.findViewById(R.id.tvLeaderboardTitle)
        recyclerViewScores = activity.findViewById(R.id.recyclerViewScores)
        btnClearHistory = activity.findViewById(R.id.btnClearHistory)
        btnBackHome = activity.findViewById(R.id.btnBackHome)

        // Force measure & layout
        val widthSpec = View.MeasureSpec.makeMeasureSpec(500, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST)

        parent.measure(widthSpec, heightSpec)
        parent.layout(0, 0, parent.measuredWidth, parent.measuredHeight)
    }

    // Activity Tests
    @Test
    fun `activity should not be null`() {
        assertNotNull(activity)
    }

    @Test
    fun `views should be initialized`() {
        assertNotNull(parent)
        assertNotNull(tvLeaderboardTitle)
        assertNotNull(recyclerViewScores)
        assertNotNull(btnClearHistory)
        assertNotNull(btnBackHome)
    }

    // Layout Tests
    @Test
    fun `parent layout should be LinearLayout`() {
        val parent = tvLeaderboardTitle.parent
        assertNotNull(parent)
        assertEquals(LinearLayout::class.java, parent.javaClass)
    }

    // Leaderboard Score TextView Tests
    @Test
    fun `leaderboard score title should have correct text`() {
        val expectedText = activity.getString(R.string.score)
        assertEquals(expectedText, tvLeaderboardTitle.text.toString())
    }

    @Test
    fun `leaderboard score title should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.purple_700)
        assertEquals(expectedColor, tvLeaderboardTitle.currentTextColor)
    }

    @Test
    fun `leaderboard score title should have correct text size`() {
        assertEquals(spToPx(24), tvLeaderboardTitle.textSize, 0.1f)
    }

    @Test
    fun `leaderboard score title should have correct text style`() {
        assertEquals(Typeface.BOLD, tvLeaderboardTitle.typeface.style)
    }

    // Clear History Button Tests
    @Test
    fun `clear history button should have correct text`() {
        val expectedText = activity.getString(R.string.clear_history)
        assertEquals(expectedText, btnClearHistory.text.toString())
    }

    @Test
    fun `clear history button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnClearHistory.currentTextColor)
    }

    @Test
    fun `clear history button should have correct background`() {
        val background = btnClearHistory.background
        val expectedResId = R.drawable.rounded_button_purple
        val actualResId = shadowOf(background).createdFromResId
        assertEquals(expectedResId, actualResId)
    }

    @Test
    fun `clear history button should have correct text size`() {
        assertEquals(spToPx(16), btnClearHistory.textSize, 0.1f)
    }

    @Test
    fun `clear history button should have correct gravity`() {
        assertEquals(Gravity.CENTER, btnClearHistory.gravity)
    }

    // Back To Home Button Tests
    @Test
    fun `back to home button should have correct text`() {
        val expectedText = activity.getString(R.string.back_to_home)
        assertEquals(expectedText, btnBackHome.text.toString())
    }

    @Test
    fun `back to home button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnBackHome.currentTextColor)
    }

    @Test
    fun `back to home button should have correct background`() {
        val background = btnBackHome.background
        val expectedResId = R.drawable.rounded_button_purple
        val actualResId = shadowOf(background).createdFromResId
        assertEquals(expectedResId, actualResId)
    }

    @Test
    fun `back to home button should have correct text size`() {
        assertEquals(spToPx(16), btnBackHome.textSize, 0.1f)
    }

    @Test
    fun `back to home button should have correct gravity`() {
        assertEquals(Gravity.CENTER, btnBackHome.gravity)
    }

    // Navigation Tests
    @Test
    fun `clear history button should be clickable`() {
        assertEquals(true, btnClearHistory.hasOnClickListeners())
    }

    @Test
    fun `clicking back to home button should navigate to HomeActivity`() {
        btnBackHome.performClick()

        val expectedIntent = Intent(activity, HomeActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity

        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun `back to home button should be clickable`() {
        assertEquals(true, btnBackHome.hasOnClickListeners())
    }

    // Helper functions
    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            activity.resources.displayMetrics
        ).toInt()
    }

    private fun spToPx(sp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp.toFloat(),
            activity.resources.displayMetrics
        )
    }
}