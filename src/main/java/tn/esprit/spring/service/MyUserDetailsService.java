package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Role;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private ClientService ClientService;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String nom) throws UsernameNotFoundException {
		Client client = ClientService.findUserByUserName(nom);
		List<GrantedAuthority> authorities = getUserAuthority(client.getRoles());
		return new org.springframework.security.core.userdetails.User(client.getNom(),client.getPassword(),
				client.getActive(), true, true, true, authorities);
	}
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Role role : userRoles) {
		roles.add(new SimpleGrantedAuthority(role.getRole()));
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}
}
