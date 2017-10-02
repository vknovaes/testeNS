package br.com.test.question01.service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.test.question01.model.CampaignPojo;
import br.com.test.question01.model.EntityManagerFactoryUtils;
import br.com.test.question02.model.SocioPojo;



@Path("/campaign")
public class Question1Service {

	private List<CampaignPojo> listAll;
	private static List<CampaignPojo> updatedList = new ArrayList<>();
	
	public Question1Service() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		
		listAll = em.createQuery("select c from campaign c where c.campaignEndDate < current_date()", CampaignPojo.class).getResultList();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<CampaignPojo> getAllCampaigns(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		listAll = em.createQuery("select c from campaign c where c.campaignEndDate >= current_date()", CampaignPojo.class).getResultList();
		return listAll;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/campteam")
	public List<CampaignPojo> getAllByTeam(SocioPojo sp){
		EntityManagerFactory emf = EntityManagerFactoryUtils.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		TypedQuery<CampaignPojo> query =  em.createQuery("select c from campaign c where c.campaignEndDate >= current_date() and c.teamId = :teamId", CampaignPojo.class);
		listAll = query.setParameter("teamId", sp.getIdTime()).getResultList();
		return listAll;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insert")
	public String insertCampaign(CampaignPojo cpj){
		EntityManagerFactory emf = EntityManagerFactoryUtils.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		updatedCampaigns(cpj);
		em.getTransaction().begin();
		em.persist(cpj);
		em.getTransaction().commit();
		em.close();
		return "Campanha " + cpj.getCampaignId() + " cadastrada com sucesso";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public String deleteCampaign(CampaignPojo campaign){
		EntityManagerFactory emf = EntityManagerFactoryUtils.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		CampaignPojo cp = em.find(CampaignPojo.class, campaign.getCampaignId());
		em.getTransaction().begin();
		em.remove(cp);
		em.getTransaction().commit();
		em.close();
		return "Campanha " + campaign.getCampaignName() + " excluída com sucesso";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public String updateCampaign(CampaignPojo campaign){
		EntityManagerFactory emf = EntityManagerFactoryUtils.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		if(campaign.getCampaignId() == null){
			insertCampaign(campaign);
		}else{
			em.merge(campaign);
			em.getTransaction().commit();
			em.close();
		}
		return "Campanha " + campaign.getCampaignId() + " atualizada com sucesso";
		
	}	
	
	
	private void updatedCampaigns(CampaignPojo cp){
		EntityManagerFactory emf = EntityManagerFactoryUtils.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		List<CampaignPojo> listCamp = em.createQuery("select c from campaign c where c.campaignEndDate = :date", CampaignPojo.class)
				.setParameter("date", cp.getCampaignEndDate()).getResultList();
		for(CampaignPojo pojo : listCamp){
			Calendar c = Calendar.getInstance();
			c.setTime(pojo.getCampaignEndDate());
			c.add(Calendar.DATE, 1);
			pojo.setCampaignEndDate(Date.valueOf(c.getTime().toString()));
			em.merge(pojo);
			em.getTransaction().commit();
			em.close();
			updatedList.add(pojo);
			updatedCampaigns(pojo);
			
		}
	}
	
}
