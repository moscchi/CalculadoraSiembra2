package edu.neolab.CalculadorSiembra.entity;

public class VegetalFinal {
	private VegetalEntity vegetal;
	private int cantPlantas;
	private String fechaSiembra;
	
	
	public VegetalFinal(VegetalEntity vegetal, int cantPlantas, String fechaSiembra) {
		this.vegetal = vegetal;
		this.cantPlantas = cantPlantas;
		this.fechaSiembra = fechaSiembra;
	}
	public VegetalEntity getVegetal() {
		return vegetal;
	}
	public void setVegetal(VegetalEntity vegetal) {
		this.vegetal = vegetal;
	}
	public int getCantPlantas() {
		return cantPlantas;
	}
	public void setCantPlantas(int cantPlantas) {
		this.cantPlantas = cantPlantas;
	}
	public String getFechaSiembra() {
		return fechaSiembra;
	}
	public void setFechaSiembra(String fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}
	
	
}
