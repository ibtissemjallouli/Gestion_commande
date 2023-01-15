package tn.uma.isamm.spring.tp1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.uma.isamm.spring.tp1.entities.Commande;
import tn.uma.isamm.spring.tp1.entities.LigneCommande;
import tn.uma.isamm.spring.tp1.entities.PK_PROD_CMD;
import tn.uma.isamm.spring.tp1.entities.Produit;

public interface LigneCommandeDAO extends JpaRepository<LigneCommande, PK_PROD_CMD>{

}
