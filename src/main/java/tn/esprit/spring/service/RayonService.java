package tn.esprit.spring.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Rayon;
import tn.esprit.spring.repository.RayonRepository;
@Service
public class RayonService implements IRayonService{

	@Autowired
	RayonRepository rayonRepository ;

	@Override
	public Rayon getById(Long idRayon) {
		return rayonRepository.findById(idRayon).get();
	}

	@Override
	public List<Rayon> getAllRayons() {
		return rayonRepository.findAll();
	}

	@Override
	public Rayon addRayon(Rayon rayon) {
		return rayonRepository.save(rayon);
	}

	@Override
	public void deleteRayon(Long id) {
		rayonRepository.deleteById(id);
	}

	@Override
	public Rayon updateRayon(Rayon rayon) {
		return rayonRepository.save(rayon);
	}
	
	
	

}
