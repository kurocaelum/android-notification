package android.imd.notificationexample

import android.app.Notification.EXTRA_TEXT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        txtMessage.text = intent.getStringExtra(EXTRA_TEXT)
    }

//    TODO
    companion object{
        val EXTRA_TEXT = "message"
    }
}
