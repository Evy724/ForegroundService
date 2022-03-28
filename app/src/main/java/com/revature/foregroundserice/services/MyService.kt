package com.revature.foregroundserice.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.revature.foregroundserice.R

//Foreground Service

const val NOTIFICATION_CHANNEL_GENERAL = "Checking"
const val INTENT_COMMAND = "Command"
const val INTENT_COMMAND_REPLY = "Command Reply"

class MyService : Service() {
    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val command = intent?.getStringExtra(INTENT_COMMAND)
        if(command == "Stop") {
            stopService()
            return START_NOT_STICKY
        }

        showNotification()

        if(command == "Reply") {
            Toast.makeText(this, "Clicked in Notification", Toast.LENGTH_LONG).show()
        }
        return START_NOT_STICKY
        //return super.onStartCommand(intent, flags, startId)
    }
    override fun stopService(name: Intent?): Boolean {
        return super.stopService(name)
    }
    private fun stopService() {
        stopForeground(true)
        stopSelf()
    }
    private fun showNotification() {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val replyIntent = Intent(this, MyService::class.java).apply {
            putExtra(INTENT_COMMAND, INTENT_COMMAND_REPLY)
        }
        val replyPendingIntent = PendingIntent.getService(this, 2 , replyIntent, 0)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                with(
                    NotificationChannel(
                        NOTIFICATION_CHANNEL_GENERAL,
                        "Hello Evan",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                ) {
                    enableLights(true)
                    setShowBadge(true)
                    enableVibration(true)
                    setSound(null, null)
                    description = "Notification Description"
                    lockscreenVisibility = Notification.VISIBILITY_PRIVATE
                    //this.startForegroundService("Start")
                    manager.createNotificationChannel(this)

                }
            } catch (e: Exception) {
                Log.d("Notification Error", "Show Notification ${e.localizedMessage}")
            }
        }
        with (
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_GENERAL)
        ) {
            setContentTitle("First")
            setContentText("Notification Text")
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setContentIntent(replyPendingIntent)
            addAction(0, "Reply", replyPendingIntent)
            addAction(0, "Answer", replyPendingIntent)
            startForeground(1, build())
        }
        //use the alternative code to create notification the old way
    }
}