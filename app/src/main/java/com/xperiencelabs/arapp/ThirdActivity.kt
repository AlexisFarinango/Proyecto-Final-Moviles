package com.xperiencelabs.arapp
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class ThirdActivity : AppCompatActivity()  {
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        // Configura los OnClickListeners para cada TextView
        findViewById<TextView>(R.id.text1).setOnClickListener { openUrl("https://esfot.epn.edu.ec/") }
        findViewById<TextView>(R.id.text2).setOnClickListener { openUrl("https://fiqa.epn.edu.ec") }
        findViewById<TextView>(R.id.text3).setOnClickListener { openUrl("https://fca.epn.edu.ec") }
        findViewById<TextView>(R.id.text4).setOnClickListener { openUrl("https://fis.epn.edu.ec/index.php/es/") }
        findViewById<TextView>(R.id.text5).setOnClickListener { openUrl("https://fgp.epn.edu.ec") }
         // Configura los OnClickListeners para cada ImageView
        findViewById<ImageView>(R.id.image1).setOnClickListener { openUrl("https://esfot.epn.edu.ec/") }
        findViewById<ImageView>(R.id.image2).setOnClickListener { openUrl("https://fiqa.epn.edu.ec") }
        findViewById<ImageView>(R.id.image3).setOnClickListener { openUrl("https://fca.epn.edu.ec") }
        findViewById<ImageView>(R.id.image4).setOnClickListener { openUrl("https://fis.epn.edu.ec/index.php/es/") }
        findViewById<ImageView>(R.id.image5).setOnClickListener { openUrl("https://fgp.epn.edu.ec") }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}