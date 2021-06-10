package edu.neolab.CalculadorSiembra.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.neolab.CalculadorSiembra.entity.VegetalEntity;
import edu.neolab.CalculadorSiembra.entity.VegetalFinal;
import edu.neolab.CalculadorSiembra.repository.VegetalRepository;

@Service
public class VegetalService implements IVegetalService {

	@Autowired
	private VegetalRepository repository;

	@Override
	public ResponseEntity<?> getCantidadPlantas(String especie, int ancho, int largo) {
		try {
			if ((ancho >= 0) && (largo >= 0)) {
				VegetalEntity vegetal = this.repository.findByEspecie(especie);
				int totalPlantas = ((ancho*100)*(largo*100))/(vegetal.getDistanciaPlantasCM()^ 2);
				return ResponseEntity.ok(totalPlantas);
			}
			return ResponseEntity.of(Optional.of("Se pudo matchear con la bd pero los valores ancho y largo que ingresa deben ser mayor que 0."));
		} catch (Exception e) {
			return new ResponseEntity<>("Valores ingresados no son válidos.", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getFechaSiembra(String especie) {
		try {
			VegetalEntity vegeFecha = this.repository.findByEspecie(especie);
			String fechaEstimada = vegeFecha.fechaSiembra(especie);
			return ResponseEntity.ok(fechaEstimada);
		} catch (Exception e) {
			return new ResponseEntity<>("Especie ingresada no es válida.", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getResultado(String especie, int ancho, int largo) {
		if ((ancho >= 0) && (largo >= 0)) {
			try {
				// Obtenemos el objeto
				VegetalEntity vegetal = this.repository.findByEspecie(especie);
				//Obtenemos el total de plantas
				int totalPlantas = ((ancho*100)*(largo*100))/(vegetal.getDistanciaPlantasCM()^ 2);
				// operamos con la fecha a ver si es posible sembrar o no.
				String fechaEstimada = vegetal.fechaSiembra(especie);
				//Creo una instancia de VegetalFinal para devolver lo solicitado en un JSON. (En un principio pensé devolver un String pero si quisiese utilizar la data no podría hacerlo sin antes operar sobre la misma.
				VegetalFinal vf = new VegetalFinal(vegetal, totalPlantas,  fechaEstimada);
				return ResponseEntity.ok(vf);
			} catch (Exception e) {
				return new ResponseEntity<>("Especie ingresada no es válida.", HttpStatus.BAD_REQUEST);
			}
		} else {
			// Se dispara cuando ancho o largo son menores a 0.
			return ResponseEntity.of(Optional.of("Se pudo matchear con la bd pero los valores ancho y largo que ingresa deben ser mayor que 0."));
		}
	}

}
