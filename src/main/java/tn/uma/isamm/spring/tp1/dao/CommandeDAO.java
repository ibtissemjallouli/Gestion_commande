package tn.uma.isamm.spring.tp1.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.uma.isamm.spring.tp1.entities.Commande;
import tn.uma.isamm.spring.tp1.entities.LigneCommande;
import tn.uma.isamm.spring.tp1.entities.Produit;

public interface CommandeDAO extends JpaRepository<Commande, Long>{
	public List<Commande> findByDateCommandeBetween(Date startDate, Date endDate);
	public List<LigneCommande> findBynumCommandeContaining(long c);
	public List<Commande> findByEtatContaining(String etat);
}
