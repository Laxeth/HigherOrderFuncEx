package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lesson_rating.*
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
            lesson_rating_header.text = LessonRepository.lessonById(lessonId)?.name
            rate_lesson.setOnClickListener {
                val lessonRatingObject = LessonRating(lesson_rating_bar.rating.toDouble(), lesson_feedback?.editText.toString())
                LessonRepository.rateLesson(lessonId,lessonRatingObject)
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }
}
