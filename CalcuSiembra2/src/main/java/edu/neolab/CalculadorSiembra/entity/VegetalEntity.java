package edu.neolab.CalculadorSiembra.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="especie")
public class VegetalEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String especie;
	
	@Column(name="distancia_plantas")
	private int distanciaPlantasCM;
	
	@Column(name="fecha_plantacion")
	private String fechaPlantacion;
	
	@Column(name="dias_a_cosecha")
	private int diasCosecha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getDistanciaPlantasCM() {
		return distanciaPlantasCM;
	}

	public void setDistanciaPlantasCM(int distanciaPlantasCM) {
		this.distanciaPlantasCM = distanciaPlantasCM;
	}

	

	public String getFechaPlantacion() {
		return fechaPlantacion;
	}

	public void setFechaPlantacion(String fechaPlantacion) {
		this.fechaPlantacion = fechaPlantacion;
	}

	public int getDiasCosecha() {
		return diasCosecha;
	}
	
	public void setDiasCosecha(int diasCosecha) {
		this.diasCosecha = diasCosecha;
	}
	//El método se creó de manera "manual". Es decir, sabiendo como funcionaba cada especie.
	public String fechaSiembra(String especie){
		//Creamos una instancia de hoy, para calcular si es posible sembrar o no.
		LocalDate hoy = LocalDate.now();
		//Creamos el formato de la fecha
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		//Imprimimos la respuesta + Le aplicamos el formato del año a la variable de hoy.
		String respuesta = "Puedes plantar. Tendras fecha estimada de cosecha para el " + hoy.plusDays(this.diasCosecha).format(formato);
		//Usamos un switch para calcular segun la fecha de hoy si es posible o no plantar. 
		switch(this.especie) {
			case "Acelga":
				if((!hoy.getMonth().equals("JANUARY")) && (!hoy.getMonth().equals("JULY"))) {
					return respuesta;
				} else {
					return "La especie consultada está fuera de calendario";
				}
			case "Ajo":
				if(!hoy.getMonth().equals("MARCH") && !hoy.getMonth().equals("APRIL") && !hoy.getMonth().equals("MAY")) {
					return "La especie consultada está fuera de calendario";
				} else {
					return respuesta;
				}
			case "Apio":
				if(hoy.getMonth().equals("JUNE")) {
					return respuesta;
				} else {
					return "La especie consultada está fuera de calendario";
				}
			case "Esparragos"://Si fuese java 14 le meto, "Papa" aca ya q son iguales
				if(!hoy.getMonth().equals("AUGUST") && !hoy.getMonth().equals("SEPTEMBER")) {
					return "La especie consultada está fuera de calendario";
				} else {
					return respuesta;
				}
			case "Espinacas":
				if(!hoy.getMonth().equals("JANUARY") && !hoy.getMonth().equals("JULY") && !hoy.getMonth().equals("AUGUST") && !hoy.getMonth().equals("SEPTEMBER") && !hoy.getMonth().equals("OCTOBER") && !hoy.getMonth().equals("NOVEMBER") && !hoy.getMonth().equals("DECEMBER")) {
					return respuesta;
				} else {
					return "La especie consultada está fuera de calendario";
				}
			case "Lechuga":
				if(!hoy.getMonth().equals("AUGUST") && !hoy.getMonth().equals("SEPTEMBER") && !hoy.getMonth().equals("OCTOBER") && !hoy.getMonth().equals("NOVEMBER") && !hoy.getMonth().equals("DECEMBER")) {
					return "La especie consultada está fuera de calendario";
				} else {
					return respuesta;
				}
			case "Papa":
				if(!hoy.getMonth().equals("AUGUST") && !hoy.getMonth().equals("SEPTEMBER")) {
					return "La especie consultada está fuera de calendario";
				} else {
					return respuesta;
				}
			case "Pimiento":
				if(hoy.getMonth().equals("SEPTEMBER")) {
					return respuesta;
				} else {
					return "La especie consultada está fuera de calendario";
				}
			case "Rabanito":
				if(!hoy.getMonth().equals("JANUARY") && !hoy.getMonth().equals("JULY") && !hoy.getMonth().equals("AUGUST")) {
					return respuesta;
				} else {
					return "La especie consultada está fuera de calendario";
				}
			case "Remolacha":
				if(!hoy.getMonth().equals("JANUARY") && !hoy.getMonth().equals("FEBRUARY") && !hoy.getMonth().equals("JULY")) {
					return respuesta;
				} else {
					return "La especie consultada está fuera de calendario";
				}
				//Validacion por si nos ingresan cualquier especie.
			default:
				return "La especie ingresada no se encuentra en nuestro listado";
		}
	}

	@Override
	public String toString() {
		return "Vegetal [id=" + id + ", especie=" + especie + ", distanciaPlantasCM=" + distanciaPlantasCM
				+ ", fechaPlantacion (SusMeses)=" + fechaPlantacion + ", diasCosecha=" + diasCosecha + "]";
	}
		
}
