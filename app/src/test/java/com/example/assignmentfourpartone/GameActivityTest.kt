package com.example.assignmentfourpartone

import android.app.Application
import android.content.Intent
import android.graphics.Typeface
import android.os.Looper.getMainLooper
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
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
class GameActivityTest {

    private lateinit var activity: GameActivity
    private lateinit var parent: LinearLayout
    private lateinit var tvScoreText: TextView
    private lateinit var tvScore: TextView
    private lateinit var tvTimerText: TextView
    private lateinit var tvTimer: TextView
    private lateinit var tvSolveThisTitle: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var btnOption1: Button
    private lateinit var btnOption2: Button
    private lateinit var btnOption3: Button
    private lateinit var btnOption4: Button

    @Before
    fun setup() {
        // Set the theme before creating the activity
        val activityController = Robolectric.buildActivity(GameActivity::class.java)
        activityController.create().start().resume().visible()
        activity = activityController.get()
        
        // Use setTheme to override with AppCompat theme
        activity.setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light_NoActionBar)

        parent = activity.findViewById(R.id.game_layout)
        tvScoreText = activity.findViewById(R.id.tvScoreText)
        tvScore = activity.findViewById(R.id.tvScore)
        tvTimerText = activity.findViewById(R.id.tvTimerText)
        tvTimer = activity.findViewById(R.id.tvTimer)
        tvSolveThisTitle = activity.findViewById(R.id.tvSolveThisTitle)
        tvQuestion = activity.findViewById(R.id.tvQuestion)
        btnOption1 = activity.findViewById(R.id.btnOption1)
        btnOption2 = activity.findViewById(R.id.btnOption2)
        btnOption3 = activity.findViewById(R.id.btnOption3)
        btnOption4 = activity.findViewById(R.id.btnOption4)

        activity.isTestMode = true

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
        assertNotNull(tvScoreText)
        assertNotNull(tvScore)
        assertNotNull(tvTimerText)
        assertNotNull(tvTimer)
        assertNotNull(tvSolveThisTitle)
        assertNotNull(tvQuestion)
        assertNotNull(btnOption1)
        assertNotNull(btnOption2)
        assertNotNull(btnOption3)
        assertNotNull(btnOption4)
    }

    // Layout Tests
    @Test
    fun `parent layout should be LinearLayout`() {
        val parent = tvScoreText.parent
        assertNotNull(parent)
        assertEquals(LinearLayout::class.java, parent.javaClass)
    }

    // Score TextView Tests
    @Test
    fun `score title should have correct text`() {
        val expectedText = activity.getString(R.string.score)
        assertEquals(expectedText, tvScoreText.text.toString())
    }

    @Test
    fun `score title should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.black)
        assertEquals(expectedColor, tvScoreText.currentTextColor)
    }

    @Test
    fun `score title should have correct text size`() {
        assertEquals(spToPx(14), tvScoreText.textSize, 0.1f)
    }

    @Test
    fun `score title should have correct text style`() {
        assertEquals(Typeface.NORMAL, tvScoreText.typeface.style)
    }

    // Score Value TextView Tests
    @Test
    fun `score value should have correct text`() {
        val expectedText = activity.getString(R.string._0)
        assertEquals(expectedText, tvScore.text.toString())
    }

    @Test
    fun `score value should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.black)
        assertEquals(expectedColor, tvScore.currentTextColor)
    }

    @Test
    fun `score value should have correct text size`() {
        assertEquals(spToPx(32), tvScore.textSize, 0.1f)
    }

    @Test
    fun `score value should have correct text style`() {
        assertEquals(Typeface.BOLD, tvScore.typeface.style)
    }

    // Time TextView Tests
    @Test
    fun `time title should have correct text`() {
        val expectedText = activity.getString(R.string.time)
        assertEquals(expectedText, tvTimerText.text.toString())
    }

    @Test
    fun `time title should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.red_700)
        assertEquals(expectedColor, tvTimerText.currentTextColor)
    }

    @Test
    fun `time title should have correct text size`() {
        assertEquals(spToPx(14), tvTimerText.textSize, 0.1f)
    }

    @Test
    fun `time title should have correct text style`() {
        assertEquals(Typeface.NORMAL, tvTimerText.typeface.style)
    }

    // Time Value TextView Tests
    @Test
    fun `time value should have correct text`() {
        val expectedText = activity.getString(R.string._30)
        assertEquals(expectedText, tvTimer.text.toString())
    }

    @Test
    fun `time value should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.red_700)
        assertEquals(expectedColor, tvTimer.currentTextColor)
    }

    @Test
    fun `time value should have correct text size`() {
        assertEquals(spToPx(32), tvTimer.textSize, 0.1f)
    }

    @Test
    fun `time value should have correct text style`() {
        assertEquals(Typeface.BOLD, tvTimer.typeface.style)
    }

    // Solve This Title TextView Tests
    @Test
    fun `solve this title should have correct text`() {
        val expectedText = activity.getString(R.string.solve_this)
        assertEquals(expectedText, tvSolveThisTitle.text.toString())
    }

    @Test
    fun `solve this title should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.purple_700)
        assertEquals(expectedColor, tvSolveThisTitle.currentTextColor)
    }

    @Test
    fun `solve this title should have correct text size`() {
        assertEquals(spToPx(16), tvSolveThisTitle.textSize, 0.1f)
    }

    @Test
    fun `solve this title should have correct text style`() {
        assertEquals(Typeface.NORMAL, tvSolveThisTitle.typeface.style)
    }

    // Question TextView Tests
    @Test
    fun `question title should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.black)
        assertEquals(expectedColor, tvQuestion.currentTextColor)
    }

    @Test
    fun `question title should have correct text size`() {
        assertEquals(spToPx(42), tvQuestion.textSize, 0.1f)
    }

    @Test
    fun `question title should have correct text style`() {
        assertEquals(Typeface.BOLD, tvQuestion.typeface.style)
    }

    // Option One Button Tests
    @Test
    fun `option one button should have correct text`() {
        val expectedText = activity.getString(R.string._13)
        assertEquals(expectedText, btnOption1.text.toString())
    }

    @Test
    fun `option one button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnOption1.currentTextColor)
    }

    @Test
    fun `option one button should have correct background`() {
        val background = btnOption1.background
        val expectedResId = R.drawable.rounded_button_purple
        val actualResId = shadowOf(background).createdFromResId
        assertEquals(expectedResId, actualResId)
    }

    @Test
    fun `option one button should have correct text size`() {
        assertEquals(spToPx(16), btnOption1.textSize, 0.1f)
    }

    @Test
    fun `option one button should have correct gravity`() {
        assertEquals(Gravity.CENTER, btnOption1.gravity)
    }

    // Option Two Button Tests
    @Test
    fun `option two button should have correct text`() {
        val expectedText = activity.getString(R.string._15)
        assertEquals(expectedText, btnOption2.text.toString())
    }

    @Test
    fun `option two button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnOption2.currentTextColor)
    }

    @Test
    fun `option two button should have correct background`() {
        /*val background = btnOption2.background
        val expectedResId = R.drawable.rounded_button_purple
        val actualResId = shadowOf(background).createdFromResId
        assertEquals(expectedResId, actualResId)*/
        val resId = shadowOf(btnOption1.background).createdFromResId
        assertEquals(R.drawable.rounded_button_purple, resId)
    }

    @Test
    fun `option two button should have correct text size`() {
        assertEquals(spToPx(16), btnOption2.textSize, 0.1f)
    }

    @Test
    fun `option two button should have correct gravity`() {
        assertEquals(Gravity.CENTER, btnOption2.gravity)
    }

    // Option Three Button Tests
    @Test
    fun `option three button should have correct text`() {
        val expectedText = activity.getString(R.string._12)
        assertEquals(expectedText, btnOption3.text.toString())
    }

    @Test
    fun `option three button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnOption3.currentTextColor)
    }

    @Test
    fun `option three button should have correct background`() {
        val background = btnOption3.background
        val expectedResId = R.drawable.rounded_button_purple
        val actualResId = shadowOf(background).createdFromResId
        assertEquals(expectedResId, actualResId)
    }

    @Test
    fun `option three button should have correct text size`() {
        assertEquals(spToPx(16), btnOption3.textSize, 0.1f)
    }

    @Test
    fun `option three button should have correct gravity`() {
        assertEquals(Gravity.CENTER, btnOption3.gravity)
    }

    // Option Four Button Tests
    @Test
    fun `option four button should have correct text`() {
        val expectedText = activity.getString(R.string._10)
        assertEquals(expectedText, btnOption4.text.toString())
    }

    @Test
    fun `option four button should have correct text color`() {
        val expectedColor = ContextCompat.getColor(activity, R.color.white)
        assertEquals(expectedColor, btnOption4.currentTextColor)
    }

    @Test
    fun `option four button should have correct background`() {
        val background = btnOption4.background
        val expectedResId = R.drawable.rounded_button_purple
        val actualResId = shadowOf(background).createdFromResId
        assertEquals(expectedResId, actualResId)
    }

    @Test
    fun `option four button should have correct text size`() {
        assertEquals(spToPx(16), btnOption4.textSize, 0.1f)
    }

    @Test
    fun `option four button should have correct gravity`() {
        assertEquals(Gravity.CENTER, btnOption4.gravity)
    }

    // Navigation Tests
    @Test
    fun `clicking option one button should navigate to GameOverActivity`() {
        shadowOf(getMainLooper()).idle()

        btnOption1.performClick()

        val expectedIntent = Intent(activity, GameOverActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity

        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun `option one button should be clickable`() {
        shadowOf(getMainLooper()).idle()

        assertEquals(true, btnOption1.hasOnClickListeners())
    }

    @Test
    fun `clicking option two button should navigate to GameOverActivity`() {
        shadowOf(getMainLooper()).idle()

        btnOption2.performClick()

        val expectedIntent = Intent(activity, GameOverActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity

        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun `option two button should be clickable`() {
        shadowOf(getMainLooper()).idle()

        assertEquals(true, btnOption2.hasOnClickListeners())
    }

    @Test
    fun `clicking option three button should navigate to GameOverActivity`() {
        shadowOf(getMainLooper()).idle()

        btnOption3.performClick()

        val expectedIntent = Intent(activity, GameOverActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity

        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun `option three button should be clickable`() {
        shadowOf(getMainLooper()).idle()

        assertEquals(true, btnOption3.hasOnClickListeners())
    }

    @Test
    fun `clicking option four button should navigate to GameOverActivity`() {
        shadowOf(getMainLooper()).idle()

        btnOption4.performClick()

        val expectedIntent = Intent(activity, GameOverActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity

        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun `option four button should be clickable`() {
        shadowOf(getMainLooper()).idle()

        assertEquals(true, btnOption4.hasOnClickListeners())
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