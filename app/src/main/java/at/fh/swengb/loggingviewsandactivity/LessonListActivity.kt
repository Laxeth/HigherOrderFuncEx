package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_lesson_list.*

class LessonListActivity : AppCompatActivity() {
    val lessonAdapter = LessonAdapter() {
        val implicitIntent= Intent(this, LessonRatingActivity::class.java)
        implicitIntent.putExtra(EXTRA_LESSON_ID, it.id)
        implicitIntent.putExtra(EXTRA_LESSON_NAME, it.name)
        startActivityForResult(implicitIntent, ADD_OR_EDIT_RATING_REQUEST)
        //onActivityResult(ADD_OR_EDIT_RATING_REQUEST, Activity.RESULT_OK,implicitIntent)


    }
    companion object {
        val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
        val EXTRA_LESSON_NAME = "EXTRA_LESSON_NAME"
        val ADD_OR_EDIT_RATING_REQUEST = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        lessonAdapter.updateList(LessonRepository.lessonsList())
        lesson_recycler_view.layoutManager = LinearLayoutManager(this)
        lesson_recycler_view.adapter = lessonAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode,resultCode,data)

        if (requestCode == ADD_OR_EDIT_RATING_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                lessonAdapter.updateList(LessonRepository.lessonsList())
            }
        }
    }
}

