package tn.uma.isamm.spring.tp1.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PRODUITS_COSMETIQUES")
@DiscriminatorValue("cosmetique")
public class ProduitCosmetique extends Produit {
	private boolean naturel;

	public ProduitCosmetique() {
		// TODO Auto-generated constructor stub
	}

	public ProduitCosmetique(Long codeProduit, String desigProduit, double prixProduit, String uniteProduit, boolean naturel) {
		super(codeProduit, desigProduit, prixProduit, uniteProduit);
		this.naturel = naturel;
		// TODO Auto-generated constructor stub
	}

	public ProduitCosmetique(String desigProduit, double prixProduit, String uniteProduit, boolean naturel) {
		super(desigProduit, prixProduit, uniteProduit);
		this.naturel =naturel;
		// TODO Auto-generated constructor stub
	}

	public boolean isNaturel() {
		return naturel;
	}

	public void setNaturel(boolean naturel) {
		this.naturel = naturel;
	}
	
	
	
	

}
