package org.bedu;

import java.util.List;
import java.util.Map;

public interface ConectorHttp {
    List<Map<String, Object>> consultarTodos();
    Map<String, Object> encontrarPorId(int id);
    Map<String, Object> editar(Map<String, Object> usuario);
    boolean eliminar(int id);
}
