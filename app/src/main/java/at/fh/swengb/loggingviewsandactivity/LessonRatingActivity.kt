package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lesson_rating.*
import kotlinx.android.synthetic.main.activity_rating.*
import kotlinx.android.synthetic.main.activity_views_copy.view.*

class LessonRatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)
        val lessonId = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)
        val lessonName = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_NAME)
        if (lessonId == null)
        {
            Toast.makeText(this,"id == null error", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        else
        {
            LessonRepository.lessonById(lessonId, success = { lesson_rating_header.text = it.name }, error = { "lessonById FAILED" })
            rate_lesson.setOnClickListener {
                val lessonRatingObject = LessonRating(lesson_rating_bar.rating.toDouble(), lesson_feedback?.editText.toString())
                LessonRepository.rateLesson(lessonId,lessonRatingObject, success = { }, error = { "rateLesson FAILED"} )
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }
}
