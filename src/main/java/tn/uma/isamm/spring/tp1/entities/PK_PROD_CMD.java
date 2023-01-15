package tn.uma.isamm.spring.tp1.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PK_PROD_CMD implements Serializable{
	
	
	@Column(name = "ID_COMMANDE")
	private long commandeId;

	@Column(name = "ID_PRODUIT")
	private long produitId;
	    


	public PK_PROD_CMD() {
		// TODO Auto-generated constructor stub
	}



	public long getCommandeId() {
		return commandeId;
	}



	public void setCommandeId(long commandeId) {
		this.commandeId = commandeId;
	}



	public long getProduitId() {
		return produitId;
	}



	public void setProduitId(long produitId) {
		this.produitId = produitId;
	}



	public PK_PROD_CMD(long commandeId, long produitId) {
		super();
		this.commandeId = commandeId;
		this.produitId = produitId;
	}


}
