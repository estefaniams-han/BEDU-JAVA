package com.bedu.inventario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductoRepository productoRepo, CategoriaRepository categoriaRepo, MarcaRepository marcaRepo) {
        return (args) -> {
            Categoria tecnologia = new Categoria("Tecnología");
            categoriaRepo.save(tecnologia);

            Marca asus = new Marca("ASUS");
            marcaRepo.save(asus);

            Marca msi = new Marca("MSI");
            marcaRepo.save(msi);

            Marca lenovo = new Marca("Lenovo");
            marcaRepo.save(lenovo);

            Marca apple = new Marca("Apple");
            marcaRepo.save(apple);

            productoRepo.save(new Producto("Laptop ASUS ROG Strix SCAR 18", "Intel Core i9, RTX 5090", 90000.00, tecnologia, asus));
            productoRepo.save(new Producto("Laptop MSI Titan 18 HX", "Intel Core i9, RTX 4090", 140000.00, tecnologia, msi));
            productoRepo.save(new Producto("Tablet Lenovo", "Pantalla 10 pulgadas", 7800.00, tecnologia, lenovo));
            productoRepo.save(new Producto("iPhone 16 Pro Max", "iPhone con tecnología de punta", 32000.00, tecnologia, apple));


            System.out.println("📂 Productos registrados:");
            productoRepo.findAll().forEach(p -> System.out.println(p.getNombre() + " - " + p.getCategoria().getNombre()));

            System.out.println("📚 Productos por marca:");
            marcaRepo.findAll().forEach(marca -> {
                System.out.println("🏷️ " + marca.getNombre() + ":");
                productoRepo.findAll().stream()
                        .filter(p -> p.getMarca().getId().equals(marca.getId()))
                        .forEach(p -> System.out.println("   - " + p.getNombre()));
            });
        };
    }
}

/*
* @ManyToOne se usa cuando varios productos pertenecen a una misma categoría.
* @JoinColumn permite personalizar el nombre de la columna de la clave foránea.
* Spring Boot se encarga de crear automáticamente las tablas si ddl-auto=update.
* */