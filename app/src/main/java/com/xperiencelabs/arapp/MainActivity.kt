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

//class MainActivity : AppCompatActivity() {
//
//    private lateinit var sceneView: ArSceneView
//    private lateinit var placeButton: ExtendedFloatingActionButton
//    private lateinit var modelNodeCarro: ArModelNode
//    private lateinit var modelNodeAvion: ArModelNode
//    private lateinit var modelNodeTanque: ArModelNode
//    private lateinit var modelNodeEdificio: ArModelNode
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Inicializa la vista de la escena AR
//        sceneView = findViewById<ArSceneView>(R.id.sceneView).apply {
//            this.lightEstimationMode = Config.LightEstimationMode.DISABLED
//        }
//
//        // Configura el botón flotante para iniciar el escaneo de QR
//        placeButton = findViewById(R.id.place)
//        placeButton.setOnClickListener {
//            startQRCodeScanner()
//        }
//
//        // Inicializa los modelos en la escena
//        initializeModels()
//    }
//
//    // Función para inicializar y cargar los modelos 3D en la escena
//    private fun initializeModels() {
//
//        try {
//            modelNodeCarro = createModel("models/carro.glb", 1f, Position(0f, 0f, -1f))
//            modelNodeAvion = createModel("models/avion.glb", 1f, Position(0f, 0f, -1f))
//            modelNodeTanque = createModel("models/tanque.glb", 1f, Position(0f, 0f, -1f))
//            modelNodeEdificio = createModel("models/plano2.glb", 1f, Position(0f, 0f, -1f))
//
//            // Agrega los modelos a la escena AR
//            sceneView.addChild(modelNodeCarro)
//            sceneView.addChild(modelNodeAvion)
//            sceneView.addChild(modelNodeTanque)
//            sceneView.addChild(modelNodeEdificio)
//        } catch (e: Exception) {
//            Toast.makeText(this, "Error cargando modelos: ${e.message}", Toast.LENGTH_LONG).show()
//        }
//    }
//
//    // Función auxiliar para crear y configurar modelos 3D
//    private fun createModel(path: String, scale: Float, position: Position): ArModelNode {
//        return ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
//            loadModelGlbAsync(glbFileLocation = path, scaleToUnits = scale, centerOrigin = position) {
//                isVisible = false // Se ocultan inicialmente hasta ser activados
//            }
//        }
//    }
//
//    // Inicia el escáner de código QR para detectar qué modelo mostrar
//    private fun startQRCodeScanner() {
//        val integrator = IntentIntegrator(this)
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
//        integrator.setPrompt("Escanee un código QR")
//        integrator.setCameraId(0)
//        integrator.setBeepEnabled(false)
//        integrator.initiateScan()
//    }
//
//    // Captura el resultado del escaneo de QR
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
//
//        if (result != null) {
//            if (result.contents == null) {
//                Toast.makeText(this, "Escaneo cancelado", Toast.LENGTH_SHORT).show()
//            } else {
//                loadModelBasedOnQRCode(result.contents)
//            }
//        }
//    }
//
//    // Muestra el modelo correspondiente según el código QR escaneado
//    private fun loadModelBasedOnQRCode(qrContent: String) {
//        // Oculta todos los modelos antes de mostrar el seleccionado
//        modelNodeCarro.isVisible = false
//        modelNodeAvion.isVisible = false
//        modelNodeTanque.isVisible = false
//        modelNodeEdificio.isVisible = false
//
//
//
//
//        // Activa el modelo basado en el contenido del QR
//        when (qrContent) {
//            "carro" -> modelNodeCarro.isVisible = true
//            "avion" -> modelNodeAvion.isVisible = true
//            "tanque" -> modelNodeTanque.isVisible = true
//            "edificio" -> modelNodeEdificio.isVisible = true
//            else -> Toast.makeText(this, "Modelo no encontrado", Toast.LENGTH_SHORT).show()
//        }
//
//    }
//
//
//
//}


class MainActivity : AppCompatActivity() {

    private lateinit var sceneView: ArSceneView
    private lateinit var placeButton: ExtendedFloatingActionButton
    private var currentModelNode: ArModelNode? = null // Para hacer un seguimiento del modelo actual

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            "carro" -> "models/carro.glb"
            "avion" -> "models/avion.glb"
            "tanque" -> "models/tanque.glb"
            "edificio2" -> "models/plano2.glb"
            "edificio" -> "models/sofa.glb"
            else -> return
        }

        // Crea el modelo y lo agrega a la escena
        val newModelNode = createModel(modelPath, 1f, Position(0f, 0f, -1f))
        sceneView.addChild(newModelNode)
        currentModelNode = newModelNode
    }

    // Función auxiliar para crear y configurar modelos 3D
    private fun createModel(path: String, scale: Float, position: Position): ArModelNode {
        return ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
            loadModelGlbAsync(glbFileLocation = path, scaleToUnits = scale, centerOrigin = position) {
                isVisible = true // Los modelos son visibles inmediatamente después de cargarse
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

