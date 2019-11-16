## Postwork 07: Análisis de código usando PMD

### OBJETIVO

- Analizar el código de la aplicación usando PMD para mejorar la calidad del código de tu proyecto personal.
- Aprender a interpretar los reportes de calidad de código para realizar correcciones de forma periódica y reducir la deuda técnica.
- Aprender cuáles son algunos de los errores más comunes que cometemos para aprender la manera de corregirlos y no volver a repetirlos.

#### REQUISITOS

1. Instalar y configurar PMD como se vió en el Ejemplo 01.
2. Descargar el código del proyecto del repositorio, o haber concluido el postwork de la sesión anterior.

#### DESARROLLO

1. Una vez que tienes el código debes ejecutar un reporte de PMD. Exiten varias formas y formatos para generar este reporte. Si ejecutas el comando **pmd** en una consola obtendrás la lista de los parámetros y opciones soportados por esta herramienta:

![imagen](/img/figura_01.png)

2. En la terminal, cambia de ubicación al directorio *src\main\java* de tu proyecto. Es dentro de este directorio que está el código de la aplicación que será analizado por pmd.

        cd src\main\java

3. Ejecuta pmd con el siguiente comando. El cual generará un archivo con formato **html** con el reporte de los errores encontrados. Si deseas cambiar el formato de salida a texto, xml o algún otro soportado por *pmd* sólo debes modificar el valor del parámetro **-f**.

        pmd -d . -f html -R rulesets/java/quickstart.xml -r reporte.html
        
La instrucción anterior generará un reporte de salida llamado **reporte.html**, el cual tiene el siguiente contenido:

 ![imagen](/img/figura_02.png)
 
 En donde vemos que el proyecto tiene 17 problemas que pueden ser corregidos para aumentar la calidad de la aplicación.
 
 Cada fila del reporte anterior tiene la siguiente estructura:
 
 - Identificador de la fila
 - Archivo que contiene el problema
 - Número de línea dentro del archivo anterior que contiene el problema.
 - Liga al sitio de PMD que contiene la descripción del problema y consejos para solucionarlo
 
  ![imagen](/img/figura_03.png)
  
  Reaizaremos la resolución de algunos de los problemas mencionados por PMD, pero queda de tu lado el solucionar todos los problemas.
  
4. Para solucionar el primer problema seguiremos el consejo dado por PMD y colocaremos un comentario en el construtor indicando que se ha dejado en blanco de forma intencional:

```java
	protected Asistencia() {
		//Este constructor está intencionalmente vacío. 
	}
```

La misma solución se puede aplicar para los casos 3, 6, 8 y 11 en las clases *Castigo*, *HorarioEntrada*, *Sugerencia* y *Usuario* respectivamente:

```java
	protected Castigo() {
		// Este constructor está intencionalmente vacío.
	}
        
        protected HorarioEntrada() {
		//Este constructor está intencionalmente vacío. 
	}
        
        protected Sugerencia() {
		//Este constructor está intencionalmente vacío. 
	}
        
        protected Usuario() {
		//Este constructor está intencionalmente vacío. 
	}
```

5. El segundo problema indica que se deben evitar las sentencias **if..then..else** innecesarias. El método que causa esto es **equals** dentro de la clase **Asistencia**. Este método tiene el siguiente código:

```java
        @Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Asistencia other = (Asistencia) obj;
		if (this.id != other.id) {
			return false;
		}
		if (this.fechaLogueo != other.fechaLogueo 
				&& (this.fechaLogueo == null || !this.fechaLogueo.equals(other.fechaLogueo))) {
			return false;
		}
		return true;
	}
```
El bloque anterior contiene muchas condiciones y en todas se regresa un valor *false*; si ninguna condición se cumple se regresa el valor *true*, podemos simplificar este código de la siguiente forma:

```java
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		final Asistencia other = (Asistencia) obj;
		
		return !(this.id != other.id || this.fechaLogueo != other.fechaLogueo  && (this.fechaLogueo == null || !this.fechaLogueo.equals(other.fechaLogueo)));
	}
```

Esta misma solución se puede aplicar al los problemas 4, 7, 9, 12 y 15 respectivamente.

En **Castigo.java**:

```java
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		final Castigo other = (Castigo) obj;
		
		return  !(this.id != other.id) || ( this.descripcion == null  ? (other.descripcion != null) : !this.descripcion.equals(other.descripcion));
	}
```

En **HorarioEntrada.java**:

```java
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		final HorarioEntrada other = (HorarioEntrada) obj;
		
		return  !(this.fechaInicio != other.fechaInicio
				&& (this.fechaInicio == null || !this.fechaInicio.equals(other.fechaInicio)));
	}
```

En **Sugerencia.java**:
```java
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final Sugerencia other = (Sugerencia) obj;
		
		return !((this.id != other.id) || this.sugerencia == null ? other.sugerencia != null: !this.sugerencia.equals(other.sugerencia));
	}
```

En **Usuario.java**:
```java
	@Override
	public boolean equals(Object obj) {
		if (obj == null  || getClass() != obj.getClass()) {
			return false;
		}
		
		final Usuario other = (Usuario) obj;
		
		return !((this.id != other.id) || this.email == null ? other.email != null : !this.email.equals(other.email));
	}

```

En **AperturaSistema.java**:
```java
	public boolean estaDisponible(Date HoraAcceso) throws SQLException {
		HorarioEntrada he = hedao.getUltimaFecha();
		
		return HoraAcceso.after(he.getFechaInicio());
	}
```

6. Para el problema 14 debemos modificiar el nombre del parámetro del método **estaDisponible**, este parámetro está declarado de la siguiente forma:

```java
        Date HoraAcceso
```

Hay que modificarlo para que quede de la siguiente forma:

```java
        Date horaAcceso
```

7. Corrige los dos últimos problemas siguiendo la guía proporcionada por PMD.

