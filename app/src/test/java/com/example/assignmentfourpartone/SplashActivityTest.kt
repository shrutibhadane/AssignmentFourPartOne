package com.example.assignmentfourpartone

import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.intArrayOf
import kotlin.use

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class SplashActivityTest {

    private lateinit var activity: SplashActivity
    private lateinit var parent: LinearLayout
    private lateinit var imgLogo: ImageView
    private lateinit var appName: TextView

    @Before
    fun setup() {
        Robolectric.buildActivity(SplashActivity::class.java).use { controller ->
            controller.setup()
            activity = controller.get()
        }

        parent = activity.findViewById(R.id.splash_layout)
        imgLogo = activity.findViewById(R.id.logoImage)
        appName = activity.findViewById(R.id.appName)

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
        assertNotNull(imgLogo)
        assertNotNull(appName)
    }

    // Layout Tests
    @Test
    fun `parent layout should be ConstraintLayout`() {
        val parent = appName.parent
        assertNotNull(parent)
        assertEquals(LinearLayout::class.java, parent.javaClass)
    }

    // Title TextView Tests
    @Test
    fun `title should have correct text`() {
        assertEquals(R.string.app_name, appName.text.toString())
    }

    @Test
    fun `title should have correct text color`() {
        assertEquals(R.color.purple_700, appName.currentTextColor)
    }

    @Test
    fun `title should have correct text size`() {
        assertEquals(spToPx(28), appName.textSize, 0.1f)
    }

    @Test
    fun `title should have correct text style`() {
        assertEquals(Typeface.BOLD, appName.typeface.style)
    }

    @Test
    fun `title should have correct gravity`() {
        assertEquals(Gravity.CENTER, appName.gravity)
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