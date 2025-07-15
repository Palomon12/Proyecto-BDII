package com.mycompany.dao;

import java.util.List;

public interface CrudDAO<T, ID> {

    void insertar(T entidad);

    void actualizar(T entidad);

    void eliminar(ID id);

    T obtenerPorId(ID id);

    List<T> obtenerTodos();
}
