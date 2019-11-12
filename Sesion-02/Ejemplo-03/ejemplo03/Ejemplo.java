public class Ejemplo{

  public static void main (String... args){
   while (x < 100) {
  		if (a[x] % 2 == 0) {
    			paridad = 0;
  		}
  		else {
    			paridad = 1;
  		}
  
  		switch(paridad) {
    			case 0:
      				println("a[" + x + "] es par");
      				break;
    			case 1:
      				println("a[" + x + "] es impar");
      				break;
    			default:
      				println("Error inesperado");
       
  		}
  
  		x++;
	}

	p = true;
  }
}