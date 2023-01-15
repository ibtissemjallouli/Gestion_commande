package tn.uma.isamm.spring.tp1.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.uma.isamm.spring.tp1.entities.Produit;

public interface ProduitDAO extends JpaRepository<Produit, Long> {
	public List<Produit> findByDesigProduitContaining(String mc);
}
