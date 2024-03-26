package com.example.ynovv.service.impl;

import com.example.ynovv.entity.Genre;
import com.example.ynovv.repository.GenreRepository;
import com.example.ynovv.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre not found with id " + id));
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre update(Long id, Genre genreDetails) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id " + id));
        genre.setName(genreDetails.getName());
        genre.setBooks(genreDetails.getBooks());
    return genreRepository.save(genre);
    }

    @Override
    public void deleteById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id " + id));
        genreRepository.delete(genre);
    }
}
