package io.github.kawishkamd.volumewidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class VolumeWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    companion object {
        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.volume_widget_layout)

            val upIntent = Intent(context, VolumeButtonReceiver::class.java).apply { action = VolumeButtonReceiver.ACTION_VOLUME_UP }
            val upPendingIntent = PendingIntent.getBroadcast(context, 0, upIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            views.setOnClickPendingIntent(R.id.widget_btn_up, upPendingIntent)

            val downIntent = Intent(context, VolumeButtonReceiver::class.java).apply { action = VolumeButtonReceiver.ACTION_VOLUME_DOWN }
            val downPendingIntent = PendingIntent.getBroadcast(context, 1, downIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            views.setOnClickPendingIntent(R.id.widget_btn_down, downPendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
