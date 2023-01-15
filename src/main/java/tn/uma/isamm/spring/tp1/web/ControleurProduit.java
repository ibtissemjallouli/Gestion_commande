package tn.uma.isamm.spring.tp1.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tn.uma.isamm.spring.tp1.dao.ProduitDAO;
import tn.uma.isamm.spring.tp1.entities.Categorie;
import tn.uma.isamm.spring.tp1.entities.Produit;
import tn.uma.isamm.spring.tp1.entities.ProduitAlimentaire;
import tn.uma.isamm.spring.tp1.entities.ProduitCosmetique;
import tn.uma.isamm.spring.tp1.metier.MetierVentes;

@Controller
public class ControleurProduit {
	@Autowired
	private MetierVentes metierVentes;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/403")
	public String denied() {
		return "403";
	}
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/user/produits";
	}

	@RequestMapping("/user/produits")
	public String produits(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size,
			@RequestParam(name = "errorMessage", defaultValue = "") String errorMessage) {
		Page<Produit> listeProduits = metierVentes.getProduitsPageable(page, size);
//		if(page == listeProduits.getTotalPages()) {
//			page--;
//			listeProduits = metierVentes.getProduitsPageable(page, size);
//		}
		model.addAttribute("activePage", page);
		model.addAttribute("size", size);
		int[] taillePagination = IntStream.range(0, listeProduits.getTotalPages()).toArray();
		model.addAttribute("taillePagination", taillePagination);
		model.addAttribute("nbPages", listeProduits.getTotalPages());
		model.addAttribute("nbElements", listeProduits.getTotalElements());
		model.addAttribute("listeProduits", listeProduits);
		return "produits";
	}

	@PostMapping("/user/rechercheProduit")
	public String rechercheProduit(long id, Model model) {
		Produit produit = metierVentes.getProduitById(id);
		boolean etat = true;
		if (produit == null)
			etat = false;
		else {
			ArrayList<Produit> produits = new ArrayList<Produit>();
			produits.add(produit);
			model.addAttribute("activePage", 0);
			model.addAttribute("size", 2);
			model.addAttribute("taillePagination", 0);
			model.addAttribute("listeProduits", produits);
			model.addAttribute("etat", etat);			
		}
		return "produits";
	}
	
	@GetMapping("/admin/ajouterProduitAlimentaire")
	public String ajoutProduitAlimentaire(Model model) {		
			model.addAttribute("produit", new ProduitAlimentaire());	
			List<Categorie> listeCateg = metierVentes.getCategories();
			model.addAttribute("listeCategories", listeCateg);
			return "ajouterProduitAlimentaire";
	}
	
	@PostMapping("/admin/ajouterProduitAlimentaire")
	public String enregistrerProduitAlimentaire(ProduitAlimentaire pa, Model model) {		
			metierVentes.saveProduit(pa);
			return "redirect:/user/produits";
	}
	
	@GetMapping("/admin/ajouterProduitCosmetique")
	public String ajoutProduitCosmetique(Model model) {		
			model.addAttribute("produit", new ProduitCosmetique());	
			List<Categorie> listeCateg = metierVentes.getCategories();
			model.addAttribute("listeCategories", listeCateg);
			return "ajouterProduitCosmetique";
	}
	
	@PostMapping("/admin/ajouterProduitCosmetique")
	public String enregistrerProduitCosmetique(ProduitCosmetique pc, Model model) {		
			metierVentes.saveProduit(pc);
			return "redirect:/user/produits";
	}
	
	@GetMapping("/admin/supprimerProduit")
	public String supprimerProduit(Long id, Long activePage, Long nbElements, Long size, RedirectAttributes ra) {
		metierVentes.deleteProduit(id);
		System.out.println(" ----"+activePage);
		if(activePage>0 && ((nbElements-1)==(size * (activePage))))
			activePage--;
		ra.addAttribute("page", activePage);
		return "redirect:/user/produits";
		
	}
	
	@GetMapping("/admin/modifierProduit")
	public String modifierProduit(@RequestParam(name="id")Long id, Model model) {
		List<Categorie> listeCateg = metierVentes.getCategories();
		model.addAttribute("listeCategories", listeCateg);		
		Produit produit = metierVentes.getProduitById(id);
		model.addAttribute("produit",produit);
		if(produit instanceof ProduitAlimentaire) {
			return "modifierProduitAlimentaire";
		}else {
			return "modifierProduitCosmetique";
		}		
	}
	
	@PostMapping("/admin/modifierProduitAlimentaire")
	public String modifierProduitAlimentaire(ProduitAlimentaire produit) {
		metierVentes.saveProduit(produit);
		return "redirect:/user/produits";
	}
	
	@PostMapping("/admin/modifierProduitCosmetique")
	public String modifierProduitCosmetique(ProduitCosmetique produit, Long id) {
		produit.setCodeProduit(id);
		metierVentes.saveProduit(produit);
		return "redirect:/user/produits";
	}
}
