package edu.neolab.CalculadorSiembra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.neolab.CalculadorSiembra.entity.VegetalEntity;

@Repository
public interface VegetalRepository extends JpaRepository<VegetalEntity, Integer>{
	//Metodo para traer 1 sola especie
	VegetalEntity findByEspecie(String especie);
	//Metodo para traer todas las especies
	List<VegetalEntity> findAll();
	//Metodo para traer por id
	VegetalEntity findById(int id);
}
