package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Client;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import tn.esprit.spring.service.ClientService;
@RestController
public class HomeRestController {
	@Autowired
	ClientService userService;
	@PostMapping("/registration")
	@ResponseBody

	public String createNewUser( @RequestBody Client user) {
		System.out.println("++++++++++++");

		System.out.println(user);
		System.out.println("++++++++++");

		String msg="";
	Client userExists = userService.findUserByNom(user.getNom());
	if (userExists != null) {
	msg="There is already a user registered with the user name provided";
	} else {
		userService.addClient(user);
	msg="OK"; }
	return msg; }
}
