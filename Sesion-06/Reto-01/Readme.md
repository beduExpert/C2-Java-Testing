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
3. Pista: Recuerda el proceso seguido durante el Ejercicio 01.

<details>
	<summary>Solución</summary>
    1. Debido a que ya se ha incluido la configuración en el archivo **pom.xml** para usar PMD y se ha incluido un conjunto de reglas para decirle qué revisar, solo es necesario ejecutar el objetivo de Maven.
    2. Para realizar el análisis de PMD se emplea el comando `mvn pmd:pmd`, que ejecuta el objetivo de Maven y genera un archivo xml con el resultado del análisis.
    3. Este archivo se encuentra en la ruta `target/pmd.xml` y lista cada uno de los problemas encontrados y su ubicación en el código.

</details>
