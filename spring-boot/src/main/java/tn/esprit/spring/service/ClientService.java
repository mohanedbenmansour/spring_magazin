package tn.esprit.spring.service;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.FactureRepository;




@Service
public class ClientService implements IClientService{

	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired 
	private FactureRepository factureRepository ;
	
	
	
	@Override
	public List<Client> retrieveAllClients() {
		
		return clientRepository.findAll() ;
		
	}

	@Override
	public Client addClient(Client c) {

		return clientRepository.save(c);
	}

	@Override
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
		
	}

	@Override
	public Client updateClient(Client c) {
		return clientRepository.save(c);
	}
 
	@Override
	public Client retrieveClient(Long id) {
		return clientRepository.findById(id).get();
	}

	@Override
	public List<Client> getClientsBetweenTwoDates(Date date1, Date date2) {

		return clientRepository.getClientsBetweenTwoDates(date1, date2);
	}
	
	
	
	@Override
	public List<Client> findClientsByCategorie(CategorieClient categorieClient) {
		
		return clientRepository.findByCategorieClient(categorieClient);
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * this is not optimised enough so i did another function like this in facture service 
	 */

	@Override
	public float getChiffreAffaireParCategorieClient(CategorieClient categorieClient, Date startDate, Date endDate) {
		List<Client>listDesClients = clientRepository.findByCategorieClient(categorieClient);
		
		float montantTotale = 0;
		
		for (Client client : listDesClients) {
			List<Facture>listDesFacturesParClient = factureRepository.findByIdClient(client.getIdClient(), startDate, endDate);
			float montantTotaleParClient = 0 ;
			for (Facture facture : listDesFacturesParClient) {
				montantTotaleParClient += facture.getMontantFacture() ;
			}
			montantTotale += montantTotaleParClient ;
		}
		
		
		return montantTotale;
	}
	/*
	 * this is not optimised enough so i did another function like this in facture service 
	 */

	

}
