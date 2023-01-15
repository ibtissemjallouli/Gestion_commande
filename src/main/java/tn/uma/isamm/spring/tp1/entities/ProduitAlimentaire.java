package tn.uma.isamm.spring.tp1.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PRODUITs_ALIMENTAIRES")
@DiscriminatorValue("alimentaire")
public class ProduitAlimentaire extends Produit{
   @Column(name="APPORT_ENERGIE")
	private int apportEnergie;
   
	public ProduitAlimentaire() {
		// TODO Auto-generated constructor stub
	}

	public ProduitAlimentaire(Long codeProduit, String desigProduit, double prixProduit, String uniteProduit,  int apportEnergie) {
		super(codeProduit, desigProduit, prixProduit, uniteProduit);
		// TODO Auto-generated constructor stub
		this.apportEnergie=apportEnergie;
	}

	public ProduitAlimentaire(String desigProduit, double prixProduit, String uniteProduit, int apportEnergie) {
		super(desigProduit, prixProduit, uniteProduit);
		this.apportEnergie=apportEnergie;
		// TODO Auto-generated constructor stub
	}

	public int getApportEnergie() {
		return apportEnergie;
	}

	public void setApportEnergie(int apportEnergie) {
		this.apportEnergie = apportEnergie;
	}
	
	
	
	
	
	

}
