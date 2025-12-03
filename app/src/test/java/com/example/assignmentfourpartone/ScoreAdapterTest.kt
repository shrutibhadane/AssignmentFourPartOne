package com.example.assignmentfourpartone

import android.app.Application
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.example.assignmentfourpartone.ScoreAdapter
import com.example.assignmentfourpartone.ScoreModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ScoreAdapterTest {

    private lateinit var adapter: ScoreAdapter
    private lateinit var scoreList: MutableList<ScoreModel>

    @Before
    fun setup() {
        scoreList = mutableListOf(
            ScoreModel("Shruti", 95, "01/12/2025 10:10:10"),
            ScoreModel("Amit", 88, "02/12/2025 11:15:00")
        )
        adapter = ScoreAdapter(scoreList)
    }

    @Test
    fun `getItemCount should return correct size`() {
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun `onBindViewHolder should bind correct data`() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        val parent = RecyclerView(context)
        parent.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)  // Add this line

        val viewHolder = adapter.onCreateViewHolder(parent, 0)
        adapter.onBindViewHolder(viewHolder, 0)

        assertEquals("Shruti", viewHolder.tvName.text.toString())
        assertEquals("95", viewHolder.tvScore.text.toString())
        assertEquals("01/12/2025 10:10:10", viewHolder.tvDate.text.toString())
    }

    @Test
    fun `adapter should reflect updated list`() {
        scoreList.add(ScoreModel("Neha", 78, "03/12/2025 12:20:30"))

        assertEquals(3, adapter.itemCount)
    }
}
