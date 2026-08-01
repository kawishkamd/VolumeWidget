package com.example.volumewidget

import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var audioManager: AudioManager
    private lateinit var volumeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        audioManager = applicationContext.getSystemService(AUDIO_SERVICE) as AudioManager
        volumeText = findViewById(R.id.tv_volume_level)

        findViewById<Button>(R.id.btn_volume_up).setOnClickListener {
            adjustVolume(AudioManager.ADJUST_RAISE)
        }

        findViewById<Button>(R.id.btn_volume_down).setOnClickListener {
            adjustVolume(AudioManager.ADJUST_LOWER)
        }

        findViewById<Button>(R.id.btn_support).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ko-fi.com/kayz"))
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_github).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/kawishkamd/volumewidget"))
            startActivity(intent)
        }

        updateVolumeText()
    }

    override fun onResume() {
        super.onResume()
        updateVolumeText()
    }

    private fun adjustVolume(direction: Int) {
        audioManager.adjustStreamVolume(
            AudioManager.STREAM_MUSIC,
            direction,
            AudioManager.FLAG_SHOW_UI
        )
        updateVolumeText()
    }

    private fun updateVolumeText() {
        val max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val current = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        volumeText.text = getString(R.string.volume_level_value, current, max)
    }
}
