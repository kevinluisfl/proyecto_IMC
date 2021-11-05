package com.ejemplo.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ejemplo.demo.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
	
	@Query(value = "SELECT * FROM persona p WHERE p.id = :id", nativeQuery = true)
	public List<Persona> findAdminById(Long id);
	
	
//
//	@Query("SELECT p FROM Persona p WHERE p.id = :id")
//	public List<Persona> findAdminQueryById(Long id);
	
}
