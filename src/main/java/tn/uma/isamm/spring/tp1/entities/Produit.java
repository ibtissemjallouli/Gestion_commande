
package tn.uma.isamm.spring.tp1.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="PRODUITS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_PRODUIT", discriminatorType=DiscriminatorType.STRING, length=20)
@DiscriminatorValue("produit")
public class Produit {
	@Id
	@Column(name="CODE_PRODUIT")
	private long codeProduit;
	@Column(name="DESIG_PRODUIT")
	private String desigProduit;
	@Column(name="PRIX_PRODUIT")
	private double prixProduit;
	@Column(name="UNITE_PRODUIT")
	private String uniteProduit;

	@ManyToOne
	@JsonManagedReference
	private Categorie categorie;
	
	
	@ManyToMany(mappedBy = "produits")
	@JsonManagedReference
	private Set<Fournisseur> fournisseurs = new HashSet<Fournisseur>();
	
	@OneToMany(mappedBy="produit")
	@JsonManagedReference
	private Set<LigneCommande> lignes= new HashSet<LigneCommande>();
	
	public Produit() {
		// TODO Auto-generated constructor stub
	}

	public Produit(String desigProduit, double prixProduit, String uniteProduit) {
		super();
		this.desigProduit = desigProduit;
		this.prixProduit = prixProduit;
		this.uniteProduit = uniteProduit;
	}

	public Produit(Long codeProduit, String desigProduit, double prixProduit, String uniteProduit) {
		super();
		this.codeProduit = codeProduit;
		this.desigProduit = desigProduit;
		this.prixProduit = prixProduit;
		this.uniteProduit = uniteProduit;
	}
	
	public Produit(Long codeProduit, String desigProduit, double prixProduit, String uniteProduit,
			Categorie categorie) {
		super();
		this.codeProduit = codeProduit;
		this.desigProduit = desigProduit;
		this.prixProduit = prixProduit;
		this.uniteProduit = uniteProduit;
		this.categorie = categorie;
	}
	
	public long getCodeProduit() {
		return codeProduit;
	}


	public void setCodeProduit(long codeProduit) {
		this.codeProduit = codeProduit;
	}


	public String getDesigProduit() {
		return desigProduit;
	}


	public void setDesigProduit(String desigProduit) {
		this.desigProduit = desigProduit;
	}


	public double getPrixProduit() {
		return prixProduit;
	}


	public void setPrixProduit(double prixProduit) {
		this.prixProduit = prixProduit;
	}


	public String getUniteProduit() {
		return uniteProduit;
	}


	public void setUniteProduit(String uniteProduit) {
		this.uniteProduit = uniteProduit;
	}


	public Set<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}


	public void setFournisseurs(Set<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}


	public Set<LigneCommande> getLignes() {
		return lignes;
	}


	public void setLignes(Set<LigneCommande> lignes) {
		this.lignes = lignes;
	}


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	@Override
	public String toString() {
		return "Produit [codeProduit=" + codeProduit + ", desigProduit=" + desigProduit + ", prixProduit=" + prixProduit
				+ ", uniteProduit=" + uniteProduit + ", categorie=" + categorie + ", fournisseurs=" + fournisseurs
				+ ", lignes=" + lignes + "]";
	}
	
	
}
