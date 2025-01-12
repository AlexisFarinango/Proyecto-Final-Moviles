package com.xperiencelabs.arapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import com.google.ar.core.Config
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position
import io.github.sceneview.node.VideoNode

class SecondActivity : AppCompatActivity() {

    private lateinit var sceneView: ArSceneView
    lateinit var placeButton: ExtendedFloatingActionButton
    private lateinit var modelNodeCarro: ArModelNode
    private lateinit var modelNodeAvion: ArModelNode
    private lateinit var modelNodeTanque: ArModelNode
    private lateinit var mediaPlayerCarro: MediaPlayer
    private lateinit var mediaPlayerAvion: MediaPlayer
    private lateinit var mediaPlayerTanque: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Inicializar la vista AR
        sceneView = findViewById<ArSceneView>(R.id.sceneView).apply {
            this.lightEstimationMode = Config.LightEstimationMode.DISABLED
        }

        // Inicializar los MediaPlayer para cada video
        mediaPlayerCarro = MediaPlayer.create(this, R.raw.ad)  // Cambiar con el video correcto
        mediaPlayerAvion = MediaPlayer.create(this, R.raw.ad)  // Cambiar con el video correcto
        mediaPlayerTanque = MediaPlayer.create(this, R.raw.ad) // Cambiar con el video correcto

        // Configurar el botón para colocar los modelos
        placeButton = findViewById(R.id.place)
        placeButton.setOnClickListener {
            placeModel()
        }

        // Crear el VideoNode para el carro
        val videoNodeCarro = VideoNode(
            sceneView.engine,
            scaleToUnits = 0.4f,
            centerOrigin = Position(x = 0f, y = -4f, z = 0f),  // Posicionar el carro en (0, -4, 0)
            glbFileLocation = "models/carro.glb",
            player = mediaPlayerCarro,
            onLoaded = { _, _ -> mediaPlayerCarro.start() }
        )

        // Crear el VideoNode para el avión
        val videoNodeAvion = VideoNode(
            sceneView.engine,
            scaleToUnits = 1.5f,
            centerOrigin = Position(x = 2f, y = -4f, z = 0f),  // Posicionar el avión en (2, -4, 0)
            glbFileLocation = "models/avion.glb",
            player = mediaPlayerAvion,
            onLoaded = { _, _ -> mediaPlayerAvion.start() }
        )

        // Crear el VideoNode para el tanque
        val videoNodeTanque = VideoNode(
            sceneView.engine,
            scaleToUnits = 1f,
            centerOrigin = Position(x = -2f, y = -4f, z = 0f),  // Posicionar el tanque en (-2, -4, 0)
            glbFileLocation = "models/tanque.glb",
            player = mediaPlayerTanque,
            onLoaded = { _, _ -> mediaPlayerTanque.start() }
        )

        // Crear el ArModelNode para el carro
        modelNodeCarro = ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/carro.glb",
                scaleToUnits = 0.4f,
                centerOrigin = Position(x = 0f, y = -4f, z = 0f)
            ) {
                sceneView.planeRenderer.isVisible = true
            }
        }

        // Crear el ArModelNode para el avión
        modelNodeAvion = ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/avion.glb",
                scaleToUnits = 1.5f,
                centerOrigin = Position(x = 2f, y = -4f, z = 0f)
            ) {
                sceneView.planeRenderer.isVisible = true
            }
        }

        // Crear el ArModelNode para el tanque
        modelNodeTanque = ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/tanque.glb",
                scaleToUnits = 1f,
                centerOrigin = Position(x = -2f, y = -4f, z = 0f)
            ) {
                sceneView.planeRenderer.isVisible = true
            }
        }

        // Agregar los modelos al SceneView
        sceneView.addChild(modelNodeCarro)
        sceneView.addChild(modelNodeAvion)
        sceneView.addChild(modelNodeTanque)

        // Agregar los VideoNodes como hijos de sus respectivos modelos
        modelNodeCarro.addChild(videoNodeCarro)
        modelNodeAvion.addChild(videoNodeAvion)
        modelNodeTanque.addChild(videoNodeTanque)
    }

    // Función para colocar el modelo en el mundo AR
    private fun placeModel() {
        // Coloca los modelos en el mundo AR
        modelNodeCarro.anchor()
        modelNodeAvion.anchor()
        modelNodeTanque.anchor()

        sceneView.planeRenderer.isVisible = false
    }

    override fun onPause() {
        super.onPause()
        // Detener los MediaPlayers cuando la actividad se pausa
        mediaPlayerCarro.stop()
        mediaPlayerAvion.stop()
        mediaPlayerTanque.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Liberar los recursos de los MediaPlayers cuando la actividad se destruya
        mediaPlayerCarro.release()
        mediaPlayerAvion.release()
        mediaPlayerTanque.release()
    }
}
