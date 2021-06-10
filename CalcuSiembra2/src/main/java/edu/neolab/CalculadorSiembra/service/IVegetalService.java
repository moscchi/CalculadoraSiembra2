package edu.neolab.CalculadorSiembra.service;

import org.springframework.http.ResponseEntity;

public interface IVegetalService {
	ResponseEntity<?> getCantidadPlantas(String especie, int ancho, int largo);

	ResponseEntity<?> getFechaSiembra(String especie);

	ResponseEntity<?> getResultado(String especie, int ancho, int largo);
}
