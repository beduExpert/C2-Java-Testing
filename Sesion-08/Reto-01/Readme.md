## Reto 01: Agregar sonar-scanner al PATH

### Objetivo
* Modificar nuestro sistema para tener disponible sonar-scanner sin tener que usar la ruta completa

### Requisitos

1. Haber terminado el Ejercicio-01.
1. Tener privilegios de adminsitrador en el equipo.

### Desarrollo

En el Ejercicio-01 debimos usar la ruta absoluta al archivo sonar-scanner.bat para iniciar el análisis del proyecto.

Si intentamos ejecutarlo de manera directa obtendremos un error.

  ![Error de path](img/figura01.png)

Para evitar esto, debemos agregar la ruta _cliente_base\bin\_ al PATH de nuestro sistema operativo.

<details>
  <summary>Solución</summary>

  <em>Estas instrucciones son específicas para Windows 10, en otras versiones puede cambiar la forma de llegar a la configuración del PATH</em>

  <ol>
      <li>Abre el menú de inicio y escribe <em>env</em></li>
         <img src="img/figura02.png" alt="Menú Inicio"/>
      <li>En la ventana de Propiedades del Sistema, abre las variables de entorno</li>
         <img src="(img/figura03.png" alt="Variables de entorno"/>
      <li>Selecciona el Path del usuario y editalo<em>(puedes hacerlo también en el Path del sistema, pero se recomienda hacerlo sólo en el propio)</em></li>
         <img alt="Path" src="img/figura04.png" />
      <li>Agrega la ruta equivalente a <em>cliente_base\bin</em></li>
         <img alt="Nueva ruta" src="img/figura03.png"/>
  </ol>

  <strong>La variable de entorno USERPROFILE almacena la ruta hasta el usuario de la sesión.</strong>

<p>
  Para verificar si lo hicíste corréctamente, inicia nuevamente el análisis, pero esta vez sólo con el siguiente comando
</p>

 <code>
     sonar-scanner
  </code>

<img alt="Llamada exitosa" src="img/figura06.png" />

<p>
Ten en cuenta que deberás abrir una nueva línea de comandos para que se cargue el nuevo PATH.

</p>
<p>
Si la línea de comandos te da un error diciendo que sonar-scanner no es un comando, es necesraio revisar los pasos anteriores.
</p>

</details>
