## Ejemplo 03: Problemas comunes

### Objetivo
- Reconocer por medio de ejemplos algunos de los problemas más comunes que afectan la calidad del código en Java y cómo evitarlos.

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia

### Desarrollo
En este ejemplo revisaremos algunos de los problemas que afectan a la calidad del código de una aplicación junto con una demostración con código y una explicación de por qué es un problema.

1. Variables no utilizadas: la declaración de una variable que después no se utiliza se considera _código muerto_, pues solo estamos realizando una instrucción que no será de utilidad para nuestro programa. Otro caso ocurre cuando declaramos una variable simplemente para retornar su valor en la siguiente instrucción:
```java
public void variableInutil(){
	int a = 0;
    hacerOtraCosa();
}
public int retornarValor(){
	int promedio = calcularSuma() / this.numElementos;
    return promedio;
}
```

2. Bloques try/catch/finally vacíos: Son una fuente de posibles bugs difíciles de encontrar, pues simplemente ocultan un posible problema y no realizan ninguna acción para mitigarlo o reportarlo:
```java
public void guardarArchivo(Reporte reporte){
	try{
    	archivo.escribir(reporte);
    } catch(FileNotFoundException ex){}
}
```

3. Código duplicado: es un problema porque estamos repitiendo una implementación previamente realizada, además de complicar posibles cambios porque se tiene que buscar cada una de estas implementaciones y corregirla en caso de ser necesario.
4. Código no optimizado: un ejemplo de esto es el crear demasiados objetos no necesarios, como cuando no empleamos la clase StringBuilder cuando sea necesario concatenar mucho texto y terminamos generando miles de Strings para retornar uno solo:
```java
public String incorrecto(){
	String resultado = "";
    for(int i = 0; i < 10000; i++){
    	resultado += String.valueOf(i);
        resultado += ", ";
    }
    return resultado;
}
public String correcto(){
	StringBuilder resultado = new StringBuilder();
    for(int i = 0; i < 10000; i++){
    	resultado.append(i);
        resultado.append(", ");
    }
    return resultado.toString();
}
```

5. Variables con nombres no descriptivos, muy largos o muy cortos: dificulan la legilibilidad del código porque no explican su funcionalidad y es necesario seguir sus usos para identificar para qué existen. Esto aplica también para nombres de métodos y clases:
```java
public void metodo(int variable1, int v2, int variableCreadaParaDemostrarQueLosNombresLargosSonIgualDeMalosQueLosMuyCortos){
	this.calculo = variable1 / v2;
    this.calculo += variableCreadaParaDemostrarQueLosNombresLargosSonIgualDeMalosQueLosMuyCortos / 2;
}
```

6. No emplear correctamente los operadores del lenguaje: En Java es un error común de principiantes el tratar de comparar objetos como Strings mediante los operadores `==` y `!=` en lugar del método `equals`, siendo que estos operadores solo comparan que ambos objetos correspondan a la misma referencia y no a que su contenido sea el mismo:
```java
public boolean incorrecto(String str1, String str2){
	return str1 == str2;
}
public boolean correcto(String str1, String str2){
	return str1.equals(str2);
}
```

7. Uso de literales en condicionales y como parámetros de configuración: es preferible emplear variables o constantes a nivel de clase para definir un valor importante que describa el comportamiento del flujo de nuestra aplicación, pues además de hacer más legible el código, permite facilitar los cambios en caso de ser necesarios:
```java
public void verificarValoresMal(int valor){
	if(valor == 10){
    	hacerAlgo();
    }
}
public void verificarValoresBien(int valor){
	if(valor == LIMITE_SOLICITUDES){
    	hacerAlgo();
    }
}
```

8. Empleao de comparaciones innecesarias: en el caso de valores booleanos se complica la legilibildad del código al agregar el operador `==` y el valor `true` o `false`:
```java
public boolean compararMal(){
	if(bandera == true){
    	return true;
    }
    else{
    	return false;
    }
}
public boolean compararBien(){
	return bandera;
}
```

9. Tratar de tener solo un punto de retorno para cada método: el que un método pueda terminar en diferentes lugares del código impacta la legilibildad, además de complicar el análisis del flujo que éste lleva:
```java
public String saludarMal(int x){
	if(x > 0){
    	return "hey";
    }
    return "hi";
}
public String saludarBien(int x){
	String saludo = "hi";
    if(x > 0){
    	saludo = "hey";
    }
    return saludo;
}
```

10. Atrapar excepciones genéricas en lugar de tipos específicos: si primero hacemos catch a una excepción más específica, esto evitará que se ejecuten las sentencias catch para los tipos que heredan de ésta, ocultando problemas o simplemente evitando la ejecución del flujo como lo esperamos:
```java
public void castEntero(String entrada){
	int valor;
    try{
    	valor = Integer.parseInt(entrada);
    } catch(Exception e){
    	log.error(e);
    } catch(NumberFormatException nfe){
    	log.error(e);//nunca se ejecutará
    }
}
```
