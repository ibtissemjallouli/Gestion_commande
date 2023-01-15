package tn.uma.isamm.spring.tp1.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.uma.isamm.spring.tp1.entities.AppUser;
import tn.uma.isamm.spring.tp1.entities.Produit;
import tn.uma.isamm.spring.tp1.metier.MetierVentes;

@RestController
@EnableAutoConfiguration
@RequestMapping("/produit")
public class ControleurRest {
	@Autowired
	MetierVentes metier;
	
	@GetMapping("/test")
	@ResponseBody
	public List<AppUser> testerProduits(){
		return metier.getAppUsers();
	}
	
	@GetMapping()
	@ResponseBody
	public List<Produit> listerProduits(){
		return metier.getProduits();
	}
	
	@DeleteMapping("/{id}")	
	public String supprimerProduit(@PathVariable(name="id")long id){
		Produit p = metier.getProduitById(id);
		if(p==null)
			return "Produit introuvable !!!";
		else {
			metier.deleteProduit(id);
			return " produit supprimé !!!";
		}
	}			
	
	@GetMapping("/{id}")
	public Produit chercherProduit(@PathVariable(name="id")long id){
		Produit p = metier.getProduitById(id);
		return p;
		//return " le produit recherché est : "+p+" il a le code "+id;
	}	
	
	@PostMapping()	
	public String ajouterProduit(@RequestBody Produit p){
		 metier.saveProduit(p);
		 return "produit enregistré!!!";
	}
	
	@PutMapping()	
	public String modifierProduit(@RequestBody Produit p){
		Produit  ancienProd = metier.getProduitById(p.getCodeProduit());
		if(ancienProd==null)
			return "produit introuvable :(";
		else {
			metier.deleteProduit(ancienProd.getCodeProduit());
			metier.saveProduit(p);
			return "produit modifié :)";
		}		 
	}
}
