package tn.uma.isamm.spring.tp1.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class LigneCommande implements Serializable{
	
	@Column(name="qte")
	private int qte;
	
	
	@EmbeddedId
	private PK_PROD_CMD PK_id;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("produitId")
	@JoinColumn(name="ID_PRODUIT")
	private Produit produit;
	
		
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("commandeId")
	@JoinColumn(name="ID_COMMANDE")
	private Commande commande;
	
	
	public LigneCommande() {
		// TODO Auto-generated constructor stub
	}
	
	public LigneCommande(Produit p) {
		this.produit=p;
	}
	
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public PK_PROD_CMD getPK_id() {
		return PK_id;
	}
	public void setPK_id(PK_PROD_CMD pK_id) {
		PK_id = pK_id;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public LigneCommande(int qte, PK_PROD_CMD pK_id, Produit produit, Commande commande) {
		super();
		this.qte = qte;
		PK_id = pK_id;
		this.produit = produit;
		this.commande = commande;
		
		
	}
	
	
	public double getTotalePrix() {
		return this.getQte()*this.getProduit().getPrixProduit();
	}
	

	
}
