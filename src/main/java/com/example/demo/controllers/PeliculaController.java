package com.example.demo.controllers;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Genero;
import com.example.demo.model.Pelicula;
import com.example.demo.service.GeneroService;
import com.example.demo.service.PeliculaService;

@Controller
@RequestMapping("/pelicula")
public class PeliculaController {
	
	@Autowired
	private GeneroService generoService;
	
	@Autowired
	private PeliculaService peliculaService;
	
	@GetMapping("/peliculas")
	public String getAllPeliculas(Model model) {
		
		List<Pelicula> listPeli = peliculaService.getAllPeliculas();
		
		model.addAttribute("pelis", listPeli);
		
		
		return "listPeliculas";
	}
	
	@GetMapping("/register")
	public String Register(Model model) {
		List<Genero> generos = generoService.getAllGeneros();
	    model.addAttribute("generos", generos);
		
		return "peliculaRegister";
	}
	
	@PostMapping("/register")
	public String createPelicula(@RequestParam("name") String nombre, @RequestParam("director") String director,@RequestParam("fechaEstreno") String fechaEstreno,@RequestParam("id") Long id, Model model) {
		
		
		
		Pelicula pelicula = new Pelicula();
		pelicula.nombre=nombre;
		pelicula.director=director;
		pelicula.fechaEstreno= fechaEstreno;
		
		Genero genero = generoService.getGeneroById(id);
		
		pelicula.genero=genero;
		
		
		peliculaService.createPelicula(pelicula);
		
		model.addAttribute("pelis", peliculaService.getAllPeliculas());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "listPeliculas";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		Pelicula pelicula = peliculaService.getPeliculaById(id);
		
		model.addAttribute("pelis", pelicula);
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "peliculaEdit";
	}
	
	@PostMapping("/edit")
	public String createPelicula1(@RequestParam("name") String nombre, @RequestParam("director") String director,@RequestParam("fechaEstreno") String fechaEstreno,@RequestParam("id") Long id, Model model) {
		
		
		
		Pelicula pelicula = peliculaService.getPeliculaById(id);
		pelicula.nombre=nombre;
		pelicula.director=director;
		pelicula.fechaEstreno= fechaEstreno;
		
		Genero genero = generoService.getGeneroById(id);
		
		pelicula.genero=genero;
		
		
		peliculaService.createPelicula(pelicula);
		
		model.addAttribute("pelis", peliculaService.getAllPeliculas());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "listPeliculas";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id, Model model) {
		peliculaService.deletePelicula(id);
		
		model.addAttribute("pelis", peliculaService.getAllPeliculas());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "listPeliculas";
	}
	

}
