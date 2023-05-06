package com.example.pocketpal


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.pocketpal.modelsb.BillModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.text.SimpleDateFormat
import java.util.*


const val channelId ="notification_channel"
const val channelName="com.example.pocketpal"


class MyFirebaseMessagingService: FirebaseMessagingService() {


   override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]
        if (remoteMessage.getNotification() != null) {
            sendNotification(
                remoteMessage.notification!!.title!!,
                remoteMessage.notification!!.body!!
            )


        }


    }
        private fun sendNotification(title: String, message: String) {
            val intent = Intent(this, Dashboard::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

            //channel id, chanel name
            var builder: NotificationCompat.Builder = NotificationCompat.Builder(
                applicationContext,
                channelId
            ).setSmallIcon(R.drawable.ic_notification)
                .setAutoCancel(true)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)

            builder = builder.setContent(getRemoteView(title, message))

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel =
                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)
            }
            notificationManager.notify(0, builder.build())
        }

        private fun getRemoteView(title: String, message: String): RemoteViews? {
            val remoteView = RemoteViews("com.example.pocketpal", R.layout.notificationb)

            remoteView.setTextViewText(R.id.title, title)
            remoteView.setTextViewText(R.id.message, message)
            remoteView.setImageViewResource(R.id.app_logo, R.drawable.ic_notification)

            return remoteView
        }

    }
