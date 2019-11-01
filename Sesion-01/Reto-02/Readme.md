## Reto 02: Uso de los métodos grafos Causa-Efecto y tablas de decisión para casos de prueba

### OBJETIVO 

- Aplicar dos métodos combinados para diseñar casos de prueba.

#### DESARROLLO

Lee los requerimientos para calcular primas en seguros de autos, mostrados a continuación:

    - Para mujeres de menos de 65 años, la prima es de $500
    - Para hombres de menos de 25 años, la prima es de $3000
    - Para hombres entre 25 y 64 años, la prima es de $1000
    - Para cualquiera de mas de 65 años, la prima es de $1500

<details>

<summary>Solucion</summary>
1. Identifica las causas y los efectos efectos

![imagen](img/figura_01.png)

2. Elabora los grafos correspondientes:

![imagen](img/figura_02.png)

3. Coloca una restricción de una y solo una porque el sexo puede ser masculino o femenino pero no ambos:

![imagen](img/figura_03.png)

4. Elabora la tabla de decisión

![imagen](img/figura_04.png)

5. Finalmente, elabora los casos de prueba:

![imagen](img/figura_05.png)



</details> 




