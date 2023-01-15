package tn.uma.isamm.spring.tp1.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tn.uma.isamm.spring.tp1.dao.CommandeDAO;
import tn.uma.isamm.spring.tp1.entities.Categorie;
import tn.uma.isamm.spring.tp1.entities.Client;
import tn.uma.isamm.spring.tp1.entities.Commande;
import tn.uma.isamm.spring.tp1.entities.LigneCommande;
import tn.uma.isamm.spring.tp1.entities.PK_PROD_CMD;
import tn.uma.isamm.spring.tp1.entities.Produit;
import tn.uma.isamm.spring.tp1.entities.ProduitAlimentaire;
import tn.uma.isamm.spring.tp1.metier.MetierVentes;

@Controller
public class ControleurCommande {
	@Autowired
	private MetierVentes metierVentes;
	@Autowired
	CommandeDAO commandeDAO;

	@RequestMapping("/user/commandes")
	public String Commandes(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size,
			@RequestParam(name = "errorMessage", defaultValue = "") String errorMessage) {
		Page<Commande> listeCommandes = metierVentes.getCommandesPageable(page, size);
		model.addAttribute("activePage", page);
		model.addAttribute("size", size);
		int[] taillePagination = IntStream.range(0, listeCommandes.getTotalPages()).toArray();
		model.addAttribute("taillePagination", taillePagination);
		model.addAttribute("nbPages", listeCommandes.getTotalPages());
		model.addAttribute("nbElements", listeCommandes.getTotalElements());
		model.addAttribute("listeCommandes", listeCommandes);
		return "commandes";
	}
	
	
	@RequestMapping("/user/sorte")
	public String CommandesTrier(@RequestParam(name="sorte") String p, Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size,
			@RequestParam(name = "errorMessage", defaultValue = "") String errorMessage) {

		List<Commande> listeCommandes = new ArrayList<Commande>();
		if(p.equalsIgnoreCase("dateCommande")) {
			listeCommandes= commandeDAO.findAll(Sort.by(Sort.Direction.ASC,p));
		}
		else {
			listeCommandes=commandeDAO.findAll();
			Commande[] a =listeCommandes.toArray(new Commande[listeCommandes.size()]);
			Arrays.sort(a,Comparator.comparing(Commande::getTotalCommande));
			listeCommandes = Arrays.asList(a);
		}
		
			model.addAttribute("listeCommandes", listeCommandes);
			return "commandes";
	}
	
	@RequestMapping("/admin/detailCommande")
	public String detailCommande(long id, Model model) {
		Commande commande = metierVentes.getCommandeById(id);
		model.addAttribute(commande);
		model.addAttribute("id", id);
		return "detailCommande";
	}
	
	@PostMapping("/user/commandeBetweenDates")
	public String commandeBetweenDates(String startDate ,String endDate, Model model) throws ParseException {
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        Date  startD = df2.parse(startDate);
        Date endD = df2.parse(endDate);
		List<Commande> commandes = metierVentes.findByDateCommande(startD,endD);
		boolean etat = true;
		if (commandes == null)
			etat = false;
		else {
			model.addAttribute("activePage", 0);
			model.addAttribute("size", 2);
			model.addAttribute("taillePagination", 0);
			model.addAttribute("listeCommandes", commandes);
			model.addAttribute("etat", etat);			
		}
		return "commandes";
	}
	
	
	@GetMapping("/admin/supprimerCommande")
	public String supprimerCommande(Long id, Long activePage, Long nbElements, Long size, RedirectAttributes ra) {
		metierVentes.deleteCommande(id);
		System.out.println(" ----"+activePage);
		if(activePage>0 && ((nbElements-1)==(size * (activePage))))
			activePage--;
		ra.addAttribute("page", activePage);
		return "redirect:/user/commandes";
		
	}
	
		
	@RequestMapping("/user/filtreEtat")
	public String filterEtat(@RequestParam(name="etats") String p ,Model model) {
		List<Commande> listeCommandes = metierVentes.getCommandesFitrer(p);
		model.addAttribute("listeCommandes", listeCommandes);
		return "commandes";
		
	}
	
	@RequestMapping("/admin/ajouterCommande")
	public String ajouterCommande(Model model) {
			Commande commande = new Commande();
			List<Client> listClient = metierVentes.getClient();
			List<Produit> listProduit = metierVentes.getProduits();
			List<LigneCommande> lignes =new ArrayList<LigneCommande>();
			for(int i=0;i<listProduit.size();i++) {
				LigneCommande ligne =new LigneCommande(listProduit.get(i));
				lignes.add(ligne);
			}	
			commande.setLignes(lignes);
			model.addAttribute("commande",commande);
			model.addAttribute("listClient", listClient);
			return "ajouterCommande";
	}
	
	
	@PostMapping("/admin/ajouterCommande")
	public String enregistrerCommande( Commande commande, Model model) {
			Commande c2 = metierVentes.saveCommande(commande);
			List <LigneCommande>lignes = c2.getLignes();
			c2.setLignes(new ArrayList<LigneCommande>());
			for(LigneCommande ligne : lignes) {
				if(ligne.getQte()!=0) {
					LigneCommande l =new LigneCommande(ligne.getQte(),new PK_PROD_CMD(),ligne.getProduit(),c2);
					metierVentes.saveLigneCommande(l);
				}
			}	
			return "redirect:/user/commandes";	
	}
	
	@GetMapping("/admin/ajouterLigneCommande")
	public String ajouterProduitCommande(long id, Model model) {	
			Commande commande = metierVentes.getCommandeById(id);		
			List<Produit> listProduit = metierVentes.getProduits();
								
			model.addAttribute("listProduits", listProduit);
			model.addAttribute("commande",commande );
			return "ajouterLigneCommande";
	}
	
	@PostMapping("/admin/ajouterLigneCommande")
	public String ajouterProduit(Model model,Long id,int qte,String produit) {
		   long id_p = Long.parseLong(produit);
		   Produit p = metierVentes.getProduitById(id_p);
		   Commande c = metierVentes.getCommandeById(id);
		   LigneCommande l1 = new LigneCommande(qte,new PK_PROD_CMD(), p, c);
		   metierVentes.saveLigneCommande(l1);
			return "redirect:/admin/detailCommande?id="+id;
	}	
	
	

	@GetMapping("/admin/modifierCommande")
	public String modifierCommande(Long id, Model model) {
		Commande commande = metierVentes.getCommandeById(id);
		List<Client> listClient = metierVentes.getClient();
		model.addAttribute("listClient", listClient);
		model.addAttribute(commande);
		model.addAttribute("id", id);
		return "modifierCommande";
		
	}
	
	
	@PostMapping("/admin/modifierCommande")
	public String enregistrerModifier(Long id,Commande commande, Model model) {
		commande.setNumCommande(id);
		//System.out.println(id +"*****"+ commande.getNumCommande());
		//System.out.println(id +"*****"+ c2.getNumCommande());
		System.out.println(commande.getAdresseLivraison());
		List <LigneCommande>lignes = commande.getLignes();
		commande.setLignes(new ArrayList<LigneCommande>());
		Commande c2 = metierVentes.saveCommande(commande);
		for(LigneCommande ligne : lignes) {
			System.out.println("before"+ligne.getQte());
			PK_PROD_CMD Pk = new PK_PROD_CMD();
			Pk.setCommandeId(c2.getNumCommande());
			Pk.setProduitId(ligne.getProduit().getCodeProduit());
			
			if(ligne.getQte()!=0) {
				ligne.setPK_id(Pk);
				ligne.setCommande(c2);
				metierVentes.saveLigneCommande(ligne);

			}
		}	
		return "redirect:/admin/detailCommande?id="+id;
		}
	
	}
	

	

