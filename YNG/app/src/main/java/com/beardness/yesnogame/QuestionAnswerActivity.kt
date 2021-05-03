package com.beardness.yesnogame

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.beardness.yesnogame.DB.Database

class QuestionAnswerActivity : AppCompatActivity() {

  companion object {
    const val EXTRA_TYPE_QA: String = "TYPE_QA"
    const val EXTRA_ID: String = "ID"
    const val EXTRA_TITLE: String = "TITLE"
    const val EXTRA_QUESTION: String = "QUESTION"
    const val EXTRA_ANSWER: String = "ANSWER"
    const val EXTRA_IS_WATCHED: String = "IS_WATCHED"

    const val TYPE_QUESTION: Int = 0
    const val TYPE_ANSWER: Int = 1
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_question_answer)
    overridePendingTransition(0, R.anim.fade_out)

    val toolbar: Toolbar = findViewById(R.id.toolbar)
    toolbar.title = getString(R.string.app_name)
    setSupportActionBar(toolbar)

    val db: SQLiteDatabase = Database(this).writableDatabase

    if (intent != null) {
      val type: Int = intent.getIntExtra(EXTRA_TYPE_QA, 0)
      val extraID: Int = intent.getIntExtra(EXTRA_ID, 1)
      val extraTitle: String? = intent.getStringExtra(EXTRA_TITLE)
      val extraQuestion: String? = intent.getStringExtra(EXTRA_QUESTION)
      val extraAnswer: String? = intent.getStringExtra(EXTRA_ANSWER)
      val extraIsWatched: Int = intent.getIntExtra(EXTRA_IS_WATCHED, 0)

      val qaTitle: TextView = findViewById(R.id.qa_title)
      val qaText: TextView = findViewById(R.id.qa_text)
      val qaButton: Button = findViewById(R.id.qa_button)

      when (type) {
        TYPE_ANSWER -> {
          val content: ContentValues = ContentValues()
          content.put(Database.COL_IS_WATCHED, 1)
          db.update(Database.DB_NAME, content, "${Database.COL_ID} = ?", arrayOf("$extraID"))

          qaTitle.text = extraTitle + " : Ответ"
          qaText.text = extraAnswer
          qaButton.text = "Назад"
          qaButton.setOnClickListener {
            onBackPressed()
          }
        }
        TYPE_QUESTION -> {
          qaTitle.text = extraTitle
          qaText.text = extraQuestion
          qaButton.text = "Показать ответ"
          qaButton.setOnClickListener {
            val intentNext: Intent = Intent(this, QuestionAnswerActivity::class.java)
            intentNext.putExtra(EXTRA_TYPE_QA, TYPE_ANSWER)
            intentNext.putExtra(EXTRA_ID, extraID)
            intentNext.putExtra(EXTRA_TITLE, extraTitle)
            intentNext.putExtra(EXTRA_QUESTION, extraQuestion)
            intentNext.putExtra(EXTRA_ANSWER, extraAnswer)
            intentNext.putExtra(EXTRA_IS_WATCHED, extraIsWatched)
            startActivity(intentNext)
          }
        }
        else -> {}
      }
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.toolbar_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.menu_info -> {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
      }
    }

    return true
  }

  override fun onBackPressed() {
    super.onBackPressed()
    overridePendingTransition(0, R.anim.fade_out)
  }

}