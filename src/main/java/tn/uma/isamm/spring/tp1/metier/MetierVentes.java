package tn.uma.isamm.spring.tp1.metier;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import tn.uma.isamm.spring.tp1.entities.AppRole;
import tn.uma.isamm.spring.tp1.entities.AppUser;
import tn.uma.isamm.spring.tp1.entities.Categorie;
import tn.uma.isamm.spring.tp1.entities.Client;
import tn.uma.isamm.spring.tp1.entities.Commande;
import tn.uma.isamm.spring.tp1.entities.LigneCommande;
import tn.uma.isamm.spring.tp1.entities.Produit;

public interface MetierVentes {
	public List<Produit> getProduits();
	public void saveProduit(Produit p);
	public Produit getProduitById(long id);
	public Commande saveCommande(Commande c);
	public Page<Produit> getProduitsPageable(int page, int size);
	public List<Categorie> getCategories();
	public void deleteProduit(Long id);
	public void deleteCommande(Long id);
	public AppUser saveAppUser(AppUser appUser);
	public AppRole saveAppRole(AppRole appRole);
	public void addRoleToUser(String login, String nomRole);
	public AppUser getUserByLogin(String login);
	public List<AppUser> getAppUsers();
	public List<Client> getClient();
	public void saveLigneCommande(LigneCommande lc);
	public List<Commande> getCommandes();
	public Commande getCommandeById(long id);
	public Page<Commande> getCommandesPageable(int page, int size);
	public List<Commande> findByDateCommande(Date startDate,Date endDate);
	public List<Commande> getCommandesFitrer(String f);
//	public List<LigneCommande> findBycommandeId(long c);
	
	//Page<Produit> getProduitsPageableByDesignation(int page, int size, String mc);
	

	

}
