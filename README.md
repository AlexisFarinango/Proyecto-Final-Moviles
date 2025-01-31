# Realidad Aumentada con Scanner QR

Silvia Chaluisa,
Dilan Bedoya, 
Alex Cardenas, 
Steven Castillo, 
Alexis Farinango, 
Lenin Gomez,
Kevin Cola y
Marcelo Pinzon 


DESARROLLO 

Dependencias utilizadas 

![image](https://github.com/user-attachments/assets/c9b6918f-e95e-4dd9-9dc9-c8c5164ab4b3)

 

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

 ![image](https://github.com/user-attachments/assets/52598ce5-4c4c-4bb3-916d-7a0cfbea74ec)


 

Muestra un párrafo de texto con información centrada. 

Tiene un id="infoText", lo que indica que se puede modificar dinámicamente desde el código Kotlin. 

layout_width="280dp" limita su ancho para que no ocupe toda la pantalla. 

![image](https://github.com/user-attachments/assets/4e3d42ad-61a8-475a-b2c4-fd7e1496ca6e)
 

 

 

Es un botón de Material Design que, al presionarlo, probablemente navega a una vista de Realidad Aumentada (AR). 

Tiene un id="navigateButton" para interactuar con él desde el código Kotlin. 

![image](https://github.com/user-attachments/assets/3425ee13-d3c4-4e26-b028-7ccef04cae65)

 

 

 Funcionamiento  

Una vez culminada la parte de desarrollo y codificación de la aplicación, se procede al despliege y a la instalación de esta misma para poder probar su funcionalidad. 

Una vez instalada se nos presentara la pantalla principal de nuestra aplicación llamada ARApp donde tenemos una interfaz con paleta de colores azules y con dos botones, uno para escanear llamado “empezar” y el otro para listar las facultades de la Politécnica llamado “Facultades”. 

 

 ![WhatsApp Image 2025-01-31 at 3 24 13 AM (1)](https://github.com/user-attachments/assets/c608dc86-8361-47c5-94c2-a2bc2e7b1def)


 

 

Si aplastamos el botón de Facultades nos llevara a una siguiente pantalla donde podemos visualizar la lista de facultades con su respectivo icono, en este caso, nuestra App consta de 5 Facultades que son: “Escuela de Formación de Tecnólogos”, ”Facultad de Química”, “Facultad de Ciencias Administrativas”, “Facultad de Sistemas” y “Facultad de Petroleos”. 

![WhatsApp Image 2025-01-31 at 3 29 20 AM (2)](https://github.com/user-attachments/assets/3a068bab-e5ba-4adf-9890-10b4816d11ed)


 

Si pulsamos en cada una de estas facultades nos redirigirá a su debida página web de cada una.


![WhatsApp Image 2025-01-31 at 3 35 35 AM](https://github.com/user-attachments/assets/af66368b-a9a6-4cd9-b598-143b7d16f720)



                 

Si regresamos a la página principal y pulsamos el botón empezar, nos llevara a una pantalla donde podremos escanear un código Qr con el boton “escanear”.

![WhatsApp Image 2025-01-31 at 3 41 05 AM](https://github.com/user-attachments/assets/d2c36234-765d-45c1-b8d0-457a154fcdff)


Aqui tenemos los siguientes Qr que cargamos como modelos para nuestra App. 

![WhatsApp Image 2025-01-31 at 7 33 02 AM](https://github.com/user-attachments/assets/0756ea0d-6de5-4b2e-9684-3df04513455b)

Al escanear nos dará una viste en 3D con la informacion de dicha facultad. 

![WhatsApp Image 2025-01-31 at 7 36 01 AM (1)](https://github.com/user-attachments/assets/ae598473-091d-4878-bef6-2d8a5f6c6196)








 

Si escaneas un Qr cualquiera no nos dejara, y nos arrojara un mensaje que el Qr escaneado no consta en los modelos de nuestra App. 

![WhatsApp Image 2025-01-31 at 3 48 31 AM](https://github.com/user-attachments/assets/2322ba74-3910-498e-8f20-61e701ca6172)


