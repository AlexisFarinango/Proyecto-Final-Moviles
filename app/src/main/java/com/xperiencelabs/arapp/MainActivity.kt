package com.xperiencelabs.arapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar el botón para navegar a la SecondActivity
        val navigateButton: MaterialButton = findViewById(R.id.navigateButton)
        navigateButton.setOnClickListener {
            // Iniciar la SecondActivity
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}
