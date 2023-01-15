package tn.uma.isamm.spring.tp1.metier;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.uma.isamm.spring.tp1.dao.AppRoleDAO;
import tn.uma.isamm.spring.tp1.dao.AppUserDAO;
import tn.uma.isamm.spring.tp1.dao.CategorieDAO;
import tn.uma.isamm.spring.tp1.dao.ClientDAO;
import tn.uma.isamm.spring.tp1.dao.CommandeDAO;
import tn.uma.isamm.spring.tp1.dao.LigneCommandeDAO;
import tn.uma.isamm.spring.tp1.dao.ProduitDAO;
import tn.uma.isamm.spring.tp1.entities.AppRole;
import tn.uma.isamm.spring.tp1.entities.AppUser;
import tn.uma.isamm.spring.tp1.entities.Categorie;
import tn.uma.isamm.spring.tp1.entities.Client;
import tn.uma.isamm.spring.tp1.entities.Commande;
import tn.uma.isamm.spring.tp1.entities.LigneCommande;
import tn.uma.isamm.spring.tp1.entities.Produit;

@Service
@Transactional
public class MetierVentesImpl implements MetierVentes {
	@Autowired
	ProduitDAO produitDAO;
	
	@Autowired
	CategorieDAO categorieDAO;
	
	@Autowired	
	AppUserDAO appUserDAO;
	
	@Autowired
	AppRoleDAO appRoleDAO;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	CommandeDAO commandeDAO;
	
	@Autowired
	LigneCommandeDAO ligneCommandeDAO;
	
	@Autowired
	ClientDAO clientDAO;
	
	@Override
	public List<Produit> getProduits() {
		// TODO Auto-generated method stub
		return produitDAO.findAll();
	}

	@Override
	public List<Commande> getCommandes() {
		// TODO Auto-generated method stub
		return commandeDAO.findAll();
	}
	
		
	@Override
	public void saveProduit(Produit p) {
		// TODO Auto-generated method stub
		produitDAO.save(p);

	}
	
	@Override
	public Commande saveCommande(Commande c) {
		// TODO Auto-generated method stub
		return commandeDAO.save(c);

	}
	@Override
	public void saveLigneCommande(LigneCommande lc) {
		// TODO Auto-generated method stub
		ligneCommandeDAO.save(lc);

	}
	
	@Override
	public List<Client> getClient(){
		return clientDAO.findAll();
	}
	
	
	
	@Override
	public List<Commande> getCommandesFitrer(String f){
		return commandeDAO.findByEtatContaining(f);
	}

	@Override
	public Page<Produit> getProduitsPageable(int page, int size) {
		// TODO Auto-generated method stub
		PageRequest pr = PageRequest.of(page, size);
		return produitDAO.findAll(pr);
	}
	
	@Override
	public Page<Commande> getCommandesPageable(int page, int size) {
		// TODO Auto-generated method stub
		PageRequest pr = PageRequest.of(page, size);
		return commandeDAO.findAll(pr);
	}

//	@Override
//	public Page<Produit> getProduitsPageableByDesignation(int page, int size, String mc) {
//		// TODO Auto-generated method stub
//		return produitDAO.findByDesigProduitContaining(page, size, mc);
//	}
	
	@Override
	public List<Commande> findByDateCommande (Date startdate, Date endDate){
		return commandeDAO.findByDateCommandeBetween(startdate, endDate);
	}

	@Override
	public Produit getProduitById(long id) {
		// TODO Auto-generated method stub
		Optional<Produit> p = produitDAO.findById(id);
		if (p.isPresent())
			return p.get();
		else
			return null;
	}
	
	public Commande getCommandeById(long id){
		// TODO Auto-generated method stub
		Optional<Commande> c = commandeDAO.findById(id);
		if (c.isPresent())
			return c.get();
		else
			return null;
	}


	@Override
	public List<Categorie> getCategories() {
		// TODO Auto-generated method stub
		return categorieDAO.findAll();
	}

	@Override
	public void deleteProduit(Long id) {
		// TODO Auto-generated method stub
		produitDAO.deleteById(id);
	}
	@Override
	public void deleteCommande(Long id) {
		// TODO Auto-generated method stub
		commandeDAO.deleteById(id);
	}

	@Override
	public AppUser saveAppUser(AppUser appUser) {
		// TODO Auto-generated method stub
		String mdp = appUser.getPassword();
		appUser.setPassword(passwordEncoder.encode(mdp));
		return appUserDAO.save(appUser);
	}

	@Override
	public AppRole saveAppRole(AppRole appRole) {
		// TODO Auto-generated method stub
		return appRoleDAO.save(appRole);
	}

	@Override
	public void addRoleToUser(String login, String nomRole) {
		// TODO Auto-generated method stub
		AppUser user =appUserDAO.findByUsername(login);
		AppRole role =appRoleDAO.findByRoleName(nomRole);
		if(user!=null && role!=null)
			user.getRoles().add(role);
		
		
	}

	@Override
	public AppUser getUserByLogin(String login) {
		// TODO Auto-generated method stub
		return appUserDAO.findByUsername(login);
	}

	@Override
	public List<AppUser> getAppUsers() {
		// TODO Auto-generated method stub
		return appUserDAO.findAll();
	}
	
//	@Override
//	public List<LigneCommande> getLigneCommande(long c){
//		return ligneCommandeDAO.getLigneCommandeByIdCommande(c);
//	}
//	
//	

}
