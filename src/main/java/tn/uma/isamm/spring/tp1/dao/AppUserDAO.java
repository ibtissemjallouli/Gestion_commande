package tn.uma.isamm.spring.tp1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.uma.isamm.spring.tp1.entities.AppUser;

public interface AppUserDAO extends JpaRepository<AppUser, Long>{
	AppUser findByUsername(String login);

}
