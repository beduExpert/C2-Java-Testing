## Reto 01: Analizar los resultados de un código con problemas de calidad

### Objetivo
- Famliarizarse con el empleo de herramientas de análisis estático de código para encontrar problemas comunes en el diseño de código.
- Interpretar los resultados obtenidos por esta herramienta para identificar en qué consiste cada problema encontrado.

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven

### Desarrollo
Se ha desarrollado una pequeña aplicación para administrar un grupo de usuarios en una base de datos en memoria, pero no se han seguido buenas prácticas durante su desarrollo y hay problemas con el código fuente que pueden afectar la funcionalidad.
1. Dentro de la carpeta **ejemploCalidad** se encuentra el proyecto de Maven configurado con varias reglas de estilo y buenas prácticas provistas por PMD, sin embargo no se han seguido al momento de escribir el código.
2. Este reto consiste en ejecutar un análisis mediante la herramienta PMD como un objetivo de Maven , encontrar el archivo con los resultados del examen e interpretar en qué consiste cada uno de los problemas, además de leer el vínculo donde se describe más a detalle y se proporciona un ejemplo.

Recuerda que un análisis de PMD se ejecutará posicionándose en consola en la carpeta raíz del proyecto y ejecutando el comando `mvn pmd:pmd`. PMD realizará el análisis y escribirá los resultados en el archivo **pmd.xml** dentro de la carpeta target.
Este reporte nos dará una breve guía de qué problemas de estilo o posibles bugs existen en nuestra aplicación, así como un link a la documentación donde se muestra un ejemplo y la descripción del problema.

<details>
	<summary>Solución</summary>
    1. Debido a que ya se ha incluido la configuración en el archivo **pom.xml** para usar PMD y se ha incluido un conjunto de reglas para decirle qué revisar, solo es necesario ejecutar el objetivo de Maven.

    2. Para realizar el análisis de PMD se emplea el comando `mvn pmd:pmd`, que ejecuta el objetivo de Maven y genera un archivo xml con el resultado del análisis.

    3. Este archivo se encuentra en la ruta `target/pmd.xml` y lista cada uno de los problemas encontrados y su ubicación en el código.

    4. Algunos de los problemas principales en este código consisten en los siguientes:

- Existen clases cuyos atributos no tienen ningún descriptor de su nivel de acceso. Esto puede causar problemas de encapsulamiento y debe evitarse.

- En diferentes puntos del código se crea una variable para retornar su valor en la línea siguiente. Esto es considerado un desperdicio porque estamos almacenando un valor que ya no será accedido dentro de ese método.

- La ley de Demeter se refiere a problemas con el encapsulamiento de nuestras clases, porque en lugar de pedir a la clase a la que hace referencia la nuestra que realice alguna operación, nos estamos comunicando con alguno de los miembros internos de ésta, de los cuales desconocemos el estado y comportamiento.

- El uso de literales en condicionales es también un problema porque dificulta la mantenibilidad del código, además de dificultar su lectura al no saber de manera sencilla qué significan estos valores.

- En algunos puntos del código se comparan los valores de Strings mediante los operadores `==` y `!=` en lugar de hacerlo con el método `equals`. Esto es un problema porque con los operadores solo verificamos que ambos String sean el mismo objeto en memoria, mas no que su contenido sea el mismo.

- El uso de *System.out.println* para informar del estado de nuestra aplicación es una mala práctica porque no se pueden inspeccionar los mensajes de error o estado, cosa que se podría hacer si se empleara algún log.

- La existencia de sentencias if vacías pues son instrucciones que no realizan ninguna operación.
- La existencia de POJOs o clases que representan información vacías sin la encapsulación necesaria de sus propiedades.

- La omisión de llaves `{}` en sentencias if es también un problema que puede causar bugs en nuestra aplicación.

</details>
