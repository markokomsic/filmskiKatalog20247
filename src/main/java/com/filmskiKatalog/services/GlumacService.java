package com.filmskiKatalog.services;

import com.filmskiKatalog.models.Glumac;
import com.filmskiKatalog.repositories.GlumacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GlumacService {
    @Autowired
    private GlumacRepository glumacRepository;

    public List<Glumac> findAllGlumci() {
        return glumacRepository.findAll();
    }

    public Optional<Glumac> findGlumacById(Long id) {
        return glumacRepository.findById(id);
    }

    public Glumac saveGlumac(Glumac glumac) {
        return glumacRepository.save(glumac);
    }

    public void deleteGlumac(Long id) {
        glumacRepository.deleteById(id);
    }

    // Dodajte dodatne metode prema potrebi
}