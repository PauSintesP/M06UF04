package com.accesadades.botiga.Service;

import com.accesadades.botiga.Model.Llibre;
import com.accesadades.botiga.Repository.LlibreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LlibreServiceImpl implements LlibreService {

    @Autowired
    private LlibreRepository llibreRepository;

    @Override
    public List<Llibre> findAll() {
        return llibreRepository.findAll();
    }

    @Override
    public Llibre findByTitol(String titol) {
        return llibreRepository.findByTitol(titol);
    }

    @Override
    public List<Llibre> findByTitolAndEditorial(String titol, String editorial) {
        return llibreRepository.findByTitolAndEditorial(titol, editorial);
    }

    @Override
    public Optional<Llibre> findByIdLlibre(Long id) {
        return llibreRepository.findById(id);
    }

    @Override
    public boolean validateISBN(String isbn) {
        String cleanedISBN = isbn.replaceAll("-", "");
        return cleanedISBN.matches("^\\d{10}$") || cleanedISBN.matches("^\\d{13}$");
    }

    @Override
    public void save(Llibre llibre) {
        llibreRepository.save(llibre);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return llibreRepository.existsByIsbn(isbn);
    }
}