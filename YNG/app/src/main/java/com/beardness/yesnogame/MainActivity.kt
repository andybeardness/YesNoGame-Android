package com.beardness.yesnogame

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beardness.yesnogame.DB.Database
import com.beardness.yesnogame.RecyclerViewAdapter.AdapterYN

class MainActivity : AppCompatActivity() {

  var database: SQLiteOpenHelper? = null
  var db: SQLiteDatabase? = null
  var toolbar: Toolbar? = null
  var recycleDataList: RecyclerView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    overridePendingTransition(0, R.anim.fade_out)

    database = Database(this)
    db = database!!.writableDatabase

    toolbar = findViewById(R.id.toolbar)
    toolbar!!.title = getString(R.string.app_name)
    setSupportActionBar(toolbar)
  }

  override fun onResume() {
    super.onResume()

    recycleDataList = findViewById(R.id.recycler_data_list)
    recycleDataList!!.layoutManager = LinearLayoutManager(this)
    recycleDataList!!.adapter = AdapterYN(this, db!!)
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

}
