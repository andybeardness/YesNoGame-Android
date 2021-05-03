package com.beardness.yesnogame

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {

  companion object {
    const val GITHUB_URL: String = "https://github.com/andybeardness/Android-YesNoGame"
    const val INSTAGRAM_URL: String = "https://www.instagram.com/beardness.andy/"
    const val TELEGRAM_URL: String = "https://t.me/beardness_andy"

    const val INSTAGRAM_PACKAGE: String = "com.instagram.android"
    const val TELEGRAM_PACKAGE: String = "org.telegram.messenger"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_info)
    overridePendingTransition(0, R.anim.fade_out)
  }

  override fun onBackPressed() {
    super.onBackPressed()
    overridePendingTransition(0, R.anim.fade_out)
  }

  fun onClickGithub(view: View) {
    val uri: Uri = Uri.parse(GITHUB_URL)

    val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
  }

  fun onClickInstagram(view: View) {
    val uri: Uri = Uri.parse(INSTAGRAM_URL)

    try {
      val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
      intent.setPackage(INSTAGRAM_PACKAGE)
      startActivity(intent)
    } catch (e: Exception) {
      val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
      startActivity(intent)
    }
  }

  fun onClickTelegram(view: View) {
    val uri: Uri = Uri.parse(TELEGRAM_URL)

    try {
      val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
      intent.setPackage(TELEGRAM_PACKAGE)
      startActivity(intent)
    } catch (e: java.lang.Exception) {
      val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
      startActivity(intent)
    }

  }


}