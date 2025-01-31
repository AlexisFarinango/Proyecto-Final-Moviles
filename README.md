# Realidad Aumentada con Scanner QR

DESARROLLO 

Dependencias utilizadas 

 

El proyecto usa varias dependencias en el archivo build.gradle, las cuales permiten integrar diferentes funcionalidades. Cada dependencia corresponde a una librería específica que se usa en el desarrollo de la aplicación. A continuación, se listan las principales dependencias y su propósito: 

ZXing (com.journeyapps:zxing-android-embedded:4.3.0) → Para escaneo de códigos QR. 

AndroidX Core KTX (androidx.core:core-ktx:1.7.0) → Extensiones de Kotlin que facilitan el desarrollo. 

AndroidX AppCompat (androidx.appcompat:appcompat:1.6.1) → Compatibilidad con versiones antiguas de Android. 

Material Components (com.google.android.material:material:1.8.0) → Implementación de Material Design. 

ConstraintLayout (androidx.constraintlayout:constraintlayout:2.1.4) → Herramienta para mejorar la disposición de los elementos en la UI. 

JUnit (junit:junit:4.13.2) → Para pruebas unitarias. 

AndroidX Test JUnit (androidx.test.ext:junit:1.1.5) → Para pruebas instrumentadas en Android. 

Espresso (androidx.test.espresso:espresso-core:3.5.1) → Para pruebas automatizadas de interfaz de usuario. 

SceneView (io.github.sceneview:arsceneview:0.10.0) → Para renderizar objetos 3D y realidad aumentada. 

Fragmentos de código más relevante 

 

Define un contenedor principal para la pantalla con un diseño vertical. 

Centrado y con fondo blanco. 

La propiedad tools:context=".MainActivity" indica que este XML pertenece a la actividad MainActivity. 

 

 

Muestra un párrafo de texto con información centrada. 

Tiene un id="infoText", lo que indica que se puede modificar dinámicamente desde el código Kotlin. 

layout_width="280dp" limita su ancho para que no ocupe toda la pantalla. 

 

 

 

Es un botón de Material Design que, al presionarlo, probablemente navega a una vista de Realidad Aumentada (AR). 

Tiene un id="navigateButton" para interactuar con él desde el código Kotlin. 

 

 

 Funcionamiento  

Una vez culminada la parte de desarrollo y codificación de la aplicación, se procede al despliege y a la instalación de esta misma para poder probar su funcionalidad. 

Una vez instalada se nos presentara la pantalla principal de nuestra aplicación llamada ARApp donde tenemos una interfaz con paleta de colores azules y con dos botones, uno para escanear llamado “empezar” y el otro para listar las facultades de la Politécnica llamado “Facultades”. 

 

 

 

 

Si aplastamos el botón de Facultades nos llevara a una siguiente pantalla donde podemos visualizar la lista de facultades con su respectivo icono, en este caso, nuestra App consta de 5 Facultades que son: “Escuela de Formación de Tecnólogos”, ”Facultad de Química”, “Facultad de Ciencias Administrativas”, “Facultad de Sistemas” y “Facultad de Petroleos”. 

 

Si pulsamos en cada una de estas facultades nos redirigirá a su debida página web de cada una. 

                 

Si regresamos a la página principal y pulsamos el botón empezar, nos llevara a una pantalla donde podremos escanear un código Qr con el boton “escanear”. 

 

Si escaneas un Qr cualquiera no nos dejara, y nos arrojara un mensaje que el Qr escaneado no consta en los modelos de nuestra App. 

