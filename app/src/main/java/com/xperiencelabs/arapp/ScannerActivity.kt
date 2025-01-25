//package com.xperiencelabs.arapp
//
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.pdf.PdfRenderer
//import android.net.Uri
//import android.os.Bundle
//import android.os.ParcelFileDescriptor
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.ar.core.HitResult
//import com.google.ar.sceneform.AnchorNode
//import com.google.ar.sceneform.Node
//import com.google.ar.sceneform.rendering.MaterialFactory
//import com.google.ar.sceneform.rendering.ShapeFactory
//import com.google.ar.sceneform.rendering.Texture
//import com.google.ar.sceneform.ux.ArFragment
//import com.google.ar.sceneform.math.Vector3
//import com.google.zxing.integration.android.IntentIntegrator
//import java.io.File
//
//class ScannerActivity : AppCompatActivity() {
//
//    private lateinit var arFragment: ArFragment
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_scanner) // Asegúrate de que activity_scanner.xml tenga un fragmento AR
//
//        // Configurar el fragmento de AR
//        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
//
//        // Mostrar el escáner en pantalla vertical
//        requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//
//        // Iniciar el escáner automáticamente
//        initScanner()
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
//
//        if (result != null && result.contents != null) {
//            val scannedContent = result.contents
//
//            if (scannedContent.startsWith("http") && scannedContent.contains(".pdf")) {
//                // Si contiene ".pdf", intenta descargarlo y renderizarlo en AR
//                downloadAndRenderPDF(scannedContent)
//            } else {
//                Toast.makeText(this, "El código QR no contiene un PDF válido.", Toast.LENGTH_LONG).show()
//            }
//        } else {
//            Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_LONG).show()
//            super.onActivityResult(requestCode, resultCode, data)
//        }
//    }
//
//    private fun initScanner() {
//        try {
//            val integrator = IntentIntegrator(this)
//            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
//            integrator.setPrompt("Escanea un código QR")
//            integrator.setCameraId(0) // Usar la cámara trasera
//            integrator.setBeepEnabled(false)
//            integrator.setBarcodeImageEnabled(true)
//
//            // Iniciar el escáner en modo vertical
//            integrator.captureActivity = VerticalCaptureActivity::class.java
//
//            // Iniciar el escáner
//            integrator.initiateScan()
//        } catch (e: Exception) {
//            println("Excepción en el escáner: $e")
//            Toast.makeText(this, "Error al iniciar el escáner", Toast.LENGTH_LONG).show()
//        }
//    }
//
//    private fun downloadAndRenderPDF(pdfUrl: String) {
//        // Aquí puedes implementar la lógica para descargar el PDF. Para este ejemplo,
//        // asumimos que el PDF ya está descargado y se encuentra en el almacenamiento local.
//        val localPdfPath = "/path/to/downloaded/pdf" // Cambia esto según tu lógica
//
//        try {
//            val bitmap = renderPdfToBitmap(localPdfPath)
//
//            // Configurar el listener para tocar un plano AR
//            arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
//                create3DObjectWithPDF(hitResult, bitmap)
//            }
//        } catch (e: Exception) {
//            Toast.makeText(this, "Error al procesar el PDF", Toast.LENGTH_LONG).show()
//        }
//    }
//
//    private fun renderPdfToBitmap(pdfPath: String): Bitmap {
//        val fileDescriptor = ParcelFileDescriptor.open(File(pdfPath), ParcelFileDescriptor.MODE_READ_ONLY)
//        val renderer = PdfRenderer(fileDescriptor)
//        val page = renderer.openPage(0) // Renderiza la primera página del PDF
//        val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
//        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
//        page.close()
//        renderer.close()
//        return bitmap
//    }
//
//    private fun create3DObjectWithPDF(hitResult: HitResult, bitmap: Bitmap) {
//        // Usa Sceneform para crear un objeto plano y renderizar el PDF como textura
//        Texture.builder().setSource(bitmap).build().thenAccept { texture ->
//            MaterialFactory.makeTransparentWithTexture(this, texture)
//                .thenAccept { material ->
//                    val renderable = ShapeFactory.makePlane(
//                        Vector3(1.0f, 1.0f, 1.0f), // Dimensiones del plano
//                        Vector3.zero(),            // Posición inicial
//                        material
//                    )
//
//                    // Colocar el objeto en el plano detectado
//                    val anchor = hitResult.createAnchor()
//                    val anchorNode = AnchorNode(anchor)
//                    anchorNode.setParent(arFragment.arSceneView.scene)
//
//                    val node = Node()
//                    node.setParent(anchorNode)
//                    node.renderable = renderable
//                }
//        }
//    }
//}
//
