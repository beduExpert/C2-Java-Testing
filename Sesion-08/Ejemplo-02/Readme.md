## Ejemplo 02: Modificación de Quality Profile

### Desarrollo 

1. Entra a SonarQube como administrador. (Usa _admin_ para usuario y contraseña)
   ![Login](img/figura01.png)
1. Entra a Quality Profiles
   ![Quality Profiles](img/figura02.png)
1. Busca la sección de Java y copia el perfil _Sonar way_. Nombralo _bedu_
   ![Copy profile](img/figura03.png)
1. Esto te llevará a la pantalla de administración del nuevo perfil. En la izquierda hay un panel con las reglas, da click sobre el 36 correspondiente a _Vulnerabilities_
   ![Profile page](img/figura04.png)
1. Busca la regla _Persistent entities should not be used as arguments of "@RequestMapping" mehotds_ y desactívala **Desactivar reglas no es algo que se deba hacer a la ligera. Esto se hace con fines méramente educativos**
   ![Profile page](img/figura05.png)
1. Vuelve a entrar a la sección _Quality Profiles_ y establece el perfil _bedu_ como el default.
   ![Profile page](img/figura06.png)
1. Ejecuta una vez más el análisis de sonarqube como lo hiciste en el Ejercicio 1 
  ```bash
  sonar-scanner
  ```
##### Resultado original
  ![Resultado de análisis original](img/figura07.png)

##### Nuevo resultado
  ![Resultado de análisis nuevo](img/figura08.png)

  Como puedes ver en las dos imágenes anteriores hay una disminución en el número de vulnerabilidades por la modificación que hicimos al perfil.


### Modificación de Quality Gate

1. Entra a Quality Gates
   ![Quality Gates](img/figura09.png)
1. Copia el perfil de _Sonar way_ y llámalo _bedu QG_
   ![Copiar Quality Gate](img/figura10.png)
1. Agrega una nueva condición con los siguientes valores: Overall Code, Skipped Unit Test is greater than 0
   ![Modificar Quality Gate](img/figura11.png)
1. En la parte inferior (_Projects_) selecciona _Without_ para ver los proyectos que no usan ese perfil y selecciona el proyecto de la unidad.
   ![Asigna Quality Gate](img/figura12.png)
1. Ejecuta una vez más el análisis de sonarqube como lo hiciste en el Ejercicio 1
   ![Prueba fallida](img/figura13.png)

Esta vez la comprobación de calidad ha fallado ya que existe una prueba que está deshabilitada.

