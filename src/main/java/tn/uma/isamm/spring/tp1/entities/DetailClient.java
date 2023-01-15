package tn.uma.isamm.spring.tp1.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter; 



@Entity
@Table(name="DETAIL_CLIENT")
public class DetailClient {
	
	@Id
    @GeneratedValue(generator = "foreigngen")
    @GenericGenerator(strategy = "foreign", name="foreigngen",parameters = @Parameter(name = "property", value="client"))
    @Column(name = "ID_DETAIL")
	private long idDetail;
	private String adresse;
	private String tel;
	private String mail;
	
	@OneToOne(mappedBy ="detailClient")
	private Client client;
	 
	public DetailClient() {
		// TODO Auto-generated constructor stub
	}

	
	
	public DetailClient(String adresse, String tel, String mail) {
		super();
		this.adresse = adresse;
		this.tel = tel;
		this.mail = mail;
	}



	public DetailClient(long idDetail, String adresse, String tel, String mail) {
		super();
		this.idDetail = idDetail;
		this.adresse = adresse;
		this.tel = tel;
		this.mail = mail;
	}



	public long getIdDetail() {
		return idDetail;
	}



	public void setIdDetail(long idDetail) {
		this.idDetail = idDetail;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
