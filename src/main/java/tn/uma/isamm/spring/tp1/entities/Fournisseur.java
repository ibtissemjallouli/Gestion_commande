package tn.uma.isamm.spring.tp1.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="FOURNISSEURS")
public class Fournisseur {
	@Id
	@Column(name="ID_FOUR")
	private long idFour;
	@Column(name="NOM_FOUR")
	private String nomFour;
	
	@ManyToMany(cascade  = CascadeType.ALL)
	@JoinTable(name="PROD_FOUR",
	joinColumns=@JoinColumn(name="CODE_FOUR"),
	inverseJoinColumns=@JoinColumn(name="CODE_PROD"))
	@JsonBackReference
	private Set<Produit> produits = new HashSet<Produit>();
	
	

	public Fournisseur(long idFour, String nomFour) {
		super();
		this.idFour = idFour;
		this.nomFour = nomFour;
	}


	public long getIdFour() {
		return idFour;
	}


	public void setIdFour(long idFour) {
		this.idFour = idFour;
	}


	public String getNomFour() {
		return nomFour;
	}


	public void setNomFour(String nomFour) {
		this.nomFour = nomFour;
	}


	public Set<Produit> getProduits() {
		return produits;
	}


	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}


	public Fournisseur() {
		// TODO Auto-generated constructor stub
	}


	public Fournisseur(String nomFour) {
		super();
		this.nomFour = nomFour;
	}
	
	
	

}
