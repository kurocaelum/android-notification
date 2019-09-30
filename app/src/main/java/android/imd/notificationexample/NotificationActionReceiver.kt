package android.imd.notificationexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(
            context,
            intent?.getStringExtra(EXTRA_MESSAGE),
            Toast.LENGTH_LONG
        ).show()
    }

    companion object{
        val EXTRA_MESSAGE = "message"
    }
}
