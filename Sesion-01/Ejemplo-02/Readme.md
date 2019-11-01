
## Ejemplo 02: Creación de tabla de decisión

### OBJETIVO

- Definir casos de prueba útiles y que generan valor aplicando el método de la tabla de decisión.

#### DESARROLLO

1. Lee la especificación de los requisitos para aplicar descuento al pagar con tarjeta:

        Se quiere determinar el importe a facturar a los clientes de unos grandes almacenes según estos criterios:
        - Si pagan con tarjeta oro tendrán un 15% de descuento.
        - Si pagan con tarjeta club tendrán un 5% de descuento.
        - Si la tarjeta (oro o club) es modalidad joven, tendrán un 5% de descuento.
        - Los descuentos son acumulables.

2. Construir la tabla de decisión asociada a este planteamiento. Recuerda que las siguientes son las reglas para crear la matriz de decisión:

 - Listar todas las variables y las distintas condiciones a incluir en la tabla de decisión: en el ejemplo podríamos decir que son los distintos tipos de tarjetas con los que puedes pagar (oro, club, modalidad joven). 
 
 - Calcular la cantidad de combinaciones posibles, y con eso definimos cuántas columnas debemos designar. En este caso el número de combinaciones es 2 ^ 3 (dos elevado a la tercera potencia). Esto es porque existen tres tipos de tarjeta con las que se pueden pagar, y cada tipo de tarketa solo tiene dos valores posibles de uso: se usa esa tarjeta para pagar y NO se usa esa tarjeta para pagar. Esto quiere decir que hay 8 combinaciones posibles.
 
 - Agregar las acciones a la tabla: Las reglas de negocio indican qué acciones se disparan para cada combinación de datos de entrada, con lo cual para cada posible combinación deberemos analizar las reglas manualmente y determinar la salida esperada. En este reto, las acciones posibles son los descuentos que pueden aplicarse, o la inviabilidad de que ocurra esa situación; por ejemplo: una persona no puede pagar con más de una tarjeta a la vez.

 - Verificar la cobertura de las combinaciones, asegurando que con las combinaciones que vamos a seleccionar estaremos cubriendo el total del espacio de valores.


![imagen](img/figura_01.png)

En la tabla anterior, las combinaciones 1 y 2 no son viables, ya que no es posible pagar con dos tarjetas al mismo tiempo. La combinación 5 es inviable porque si no se paga con ninguna tajeta, no es posible que la tarjeta tenga la modalidad joven.

Se agregó una quinta regla, en la que se indica si se permite calcular el importe. Podemos ver que en general si es posile realizar un pago también se puedde calcular el importe.


3. Simplificar la tabla anterior, eliminando los casos inviables. Esto es porque si sabemos que un caso no es viable de aparecer, no tiene mucho sentido aplicar dicha prueba.

![imagen](img/figura_02.png)

En la imagen anterior podemos ver que hemos eliminado los casos inviables, ya que estos no tiene sentido probarlos si nunca se aplicarán.
