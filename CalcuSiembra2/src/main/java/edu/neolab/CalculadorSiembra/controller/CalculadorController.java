package edu.neolab.CalculadorSiembra.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;

import edu.neolab.CalculadorSiembra.entity.VegetalEntity;
import edu.neolab.CalculadorSiembra.repository.VegetalRepository;
import edu.neolab.CalculadorSiembra.service.VegetalService;

@CrossOrigin
@RestController
public class CalculadorController {
	
	//Clase controller. Al final está el metodo para acceder a la consigna que se nos pidio. Previamente estan los submetodos que los deje
	//por si querian chequearlos. 
	@Autowired
	private VegetalRepository repository;
	
	//Aca el metodo service
	@Autowired
	private VegetalService service;
	
	
	//Metodo para obtener todos los vegetales de la bd.
	@GetMapping("/getAllVegetales")
	public ResponseEntity<?> getAllVegetales(){
		try {
			return ResponseEntity.ok(this.repository.findAll());
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error.", HttpStatus.BAD_REQUEST);
		}
	}
	
	//Agrego metodo por id por que esta en la consigna, sin embargo dejo el metodo obtener por especie que habia realizado en la primer versiond el TP.
	@GetMapping("vegetalId/{id}")
	public ResponseEntity<?> getVegId(@PathVariable("id") int id)  {
		if (!this.repository.findById(id).equals("")) {
			return ResponseEntity.ok(this.repository.findById(id));
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
		
	//metodo para obtener 1 solo vegetal por especie
	@GetMapping("vegetal/{especie}")
	public ResponseEntity<?> getVegetal(@PathVariable("especie") String especie){
		if(especie != this.repository.findByEspecie(especie).getEspecie()) {
		try {
			return ResponseEntity.ok(this.repository.findByEspecie(especie));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//Metodo para obtener que cantidad de plantas entran a una superficie.
	@GetMapping("cantidad_plantas/especie={especie}&ancho={ancho}&largo={largo}")
	public ResponseEntity<?> getCantidad(@PathVariable("especie") String especie, @PathVariable("ancho") int ancho, @PathVariable("largo") int largo ){
		try {
			return ResponseEntity.ok(this.service.getCantidadPlantas(especie, ancho, largo));
		} catch (Exception e) {
			return new ResponseEntity<>("Datos ingresados incorrectos", HttpStatus.BAD_REQUEST);
		}
	}
	
	//Metodo para obtener la fecha de la siembra si es que es posible
	@GetMapping("fecha_siembra/{especie}")
	public ResponseEntity<?> getFechaSiembra(@PathVariable("especie") String especie){
		try {
			return ResponseEntity.ok(this.service.getFechaSiembra(especie));
		} catch (Exception e) {
			return new ResponseEntity<>("Datos ingresados incorrectos", HttpStatus.BAD_REQUEST);
		}
	}
	
	//Este es el que tiene la funcionalidad final. Lo que pedia la consigna, el resto está fragmentado en partes. 
	@GetMapping("siembraAPI/especie={especie}&ancho={ancho}&largo={largo}")
	public ResponseEntity<?> getFuncionalidad(@PathVariable("especie") String especie, @PathVariable("ancho") int ancho, @PathVariable("largo") int largo ) throws JsonProcessingException{
		try {
			return ResponseEntity.ok(this.service.getResultado(especie, ancho, largo));
		} catch (Exception e) {
			return new ResponseEntity<>("Datos ingresados incorrectos", HttpStatus.BAD_REQUEST);
		}
	}
}
