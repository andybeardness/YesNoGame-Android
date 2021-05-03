package com.beardness.yesnogame.RecyclerViewAdapter

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beardness.yesnogame.DB.Database
import com.beardness.yesnogame.QuestionAnswerActivity
import com.beardness.yesnogame.R

class AdapterYN(
  private val context: Context,
  db: SQLiteDatabase) :
    RecyclerView.Adapter<AdapterYN.ViewHolderYN>() {

  private val cursor: Cursor = db.query(
    Database.DB_NAME,
    arrayOf(
      Database.COL_ID,
      Database.COL_TITLE,
      Database.COL_QUESTION,
      Database.COL_ANSWER,
      Database.COL_IS_WATCHED
    ),
    null,
    null,
    null,
    null,
    null
  )

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderYN {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
    return ViewHolderYN(view)
  }

  override fun onBindViewHolder(holder: ViewHolderYN, position: Int) {
    cursor.moveToPosition(position)

    Log.d("BIND_VH_POSITION", "\t\t\t\t :: $position")

    val id: Int = cursor.getInt(cursor.getColumnIndex(Database.COL_ID))
    val title: String = cursor.getString(cursor.getColumnIndex(Database.COL_TITLE))
    val question: String = cursor.getString(cursor.getColumnIndex(Database.COL_QUESTION))
    val answer: String = cursor.getString(cursor.getColumnIndex(Database.COL_ANSWER))
    val isWatched: Int = cursor.getInt(cursor.getColumnIndex(Database.COL_IS_WATCHED))

    Log.d("BIND_VH", "TITLE : $title | IS_WATCHED : $isWatched")

    holder.bind(context, id, title, question, answer, isWatched)
  }

  override fun getItemCount(): Int {
    return cursor.count
  }

  // VIEW HOLDER CLASS HERE !
  class ViewHolderYN(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvTitle: TextView = itemView.findViewById(R.id.list_item_title)
    private val tvQuestion: TextView = itemView.findViewById(R.id.list_item_question)

    fun bind(context: Context, id: Int, title: String, question: String, answer: String, isWatched: Int) {

      tvTitle.text = title
      tvQuestion.text = question

      if (isWatched == 1) {
        itemView.setBackgroundResource(R.drawable.item_list_background_watched)
        Log.d("BIND_VH_IS_WATCHED", " >>>>>>>>>>>>>>>>>>>>>>> $isWatched")
      }
      else {
        itemView.setBackgroundResource(R.drawable.item_list_background)
      }

      itemView.setOnClickListener {
        val intent: Intent = Intent(context, QuestionAnswerActivity::class.java)
        intent.putExtra(QuestionAnswerActivity.EXTRA_TYPE_QA, QuestionAnswerActivity.TYPE_QUESTION)
        intent.putExtra(QuestionAnswerActivity.EXTRA_ID, id)
        intent.putExtra(QuestionAnswerActivity.EXTRA_TITLE, title)
        intent.putExtra(QuestionAnswerActivity.EXTRA_QUESTION, question)
        intent.putExtra(QuestionAnswerActivity.EXTRA_ANSWER, answer)
        intent.putExtra(QuestionAnswerActivity.EXTRA_IS_WATCHED, isWatched)

        context.startActivity(intent)
      }
    }

  }
}