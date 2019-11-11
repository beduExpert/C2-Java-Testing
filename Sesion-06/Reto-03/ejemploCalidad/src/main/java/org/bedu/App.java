package org.bedu;

import java.util.Arrays;

public class App 
{
    public static void main( String[] args )
    {
        String operacionARealizar = args[0];

        AdministradorUsuarios admin = new AdministradorUsuarios(new UsuarioRepositorio());
        admin.guardarUsuario("juanlopez", "jlopez@mail.com", "Usuario dummy para llenar la bd", Arrays.asList(new String[]{"amarillo"}));

        if(operacionARealizar == "agregar"){
            System.out.println("Agregando usuario a la bd");
            admin.guardarUsuario("mariaperez", "maria@mail.com", "Usuario dummy para llenar la bd", Arrays.asList(new String[]{"rosa"}));
        }
        else if(operacionARealizar == "editar"){

        }
        else if(operacionARealizar == "eliminar"){
            admin.eliminarUsuario(2);
        }
    }
}
