package br.com.fiap.pethub.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

	@Autowired
	PetRepository petRepository;

	public List<Pet> findAll() {
		return petRepository.findAll();
	}

	public boolean delete(Long id) {
		var pet = petRepository.findById(id);

		if(pet.isEmpty()) return false;

		petRepository.deleteById(id);
		return true;
	}
}
