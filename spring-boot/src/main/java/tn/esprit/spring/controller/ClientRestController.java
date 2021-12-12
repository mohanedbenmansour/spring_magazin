package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.service.IClientService;
@Api(tags = "Gestion des client")
@RestController
@RequestMapping("/clients")
public class ClientRestController {

	@Autowired
	IClientService clientService ;
	
	@GetMapping("/get-all")
	@ApiOperation(value = "Récupérer la liste des clients")
	@ResponseBody
	public List<Client>getClients(){
		List<Client> listClients = clientService.retrieveAllClients();
		return listClients ;
	}
	
	@GetMapping("/get-one/{client-id}")
	@ResponseBody
	public Client retrieveClient(@PathVariable("client-id") Long clientId) {
	return clientService.retrieveClient(clientId);
	}

	// http://localhost:8081/SpringMVC/client/add-client
	@PostMapping("/add")
	@ResponseBody
	public Client addClient(@RequestBody Client c)
	{
	Client client = clientService.addClient(c);
	return client;
	}
	
	@DeleteMapping("/remove/{client-id}")
	@ResponseBody
	public void removeClient(@PathVariable("client-id") Long clientId) { 
	 clientService.deleteClient(clientId);
	}

	
	@PutMapping("/modify")
	@ResponseBody
	public Client modifyClient(@RequestBody Client client) {
	return clientService.updateClient(client);
	}
	
	//get clients between two dates
	@GetMapping("/date1/{date1}/date2/{date2}")
	@ResponseBody
	public List<Client> getBetweenTwoDates
			(@PathVariable("date1")@DateTimeFormat(pattern = "yyyy-MM-dd")Date d1,
			@PathVariable("date2")@DateTimeFormat(pattern = "yyyy-MM-dd")Date d2){
		return clientService.getClientsBetweenTwoDates(d1, d2);
	}
	
	@GetMapping("categories/{cat}")
	@ResponseBody
	public List<Client> getByCategorie(@PathVariable("cat")CategorieClient categorieClient){
		return clientService.findClientsByCategorie(categorieClient);
	}
	
	// get chiffre d'affaire par categorie des clients et entre les deux dates
	@GetMapping("/getTotal/{catg}/{d1}/{d2}")
	@ResponseBody
	public float getChiffreAffaireParCategorieClient
			(@PathVariable("catg")CategorieClient categorieClient,@PathVariable("d1")
			@DateTimeFormat(pattern = "yyyy-MM-dd")Date d1,
			@PathVariable("d2")@DateTimeFormat(pattern = "yyyy-MM-dd")Date d2) 
	{
		return clientService.getChiffreAffaireParCategorieClient(categorieClient, d1, d2) ;
	}
	
}
