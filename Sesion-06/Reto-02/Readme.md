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
Durante el desarrollo del reto anterior se encontraron algunos problemas en la aplicación administradora de usuarios, estos problemas se enlistan a continuación y el objetivo de este reto consiste en corregirlos en el código de la aplicación.
Finalmente, hay que ejecutar nuevamente el análisis mediante PMD y verificar que se hayan corregido dichos problemas.

 - Existen clases cuyos atributos no tienen ningún descriptor de su nivel de acceso. Esto puede causar problemas de encapsulamiento y debe evitarse.
 - En diferentes puntos del código se crea una variable para retornar su valor en la línea siguiente. Esto es considerado un desperdicio porque estamos almacenando un valor que ya no será accedido dentro de ese método.
 - La ley de Demeter se refiere a problemas con el encapsulamiento de nuestras clases, porque en lugar de pedir a la clase a la que hace referencia la nuestra que realice alguna operación, nos estamos comunicando con alguno de los miembros internos de ésta, de los cuales desconocemos el estado y comportamiento.
 - El uso de literales en condicionales es también un problema porque dificulta la mantenibilidad del código, además de dificultar su lectura al no saber de manera sencilla qué significan estos valores.
 - En algunos puntos del código se comparan los valores de Strings mediante los operadores `==` y `!=` en lugar de hacerlo con el método `equals`. Esto es un problema porque con los operadores solo verificamos que ambos String sean el mismo objeto en memoria, mas no que su contenido sea el mismo.
 - El uso de System.out.println para informar del estado de nuestra aplicación es una mala práctica porque no se pueden inspeccionar los mensajes de error o estado, cosa que se podría hacer si se empleara algún log.
 - La existencia de sentencias if vacías pues son instrucciones que no realizan ninguna operación.
 - La existencia de POJOs o clases que representan información vacías sin la encapsulación necesaria de sus propiedades.
 - La omisión de llaves `{}` en sentencias if es también un problema que puede causar bugs en nuestra aplicación.


<details>
	<summary>Solución</summary>
	
  1. El primer problema que corregiremos será agregar descriptores de acceso a los atributos de las clases de nuestra aplicación. Las clases donde tenemos que realizar estas correcciones son: AdministradorUsuarios y UsuarioRepositorio, cuyos atributos pueden ser marcados con la palabra clave `private`.
	
 2. El siguiente problema es la creación de variables innecesarias para retornar su valor, esto ocurre en AdministradorUsuarios. Para corregir este problema, eliminaremos las variables llamadas `tmp` y las sustituiremos por un `return` del valor que se almacenaba en ésta:
```java
//antes
Usuario tmp = repositorio.guardar(usuario);
//ahora
return repositorio.guardar(usuario);
```

 3. La corrección de problemas con la ley de Demeter nos indica que existen problemas en el diseño de las interacciones entre las clases que componen a nuestra aplicación. Para corregir el problema que tenemos en el método `editarUsuario` de AdministradorUsuarios, debemos realizar cambiar la implementación de este método para que reciba el objeto Usuario con las ediciones realizadas y reemplazarlo directamente en la base de datos.
 
```java
public usuario editarUsuario(Usuario nuevo){
	return repositorio.reemplazar(nuevo.getId(), nuevo);
}
```

 4. En la clase App, donde se encuentra el método `main` de nuestra aplicación, existen varias condiciones que comparan directamente el valor de entrada como argumento con un String hardcodeado, además de emplear los operadores de igualdad en lugar del método `equals`. La forma de corregir este problema será extrayendo los valores hardcodeados a constantes a nivel de clase y cambiando dichos operadores por el método equals:
 
```java
private static final String AGREGAR = "agregar";
    private static final String EDITAR = "editar";
    private static final String ELIMINAR = "eliminar";

    public static void main(String[] args )
    {
        String operacionARealizar = args[0];

        AdministradorUsuarios admin = new AdministradorUsuarios(new UsuarioRepositorio());
        admin.guardarUsuario("juanlopez", "jlopez@mail.com", "Usuario dummy para llenar la bd", Arrays.asList(new String[]{"amarillo"}));

        if(operacionARealizar.equals(AGREGAR)){
            System.out.println("Agregando usuario a la bd");
            admin.guardarUsuario("mariaperez", "maria@mail.com", "Usuario dummy para llenar la bd", Arrays.asList(new String[]{"rosa"}));
        }
        else if(operacionARealizar.equals(EDITAR)){

        }
        else if(operacionARealizar.equals(ELIMINAR)){
            admin.eliminarUsuario(2);
        }
    }
```

 5. Las sentencias if vacías deben eliminarse o agregar el código necesario para que sean útiles. Para el caso del método main, eliminaremos la sentencia para el caso de editar.

 6. La omisión de las llaves en sentencias if, while o for puede causar bugs en nuestra aplicación, pues un simple error de indentación puede cambiar el comportamiento esperado. En el caso de nuestra aplicación, esto debe corregirse en UsuarioRepositorio.

</details>
