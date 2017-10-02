package br.com.test.question02.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.test.question01.model.CampaignPojo;
import br.com.test.question01.model.EntityManagerFactoryUtils;
import br.com.test.question01.service.Question1Service;
import br.com.test.question02.model.SocioPojo;

@Path("/user")
public class Question2Service {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	public String createUser(SocioPojo sp){
		EntityManagerFactory emf = EntityManagerFactoryUtils.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		
		SocioPojo sp2 = em.find(SocioPojo.class, sp.getEmail());
		
		if(sp2 != null){
			associarCampanhas(sp2);
			return "Usuário já cadastrado";
		}
		
		em.getTransaction().begin();
		em.persist(sp);
		em.getTransaction().commit();
		em.close();
		
		associarCampanhas(sp);
		
		return "Usuário " + sp.getEmail() + " cadastrado com sucesso";
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/campanhas")
	public List<CampaignPojo> associarCampanhas(SocioPojo sp){
		Question1Service q1s = new Question1Service();
		List<CampaignPojo> campanhasTime = q1s.getAllByTeam(sp);
		sp.setListCamps(campanhasTime);
		EntityManagerFactory emf = EntityManagerFactoryUtils.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(sp);
		em.getTransaction().commit();
		em.close();
		
		return campanhasTime;
	}
		
	
}
