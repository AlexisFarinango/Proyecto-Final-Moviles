package com.xperiencelabs.arapp

import android.content.Intent
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

class SecondActivity : AppCompatActivity() {
    private lateinit var sceneView: ArSceneView
    private lateinit var placeButton: ExtendedFloatingActionButton
    private var currentModelNode: ArModelNode? = null // Para hacer un seguimiento del modelo actual

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        // Inicializa la vista de la escena AR
        sceneView = findViewById<ArSceneView>(R.id.sceneView).apply {
            this.lightEstimationMode = Config.LightEstimationMode.DISABLED
        }

        // Configura el botón flotante para iniciar el escaneo de QR
        placeButton = findViewById(R.id.place)
        placeButton.setOnClickListener {
            startQRCodeScanner()
        }
    }

    // Función para inicializar y cargar los modelos 3D en la escena (solo cuando se escanea el QR)
    private fun loadModel(modelName: String) {
        // Elimina el modelo anterior si existe
        currentModelNode?.let { sceneView.removeChild(it) }

        // Cargar el nuevo modelo
        val modelPath = when (modelName) {
            "facultadCienciasAdministrativas" -> "models/ciencias_administrativas.glb"
            "facultadEsfot" -> "models/esfot.glb"
            "facultadPetroleos" -> "models/petroleos.glb"
            "facultadQuimica" -> "models/quimica.glb"
            "facultadSistemas" -> "models/sistemas.glb"
            else -> {
                // Mostrar mensaje de error si el QR no es válido
                Toast.makeText(this, "❌ Modelo no encontrado para: $modelName", Toast.LENGTH_SHORT).show()
                return // Salir de la función sin cargar ningún modelo
            }
        }

        // Crea el modelo y lo agrega a la escena
        val newModelNode = createModel(modelPath, 1f, Position(0f, 0f, -1f))
        sceneView.addChild(newModelNode)
        currentModelNode = newModelNode
        Toast.makeText(this, "✅ Modelo encontrado: $modelName", Toast.LENGTH_SHORT).show()
    }

    // Función auxiliar para crear y configurar modelos 3D
    private fun createModel(path: String, scale: Float, position: Position): ArModelNode {
        return ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
            loadModelGlbAsync(glbFileLocation = path, scaleToUnits = scale, centerOrigin = position) {
                isVisible = true // Los modelos son visibles después de cargarse
                this.rotation = Position(0f, 0f, 0f)
            }
        }
    }

    // Inicia el escáner de código QR para detectar qué modelo mostrar
    private fun startQRCodeScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanee un código QR")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(false)
        integrator.initiateScan()
    }

    // Captura el resultado del escaneo de QR
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Escaneo cancelado", Toast.LENGTH_SHORT).show()
            } else {
                loadModel(result.contents) // Cargar el modelo según el QR escaneado

            }
        }
    }
}