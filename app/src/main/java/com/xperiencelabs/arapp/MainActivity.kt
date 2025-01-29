
package com.xperiencelabs.arapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.ar.core.Config
import com.google.zxing.integration.android.IntentIntegrator
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position

class MainActivity : AppCompatActivity() {

    private lateinit var sceneView: ArSceneView
    private lateinit var placeButton: ExtendedFloatingActionButton
    private lateinit var modelNodeCarro: ArModelNode
    private lateinit var modelNodeAvion: ArModelNode
    private lateinit var modelNodeTanque: ArModelNode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sceneView = findViewById<ArSceneView>(R.id.sceneView).apply {
            this.lightEstimationMode = Config.LightEstimationMode.DISABLED
        }

        // Configura el botón para iniciar el escaneo del código QR
        placeButton = findViewById(R.id.place)
        placeButton.setOnClickListener {
            startQRCodeScanner()
        }

        // Inicializa los modelos en AR
        initializeModels()
    }

    // Inicializar los modelos AR
    private fun initializeModels() {
        modelNodeCarro = ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/plano2.glb",
                scaleToUnits = 0.4f,
                centerOrigin = Position(x = 0f, y = -4f, z = 0f)
            )
        }

        modelNodeAvion = ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/poli.glb",
                scaleToUnits = 1.5f,
                centerOrigin = Position(x = 0f, y = -1f, z = -1.5f)
            )
        }

        modelNodeTanque = ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/tanque.glb",
                scaleToUnits = 1f,
                centerOrigin = Position(x = -2f, y = -4f, z = 0f)
            )
        }
    }

    // Inicia el escaneo del código QR
    private fun startQRCodeScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanee un código QR")
        integrator.setCameraId(0)  // Usa la cámara trasera
        integrator.setBeepEnabled(false)
        integrator.initiateScan()
    }

    // Manejar el resultado del escaneo del código QR
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Escaneo cancelado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Modelo QR detectado: ${result.contents}", Toast.LENGTH_SHORT).show()
                loadModelBasedOnQRCode(result.contents)
            }
        }
    }

    // Cargar el modelo específico basado en el QR escaneado
    private fun loadModelBasedOnQRCode(qrContent: String) {
        // Eliminar cualquier modelo previo antes de agregar uno nuevo
        sceneView.children.filterIsInstance<ArModelNode>().forEach {
            sceneView.removeChild(it)
        }

        when (qrContent) {
            "carro" -> {
                if (!sceneView.children.contains(modelNodeCarro)) {
                    sceneView.addChild(modelNodeCarro)
                    modelNodeCarro.anchor()
                }
            }
            "avion" -> {
                if (!sceneView.children.contains(modelNodeAvion)) {
                    sceneView.addChild(modelNodeAvion)
                    modelNodeAvion.anchor()
                }
            }
            "tanque" -> {
                if (!sceneView.children.contains(modelNodeTanque)) {
                    sceneView.addChild(modelNodeTanque)
                    modelNodeTanque.anchor()
                }
            }
            else -> {
                Toast.makeText(this, "Modelo no encontrado", Toast.LENGTH_SHORT).show()
            }
        }

        sceneView.planeRenderer.isVisible = false
    }


    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

