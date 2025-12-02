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
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
class GameOverActivityTest {

    private lateinit var activity: GameOverActivity
    private lateinit var parent: LinearLayout
    private lateinit var tvGameOverTitle: TextView
    private lateinit var scoreCardLayout: LinearLayout
    private lateinit var tvYourScore: TextView
    private lateinit var tvYourScoreValue: TextView
    private lateinit var btnPlayAgain: Button
    private lateinit var btnSaveScore: Button

    @Before
    fun setup() {
        // Set the theme before creating the activity
        val activityController = Robolectric.buildActivity(GameOverActivity::class.java)
        activityController.create().start().resume().visible()
        activity = activityController.get()
        
        // Use setTheme to override with AppCompat theme
        activity.setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light_NoActionBar)

        parent = activity.findViewById(R.id.game_over_layout)
        tvGameOverTitle = activity.findViewById(R.id.tvGameOverTitle)
        scoreCardLayout = activity.findViewById(R.id.score_card_layout)
        tvYourScore = activity.findViewById(R.id.tvYourScore)
        tvYourScoreValue = activity.findViewById(R.id.tvYourScoreValue)
        btnPlayAgain = activity.findViewById(R.id.btnPlayAgain)
        btnSaveScore = activity.findViewById(R.id.btnSaveScore)

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
        assertNotNull(tvGameOverTitle)
        assertNotNull(scoreCardLayout)
        assertNotNull(tvYourScore)
        assertNotNull(tvYourScoreValue)
        assertNotNull(btnPlayAgain)
        assertNotNull(btnSaveScore)
    }

    // Layout Tests
    @Test
    fun `parent layout should be LinearLayout`() {
        val parent = tvGameOverTitle.parent
        assertNotNull(parent)
        assertEquals(LinearLayout::class.java, parent.javaClass)
    }

    // Game over TextView Tests
    @Test
    fun `game over title should have correct text`() {
        val expectedText = activity.getString(R.string.game_over)
        assertEquals(expectedText, tvGameOverTitle.text.toString())
    }

    @Test
    fun `game over title should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.purple_700)
        assertEquals(expectedColor, tvGameOverTitle.currentTextColor)
    }

    @Test
    fun `game over title should have correct text size`() {
        assertEquals(spToPx(48), tvGameOverTitle.textSize, 0.1f)
    }

    @Test
    fun `game over title should have correct text style`() {
        assertEquals(Typeface.BOLD, tvGameOverTitle.typeface.style)
    }

    @Test
    fun `game over title should have correct gravity`() {
        assertEquals(Gravity.CENTER, tvGameOverTitle.gravity)
    }

    // Score Card
    @Test
    fun `score card layout should have correct gravity`() {
        assertEquals(Gravity.CENTER, scoreCardLayout.gravity)
    }

    // Your score TextView Tests
    @Test
    fun `your score title should have correct text`() {
        val expectedText = activity.getString(R.string.your_score)
        assertEquals(expectedText, tvYourScore.text.toString())
    }

    @Test
    fun `your score title should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.purple_500)
        assertEquals(expectedColor, tvYourScore.currentTextColor)
    }

    @Test
    fun `your score title should have correct text size`() {
        assertEquals(spToPx(20), tvYourScore.textSize, 0.1f)
    }

    @Test
    fun `your score title should have correct text style`() {
        assertEquals(Typeface.NORMAL, tvYourScore.typeface.style)
    }

    // Your score value TextView Tests
    @Test
    fun `your score value should have correct text`() {
        val expectedText = activity.getString(R.string._0)
        assertEquals(expectedText, tvYourScoreValue.text.toString())
    }

    @Test
    fun `your score value should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.purple_500)
        assertEquals(expectedColor, tvYourScoreValue.currentTextColor)
    }

    @Test
    fun `your score value should have correct text size`() {
        assertEquals(spToPx(64), tvYourScoreValue.textSize, 0.1f)
    }

    @Test
    fun `your score value should have correct text style`() {
        assertEquals(Typeface.BOLD, tvYourScoreValue.typeface.style)
    }

    // Play Again Button Tests
    @Test
    fun `play again button should have correct text`() {
        val expectedText = activity.getString(R.string.play_again)
        assertEquals(expectedText, btnPlayAgain.text.toString())
    }

    @Test
    fun `play again button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnPlayAgain.currentTextColor)
    }

    @Test
    fun `play again button should have correct background`() {
        val background = btnPlayAgain.background
        val expectedResId = R.drawable.rounded_button_purple
        val actualResId = shadowOf(background).createdFromResId
        assertEquals(expectedResId, actualResId)
    }

    @Test
    fun `play again button should have correct text size`() {
        assertEquals(spToPx(16), btnPlayAgain.textSize, 0.1f)
    }

    @Test
    fun `play again button should have correct gravity`() {
        assertEquals(Gravity.CENTER, btnPlayAgain.gravity)
    }

    // Save Score Button Tests
    @Test
    fun `save score button should have correct text`() {
        val expectedText = activity.getString(R.string.save_score)
        assertEquals(expectedText, btnSaveScore.text.toString())
    }

    @Test
    fun `save score button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnSaveScore.currentTextColor)
    }

    @Test
    fun `save score button should have correct background`() {
        val background = btnSaveScore.background
        val expectedResId = R.drawable.rounded_button_purple
        val actualResId = shadowOf(background).createdFromResId
        assertEquals(expectedResId, actualResId)
    }

    @Test
    fun `save score button should have correct text size`() {
        assertEquals(spToPx(16), btnSaveScore.textSize, 0.1f)
    }

    @Test
    fun `save score button should have correct gravity`() {
        assertEquals(Gravity.CENTER, btnSaveScore.gravity)
    }

    // Navigation Tests
    @Test
    fun `play again button should navigate to GameActivity`() {
        btnPlayAgain.performClick()

        val expectedIntent = Intent(activity, GameActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity

        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun `play again button should be clickable`() {
        assertEquals(true, btnPlayAgain.hasOnClickListeners())
    }

    @Test
    fun `save score button should navigate to LeaderboardActivity`() {
        btnSaveScore.performClick()

        val expectedIntent = Intent(activity, LeaderboardActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity

        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun `save score button should be clickable`() {
        assertEquals(true, btnSaveScore.hasOnClickListeners())
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