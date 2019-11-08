## Reto 03: Agregar pruebas al factorial
### Objetivo
- Practicar la creación de suites de pruebas mediante la adición de casos adicionales al proyecto de factorial empleado en el ejemplo 3.

### Requisitos
1. JDK 8 o más reciente
2. IDE de tu preferencia
3. Apache Maven
4. TestNG
5. Ejemplo 03 (Factorial)

### Desarrollo
1. Como se vio en el ejemplo, las suites de pruebas permiten agrupar diferentes clases y métodos de acuerdo a criterios definidos por el desarrollador, facilitando el proceso de prueba de software.
2. El programa del ejemplo 3 sin embargo, aún está incompleto, pues no considera los casos de error para números negativos ni limita el número que puede ingresar el usuario, pues si ingresa un número mayor a 20 ocasionará un desbordamiento que retornará un valor negativo.
3. El reto consiste en agregar una suite de pruebas que verifique el programa lance una excepción para los casos descritos en el paso anterior, esta suite se llamará **Pruebas incorrectas** y debe ir acompañada del código necesario tanto en las pruebas como en la clase Factorial.

<details>
	<summary>Solución</summary>

1. Para agregar una nueva suite de pruebas, primero debemos agregar los métodos necesarios a FactorialTest, uno para probar valores negativos y otro para probar valores mayores a 20:
	
```java
    @Test(expectedExceptions = InvalidArgumentException.class)
    public void testFactorialMayor() {
        factorial.calcular(21);
    }

    @Test(expectedExceptions = InvalidArgumentException.class)
    public void testFactorialNegativo() {
        factorial.calcular(-1);
    }
```
2. Debemos crear también la suite de pruebas en **testng.xml**:

```xml
<test name="Valores inválidos" preserve-order="false" parallel="true">
    <classes>
        <class name="org.bedu.FactorialTest">
            <methods>
                <include name="testFactorialMayor"/>
                <include name="testFactorialNegativo"/>
            </methods>
        </class>
    </classes>
</test>
```
3. Finalmente, agregaremos la condición de error en la clase factorial

```java
	if(x > 20 || x < 0){
            throw new InvalidArgumentException(new String[]{"El valor de x no es válido"});
        }
```
</details>
