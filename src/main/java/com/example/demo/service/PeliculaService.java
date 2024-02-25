package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pelicula;

import com.example.demo.repository.PeliculaRepository;

@Service
public class PeliculaService {
	
	@Autowired
	private PeliculaRepository peliculaRepository;
	
	public List<Pelicula> getAllPeliculas() {
		return peliculaRepository.findAll();
	}
	
	public Pelicula createPelicula(Pelicula pelicula) {
		return peliculaRepository.save(pelicula);
	}
	
	public Pelicula getPeliculaById(long id) {
		return peliculaRepository.findById(id).orElse(null);
	}
	public void deletePelicula(Long id) {
		peliculaRepository.deleteById(id);
	}

}
