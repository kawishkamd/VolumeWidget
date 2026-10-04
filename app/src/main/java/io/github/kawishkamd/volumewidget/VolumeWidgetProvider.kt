package com.example.volumewidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.RemoteViews

class VolumeWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        
        when (intent.action) {
            ACTION_VOLUME_UP, ACTION_VOLUME_DOWN -> {
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
                } else {
                    @Suppress("DEPRECATION")
                    vibrator.vibrate(20)
                }
                val direction = if (intent.action == ACTION_VOLUME_UP) AudioManager.ADJUST_RAISE else AudioManager.ADJUST_LOWER
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, direction, AudioManager.FLAG_SHOW_UI)
            }
        }
    }

    companion object {
        const val ACTION_VOLUME_UP = "com.example.volumewidget.ACTION_VOLUME_UP"
        const val ACTION_VOLUME_DOWN = "com.example.volumewidget.ACTION_VOLUME_DOWN"

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.volume_widget_layout)

            val upIntent = Intent(context, VolumeWidgetProvider::class.java).apply { action = ACTION_VOLUME_UP }
            val upPendingIntent = PendingIntent.getBroadcast(context, 0, upIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            views.setOnClickPendingIntent(R.id.widget_btn_up, upPendingIntent)

            val downIntent = Intent(context, VolumeWidgetProvider::class.java).apply { action = ACTION_VOLUME_DOWN }
            val downPendingIntent = PendingIntent.getBroadcast(context, 1, downIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            views.setOnClickPendingIntent(R.id.widget_btn_down, downPendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
