## Reto 03: Estimación de la deuda técnica

### Objetivo
- Basarse en los defectos de calidad encontrados en una aplicación para estimar el costo para pagar la deuda técnica asociada.

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. Completar el reto 01 (Analizar los resultados de un código con problemas de calidad)

### Introducción
Como ya se vio anteriormente, la deuda técnica se refiere a los diferentes defectos que pueden encontrarse en un proyecto y afectan el desarrollo del mismo por ser necesario corregirlos para poder seguir en su avance.
Dentro del ámbito del software podemos definir 4 tipos principales de deuda técnica:

- Deuda de diseño: que se refiere a la duplicidad del código, ausencia de patrones de diseño o la falta de seuimiento a estándares de codificación o buenas prácticas.
- Deuda en pruebas: como su nombre lo indica, se refiere a la ausencia de pruebas o a que éstas sean de mala calidad.
- Deuda en documentación: se puede referir tanto a a la falta de legilibilidad y orden en el código como a la falta de comentarios y documentación donde sea necesario: como para el uso de APIs, bibliotecas o fuentes que describan la funcionalidad del sistema.
- Deuda en defectos: se refiere a todos aquellos defectos que son conocidos y aún no se han arreglado porque son de baja prioridad, baja severidad o existe alguna alternativa para evitarlos.

Agrupados en esos 4 grupos principales, existen diferentes métodos para calcular según el tipo de defecto del que se trate. A saber:

- Duplicidad de código: se expresa como un porcentaje de la cantidad de líneas duplicadas entre el total de líneas del proyecto.
- Complejidad del código: es expresada comúnmente mediante la complejidad ciclomática (el número total de caminos diferentes en una unidad de código), definiendo los rangos siguientes: de 1 a 10 normal, de 11 a 20 moderado, de 21 a 50 riesgoso y más de 50 como no estable o no mantenible.
- Cobertura de pruebas: se calcula identificando tanto las líneas como los caminos que son y no son cubiertos por las diferentes pruebas y es expresada como un procentaje de éstas.
- Ciclos de dependencia y acoplamiento: se refieren al conteo de clases que dependen de alguna otra, así como si existe algún ciclo entre las diferentes dependencias (Clase A depende de la B, la B depende de la C y la C depende de A).

De esta manera, se forman reglas describiendo cada uno de los defectos (cosa con la que nos ha ayudado la herramienta de análisis de código) y se define el costo en horas necesarias para corregir cada uno de estos defectos. El paso final será realizar la suma de horas o días necesarios para corregir todos los problemas hallados.

### Desarrollo
Aclarado el método para realizar el cálculo de la deuda técnica, este reto consiste en guiarse en el reporte generado por PMD durante el Reto 1 para estimar la deuda técnica como horas necesarias para realizar la corrección de los defectos.
Se considerará que para cada observación encontrada se tendrá que invertir un total de 2 horas de trabajo para su corrección. Así, para estimar la deuda técnica total simplemente tendremos que realizar la suma de las horas y expresarla en días laborales (cada día laboral tiene 8 horas).
