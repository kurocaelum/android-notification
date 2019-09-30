package android.imd.notificationexample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

// "Faz papel de singleton"
object NotificationUtils {
    val CHANNEL_ID = "default"

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(context: Context){

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelName = "Padrão"
        val channelDescription = "Canal padrão de notificações"
        val channel = NotificationChannel(
            CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = channelDescription
            enableLights(true)
            lightColor = Color.GREEN
            enableVibration(true)
            vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        }
        notificationManager.createNotificationChannel(channel)
    }

    fun notificationSimple(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(context)
        }

        var notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_favorite)
            .setContentTitle("Minha notificação")
            .setContentText("Texto da minha notificação")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(ActivityCompat.getColor(context, R.color.colorAccent)) //Pega color padrão do app
            .setDefaults(Notification.DEFAULT_ALL)

        var notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1, notificationBuilder.build())
    }

    //TODO DetailsActivity kt e xml
    private fun getContentIntent(context: Context):PendingIntent?{
        val intent = Intent(context, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EXTRA_TEXT, "Via notificação")
        }

        return PendingIntent.getActivity(context, 0, intent, 0)
    }

    fun notificationWithAction(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(context)
        }

        var notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_favorite)
            .setContentTitle("Minha notificação - Ação")
            .setContentText("Texto da minha notificação - Ação")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(ActivityCompat.getColor(context, R.color.colorAccent)) //Pega color padrão do app
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentIntent(getContentIntent(context))
            .setAutoCancel(true)

        var notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(2, notificationBuilder.build())
    }

    fun notificationBigText(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(context)
        }
        var bigTextStyle = NotificationCompat
            .BigTextStyle()
            .bigText("Texto da minha notificação - Big Text - AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")


        var notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_favorite)
            .setContentTitle("Minha notificação - Big Text")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(ActivityCompat.getColor(context, R.color.colorAccent)) //Pega color padrão do app
            .setDefaults(Notification.DEFAULT_ALL)
            .setStyle(bigTextStyle)
            .setAutoCancel(true)

        var notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(3, notificationBuilder.build())
    }

    fun notificationWithButtonAction(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(context)
        }

        val actionIntent = Intent(context, NotificationActionReceiver::class.java).apply {
            putExtra(NotificationActionReceiver.EXTRA_MESSAGE, "Ação da notificação")
        }

        val pendingIntent = PendingIntent.getBroadcast(context, 0, actionIntent, 0)

        var notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_favorite)
            .setContentTitle("Minha notificação - Botão Ação")
            .setContentText("Texto da minha notificação - Botão Ação")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(ActivityCompat.getColor(context, R.color.colorAccent)) //Pega color padrão do app
            .setDefaults(Notification.DEFAULT_ALL)
            .addAction(0, "Ação", pendingIntent)
            .setAutoCancel(true)

        var notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(4, notificationBuilder.build())
    }

}