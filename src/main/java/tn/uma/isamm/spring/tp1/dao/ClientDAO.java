package tn.uma.isamm.spring.tp1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.uma.isamm.spring.tp1.entities.Client;

public interface ClientDAO extends JpaRepository<Client, Long>{
	
}
