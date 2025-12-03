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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
class HomeActivityTest {

    private lateinit var activity: HomeActivity
    private lateinit var parent: LinearLayout
    private lateinit var tvTitleText: TextView
    private lateinit var btnStartGame: Button
    private lateinit var btnViewLeaderboard: Button

    @Before
    fun setup() {
        // Set the theme before creating the activity
        val activityController = Robolectric.buildActivity(HomeActivity::class.java)
        activityController.create().start().resume().visible()
        activity = activityController.get()
        
        // Use setTheme to override with AppCompat theme
        activity.setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light_NoActionBar)

        parent = activity.findViewById(R.id.home_layout)
        tvTitleText = activity.findViewById(R.id.tvTitleText)
        btnStartGame = activity.findViewById(R.id.btnStartGame)
        btnViewLeaderboard = activity.findViewById(R.id.btnViewLeaderboard)

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
        assertNotNull(tvTitleText)
        assertNotNull(btnStartGame)
        assertNotNull(btnViewLeaderboard)
    }

    // Layout Tests
    @Test
    fun `parent layout should be LinearLayout`() {
        val parent = tvTitleText.parent
        assertNotNull(parent)
        assertEquals(LinearLayout::class.java, parent.javaClass)
    }

    // Title TextView Tests
    @Test
    fun `title should have correct text`() {
        val expectedText = activity.getString(R.string.app_name)
        assertEquals(expectedText, tvTitleText.text.toString())
    }

    @Test
    fun `title should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.purple_700)
        assertEquals(expectedColor, tvTitleText.currentTextColor)
    }

    @Test
    fun `title should have correct text size`() {
        assertEquals(spToPx(24), tvTitleText.textSize, 0.1f)
    }

    @Test
    fun `title should have correct text style`() {
        assertEquals(Typeface.BOLD, tvTitleText.typeface.style)
    }

    // Start Game Button Tests
    @Test
    fun `start game button should have correct text`() {
        val expectedText = activity.getString(R.string.start_game)
        assertEquals(expectedText, btnStartGame.text.toString())
    }

    @Test
    fun `start game button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnStartGame.currentTextColor)
    }

    @Test
    fun `start game button should have correct background`() {
        val background = btnStartGame.background
        val expectedResId = R.drawable.rounded_button_purple
        val actualResId = Shadows.shadowOf(background).createdFromResId
        assertEquals(expectedResId, actualResId)
    }

    @Test
    fun `start game button should have correct text size`() {
        assertEquals(spToPx(16), btnStartGame.textSize, 0.1f)
    }

    @Test
    fun `start game button should have correct gravity`() {
        assertEquals(Gravity.CENTER, btnStartGame.gravity)
    }

    @Test
    fun `start game button should have correct margin`() {
        val params = btnStartGame.layoutParams as LinearLayout.LayoutParams
        assertEquals(dpToPx(16), params.bottomMargin)
    }

    // View Leaderboard Button Tests
    @Test
    fun `View Leaderboard button should have correct text`() {
        val expectedText = activity.getString(R.string.view_leaderboard)
        assertEquals(expectedText, btnViewLeaderboard.text.toString())
    }

    @Test
    fun `View Leaderboard button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnViewLeaderboard.currentTextColor)
    }

    @Test
    fun `View Leaderboard button should have correct background`() {
        val background = btnViewLeaderboard.background
        val expectedResId = R.drawable.rounded_button_purple
        val actualResId = Shadows.shadowOf(background).createdFromResId
        assertEquals(expectedResId, actualResId)
    }

    @Test
    fun `View Leaderboard button should have correct text size`() {
        assertEquals(spToPx(16), btnViewLeaderboard.textSize, 0.1f)
    }

    @Test
    fun `View Leaderboard button should have correct gravity`() {
        assertEquals(Gravity.CENTER, btnViewLeaderboard.gravity)
    }

    // Navigation Tests
    @Test
    fun `clicking start game button should navigate to GameActivity`() {
        btnStartGame.performClick()

        val expectedIntent = Intent(activity, GameActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity

        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun `start game button should be clickable`() {
        assertEquals(true, btnStartGame.hasOnClickListeners())
    }

    @Test
    fun `clicking view leaderboard button should navigate to LeaderboardActivity`() {
        btnViewLeaderboard.performClick()

        val expectedIntent = Intent(activity, LeaderboardActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity

        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun `view leaderboard button should be clickable`() {
        assertEquals(true, btnViewLeaderboard.hasOnClickListeners())
    }

    @Test
    fun `buttons should be focusable for accessibility`() {
        assertEquals(true, btnStartGame.isFocusable)
        assertEquals(true, btnViewLeaderboard.isFocusable)
    }

    @Test
    fun `activity should finish when finish is called`() {
        activity.finish()
        assertEquals(true, activity.isFinishing)
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