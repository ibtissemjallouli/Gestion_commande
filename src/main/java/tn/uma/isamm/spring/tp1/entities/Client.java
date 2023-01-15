package tn.uma.isamm.spring.tp1.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.service.ServiceRegistry;

@Entity
@Table(name="CLIENTS")
public class Client implements Serializable{
	@Id
	@GeneratedValue
	private long idClient;
	private String nomClient;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private DetailClient detailClient;
	
	@OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
	private Set<Commande> commandes = new HashSet<Commande>();

	public Client() {
		// TODO Auto-generated constructor stub		
	}	
	
	public Client(String nomClient) {
		super();
		this.nomClient = nomClient;	
	}

	public Client(long idClient, String nomClient) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;	
	}
	
	public Client(long idClient, String nomClient, Set<Commande> commandes) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.commandes = commandes;
	}

	public Client(long idClient, String nomClient, DetailClient detailClient, Set<Commande> commandes) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.detailClient = detailClient;
		this.commandes = commandes;
		
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public DetailClient getDetailClient() {
		return detailClient;
	}

	public void setDetailClient(DetailClient detailClient) {
		this.detailClient = detailClient;
	}

	public Set<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(Set<Commande> commandes) {
		this.commandes =  commandes;
	}	
	
	public void ajouterCommande(Commande c) {
		this.commandes.add(c);
	}
	
	public void supprimerCommande(Commande c) {
		this.commandes.remove(c);
	}
}
