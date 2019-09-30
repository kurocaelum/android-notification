package android.imd.notificationexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSimple.setOnClickListener {
            NotificationUtils.notificationSimple(this)
        }

        btnAction.setOnClickListener {
            NotificationUtils.notificationWithAction(this)
        }

        btnText.setOnClickListener {
            NotificationUtils.notificationBigText(this)
        }

        btnButtonAction.setOnClickListener {
            NotificationUtils.notificationWithButtonAction(this)
        }
    }
}
