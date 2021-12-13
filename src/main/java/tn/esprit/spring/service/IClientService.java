package tn.esprit.spring.service;
import java.util.Date;

import java.util.List;

import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;

public interface IClientService {

	List<Client> retrieveAllClients();

	Client addClient(Client c);

	void deleteClient(Long id);

	Client updateClient(Client c);

	Client retrieveClient(Long id);
	Client findUserByUserName(String username);
	
	List<Client> getClientsBetweenTwoDates (Date date1 , Date date2);
	
	List<Client> findClientsByCategorie(CategorieClient categorieClient);

	Client findUserByNom(String nom);
	float getChiffreAffaireParCategorieClient(CategorieClient categorieClient, Date startDate, Date endDate);
	
}
