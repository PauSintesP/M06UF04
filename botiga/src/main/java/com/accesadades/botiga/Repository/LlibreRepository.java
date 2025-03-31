package com.accesadades.botiga.Repository;

import com.accesadades.botiga.Model.Llibre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LlibreRepository extends JpaRepository<Llibre, Long> {
    boolean existsByIsbn(String isbn);
    List<Llibre> findAll();
    Llibre findByTitol(String titol);
    List<Llibre> findByTitolAndEditorial(String titol, String editorial);
}