package io.github.kawishkamd.volumewidget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

/**
 * Receives the widget's +/- taps. Declared with exported="false" so that only
 * this app's own PendingIntents can trigger a volume change.
 */
class VolumeButtonReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val direction = when (intent.action) {
            ACTION_VOLUME_UP -> AudioManager.ADJUST_RAISE
            ACTION_VOLUME_DOWN -> AudioManager.ADJUST_LOWER
            else -> return
        }

        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(20)
        }

        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, direction, AudioManager.FLAG_SHOW_UI)
    }

    companion object {
        const val ACTION_VOLUME_UP = "io.github.kawishkamd.volumewidget.ACTION_VOLUME_UP"
        const val ACTION_VOLUME_DOWN = "io.github.kawishkamd.volumewidget.ACTION_VOLUME_DOWN"
    }
}
