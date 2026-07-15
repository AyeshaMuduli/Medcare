package com.example.medcare.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.medcare.R

object NotificationHelper {

    private const val CHANNEL_ID = "medcare_orders"

    fun createNotificationChannel(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                "Order Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )

            channel.description = "Notifications for MedCare orders"

            val manager =
                context.getSystemService(NotificationManager::class.java)

            manager.createNotificationChannel(channel)
        }
    }

    fun showOrderPlacedNotification(context: Context) {

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Order Placed")
            .setContentText("Your medicines will be delivered soon.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        NotificationManagerCompat.from(context)
            .notify(1001, builder.build())
    }
}
