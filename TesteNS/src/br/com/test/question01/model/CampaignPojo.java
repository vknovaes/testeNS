package br.com.test.question01.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="campaign")
public class CampaignPojo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5524716683675283241L;

	@Id
	@GeneratedValue
	private Long campaignId;
	
	@Column(insertable=true, updatable=true, nullable=false)
	private Long teamId;
	
	@Column(insertable=true, updatable=true, nullable=false)
	private String campaignName;
	
	@Column(insertable=true, updatable=true, nullable=false)
	private Date campaignStartDate;
	
	@Column(insertable=true, updatable=true, nullable=false)
	private Date campaignEndDate;
	
	
	public Long getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public Date getCampaignStartDate() {
		return campaignStartDate;
	}
	public void setCampaignStartDate(Date campaignStartDate) {
		this.campaignStartDate = campaignStartDate;
	}
	public Date getCampaignEndDate() {
		return campaignEndDate;
	}
	public void setCampaignEndDate(Date campaignEndDate) {
		this.campaignEndDate = campaignEndDate;
	}
	
	
	
}
