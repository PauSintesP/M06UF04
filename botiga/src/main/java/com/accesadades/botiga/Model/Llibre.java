package com.accesadades.botiga.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "books")
public class Llibre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_llibre")
    private Long idLlibre;
    
    @Column(nullable = false)
    private String titol;
    
    @Column(nullable = false)
    private String autor;
    
    @Column(nullable = false, unique = true, length = 255)
    private String isbn;
    
    @Column(name = "data_publicacio", nullable = false)
    private LocalDate dataPublicacio;
    
    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal preu;
    
    @Column(nullable = false)
    private String editorial;
}