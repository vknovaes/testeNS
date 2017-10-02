package br.com.test.question02.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import br.com.test.question01.model.CampaignPojo;

@Entity(name="socio")
public class SocioPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3602485746851374645L;

	@Id
	private String email;
	
	@Column(insertable=true,updatable=true,nullable=false)
	private String nome;
	
	@Column(insertable=true,updatable=true,nullable=false)
	private Date dtNasc;
	
	@Column(insertable=true,updatable=true,nullable=false)
	private Long idTime;
	
	@ManyToMany
	@JoinTable(name="campUser", joinColumns={@JoinColumn(name="email")},inverseJoinColumns={@JoinColumn(name="campaignId")})
	private List<CampaignPojo> listCamps;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}
	public Long getIdTime() {
		return idTime;
	}
	public void setIdTime(Long idTime) {
		this.idTime = idTime;
	}
	public List<CampaignPojo> getListCamps() {
		return listCamps;
	}
	public void setListCamps(List<CampaignPojo> listCamps) {
		this.listCamps = listCamps;
	}
	
	
}
