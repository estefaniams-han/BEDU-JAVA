package com.bedu.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreContaining(String nombre);

    List<Producto> findByPrecioGreaterThan(double precio);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByPrecioBetween(double min, double max);
    List<Producto> findByNombreStartingWithIgnoreCase(String prefijo);
}

/*
* Este archivo es una interfaz que hereda de JpaRepository. Gracias a eso podemos hacer cosas como:
* 1. Guardar en la base de datos
* 2. Devolver todos los productos
* 3. Buscar por nombre parcial
* */
