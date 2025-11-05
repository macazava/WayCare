package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaRepository {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria criar(Categoria categoria) {
        return categoriaRepository.seive(categoria);
    }

    private Categoria seive(Categoria categoria) {
        return null;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    private List<Categoria> findAll() {
        return null;
    }

    private class categoriaRepository {
        public List<Categoria> FindAll() {
            return null;
        }

        public Categoria seive(Categoria categoria) {
            return null;
        }
    }
}
