## Reto 02: Corregir los defectos encontrados por PMD

### Objetivo
- Basarse en los resultados reportados por una herramienta de análisis estático de código para realizar las correcciones necesarias al código fuente.
- Encontrar de manera práctica problemas comunes en el diseño de código y cómo pueden mitigarse.

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. Completar el reto 01 (Analizar los resultados de un código con problemas de calidad)

### Desarrollo
Durante el desarrollo del reto anterior se encontraron algunos problemas en la aplicación administradora de usuarios. Para este reto tendrás que realizar la corrección de los problemas encontrados ayudándote del reporte de PMD.

<details>
	<summary>Solución</summary>
1. El primer problema encontrado consiste en la comparación de Strings usando el operador `==`, esto es un problema debido a que en Java solo se compara la referencia al String y no el contenido de éste. Para realizar una comparación correcta se debe emplear el método `string1.equals(string2)`.
    
2. Otro problema es el constante empleo de variables inútiles para almacenar un resultado que se va a retornar inmediatamente. Esto debe evitarse para no crear una variable que no vamos a utilizar.

3. Los bloques try/catch contienen varios problemas: empezando por hacer un catch de excepciones genéricas, que puede enmascarar problemas en la aplicación. El segundo problema es la existencia de bloques catch vacíos, esta práctica también es un problema porque puede hacer muy difícil encontrar problemas en la lógica de la aplicación.

4. Respecto a las sentencias if, éstas deben simplificarse evitando comparar valores booleanos con true y false, pues directamente proporcionan un valor de éstos. Debemos evitar también que el cuerpo de éstas esté vacío.

</details>
