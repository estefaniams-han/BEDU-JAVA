package com.bedu.inventario;

import jakarta.persistence.*;

@Entity
public class Producto {

    @Id // Campo que funcionará como clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID se generará automáticamente (autoincremental)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "categoria_id") // nombre de la columna FK en la tabla productos
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "marca_id") // nombre de la columna FK en la tabla productos
    private Marca marca;

    // Campos que serán columnas en la tabla 'producto'
    private String nombre;
    private String descripcion;
    private double precio;


    protected Producto() {} // Constructor por defecto requerido por JPA

    // Constructor público para crear objetos de tipo Producto con los campos necesarios
    public Producto(String nombre, String descripcion, double precio, Categoria categoria, Marca marca) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
    }

    // Getters para acceder a los atributos (necesarios para el funcionamiento de JPA y buenas prácticas)
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public Categoria getCategoria() {
        return categoria;
    }
    public Marca getMarca() {
        return marca;
    }

    // Método que permite imprimir el objeto de forma legible (útil para logs o consola)
    @Override
    public String toString() {
        return String.format(
                "Producto[id=%d, nombre='%s', precio=%.2f, categoria='%s']",
                id, nombre, precio, categoria != null ? categoria.getNombre() : "Sin categoría"
        );
    }
}