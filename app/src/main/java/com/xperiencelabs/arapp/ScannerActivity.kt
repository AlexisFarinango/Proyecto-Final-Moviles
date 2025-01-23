package com.xperiencelabs.arapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.xperiencelabs.arapp.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator

class ScannerActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mostrar el escaner en pantalla vertical
        requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        // Iniciar el escáner automáticamente
        initScanner()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null && result.contents != null) {
            val scannedContent = result.contents

            if (scannedContent.startsWith("http") && scannedContent.contains(".pdf")) {
                // Si contiene ".pdf", intenta abrirlo como PDF
                showPDF(scannedContent)
            } else {
                // Abre el enlace en el navegador como fallback
                openInBrowser(scannedContent)
            }
            finish()
        } else {
            Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_LONG).show()
            super.onActivityResult(requestCode, resultCode, data)
            finish()
        }
    }

    private fun showPDF(pdfUrl: String) {
        try {
            // Crear un intento para abrir el PDF usando un visor de PDFs
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(pdfUrl), "application/pdf")
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "No se pudo abrir el PDF", Toast.LENGTH_LONG).show()
        }
    }



    // Función para abrir un enlace en el navegador
    private fun openInBrowser(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "No se pudo abrir el enlace", Toast.LENGTH_LONG).show()
        }
    }


    private fun initScanner() {
        try {
            val integrator = IntentIntegrator(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt("Escanea un código QR")
            integrator.setCameraId(0) // Usar la cámara trasera
            integrator.setBeepEnabled(false)
            integrator.setBarcodeImageEnabled(true)

            // Iniciar el escáner en modo vertical
            integrator.captureActivity = VerticalCaptureActivity::class.java

            // Iniciar el escáner
            integrator.initiateScan()
        } catch (e: Exception) {
            println("Excepcion en el escáner: $e")
            Toast.makeText(this, "Error al iniciar el escáner", Toast.LENGTH_LONG).show()

            // Regresar a la actividad anterior
            finish()
        }
    }

}




