package tn.uma.isamm.spring.tp1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.uma.isamm.spring.tp1.entities.AppRole;

public interface AppRoleDAO extends JpaRepository<AppRole, Long> {
	AppRole findByRoleName(String nomRole);
}
