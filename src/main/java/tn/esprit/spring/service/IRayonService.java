package tn.esprit.spring.service;

import java.util.List;
import tn.esprit.spring.entity.*;

public interface IRayonService {

	public Rayon getById(Long idRayon);
	
	public List<Rayon> getAllRayons ();
	
	public Rayon addRayon (Rayon rayon);
	
	public void deleteRayon (Long id);
	
	public Rayon updateRayon (Rayon rayon);
}
