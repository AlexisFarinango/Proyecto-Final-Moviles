package com.xperiencelabs.arapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Encuentra el botón por su ID
        val button = findViewById<ExtendedFloatingActionButton>(R.id.place)

        // Configura el OnClickListener para el botón
        button.setOnClickListener {
            // Crea un Intent para iniciar SecondActivity
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}