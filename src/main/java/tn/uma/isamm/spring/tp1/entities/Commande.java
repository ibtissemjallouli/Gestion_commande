package tn.uma.isamm.spring.tp1.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="COMMANDES")
public class Commande{
	@Id
	@Column(name="NUM_CMD")
	@GeneratedValue
	private long numCommande;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="DATE_CMD")
	private Date dateCommande;
	@Column(name="ADR_LIVRAISON")
	private String adresseLivraison;
	@Column(name="ETAT")
	private String etat ="enCours";
	

	@OneToMany(mappedBy="commande")
	private List<LigneCommande> lignes = new ArrayList<LigneCommande>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_CLIENT" , nullable = true)
	private Client client;
	
	public Commande(Date dateCommande, String adresseLivraison, String etat) {
		super();
		this.dateCommande = dateCommande;
		this.adresseLivraison = adresseLivraison;
		this.etat = etat;
		this.client = client;
		this.lignes = lignes;		
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	
	public Commande() {
		// TODO Auto-generated constructor stub
	}

	public Commande(Date dateCommande, String adresseLivraison) {
		super();
		this.dateCommande = dateCommande;
		this.adresseLivraison = adresseLivraison;
	}	
	
	public Commande(Date dateCommande, String adresseLivraison, Client client) {
		super();
		this.dateCommande = dateCommande;
		this.adresseLivraison = adresseLivraison;
		this.client = client;
	
	}	

	public long getNumCommande() {
		return numCommande;
	}

	public void setNumCommande(long numCommande) {
		this.numCommande = numCommande;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public String getAdresseLivraison() {
		return adresseLivraison;
	}

	public void setAdresseLivraison(String adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<LigneCommande> getLignes() {
		return lignes;
	}

	public void setLignes(List<LigneCommande> lignes) {
		this.lignes = lignes;
	}
	
	public double getTotalCommande () {
		double totalPrix=0;
		for (LigneCommande l : this.lignes) {
			totalPrix += l.getTotalePrix();
		}
		return totalPrix;
	}
	
	
	
}
