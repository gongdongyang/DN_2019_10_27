package com.gdy.dn_2019_10_27.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gdy.dn_2019_10_27.R
import io.flutter.facade.Flutter
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.view.FlutterView
import kotlinx.android.synthetic.main.activity_hflutter.*

class HFlutterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hflutter)


        //GeneratedPluginRegistrant.registerWith(this@HFlutterActivity)

        val flutterView = Flutter.createView(this, lifecycle, "Ly")
        fl_flutter_view.addView(flutterView)

        val listeners = arrayOfNulls<FlutterView.FirstFrameListener>(1);
        listeners[0] = FlutterView.FirstFrameListener(){
            fl_flutter_view.visibility = View.VISIBLE
        }

        flutterView.addFirstFrameListener(listeners[0])
    }

}
