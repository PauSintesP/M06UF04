package com.accesadades.botiga.Service;

import com.accesadades.botiga.Model.Llibre;
import java.util.List;
import java.util.Optional;

public interface LlibreService {
    List<Llibre> findAll();
    Llibre findByTitol(String titol);
    List<Llibre> findByTitolAndEditorial(String titol, String editorial);
    Optional<Llibre> findByIdLlibre(Long id);
    boolean validateISBN(String isbn);
    void save(Llibre llibre);
    boolean existsByIsbn(String isbn);
}