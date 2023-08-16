package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.entity.PetStore;
import pet.store.service.PetStoreService;

//telling springboot that this is a rest controller
@RestController
@Slf4j
@RequestMapping("/pet_store")
public class PetStoreController {
	@Autowired
	private PetStoreService petStoreService;

	// PET STORE REQUEST
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Creating pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}

	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		log.info("Updating pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@GetMapping("/{petStoreId}")
	public PetStore retrievePetStoreById(@PathVariable Long petStoreId) {
		log.info("Retrieving pet store with ID={}", petStoreId);
		return petStoreService.findPetStoreById(petStoreId);
	}
	
	@GetMapping
	public List<PetStoreData> retrieveAllPetStores() {
		log.info("Retrieving all pet stores called.");
		return petStoreService.retrieveAllPetStores();
	}
	
	@DeleteMapping
	public void deleteAllPetStores(){
		log.info("Attempting to delete all Pet Stores");
		throw new UnsupportedOperationException("Deleting all Pet Stores is not allowed.");
	}
	
	@DeleteMapping("/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
		log.info("Deleting pet store with ID= ", +petStoreId);
		petStoreService.deletePetStoreById(petStoreId);

		return Map.of("message", "Pet store with ID= " + petStoreId + " was deleted");
	}

	
	
	// EMPLOYEE REQUEST
	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee insertEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee petStoreEmployee) {
		log.info("Creating pet store employee {}", petStoreEmployee);

		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
	}
	
	@PutMapping("/{petStoreId}/employee/{employeeId}")
	public PetStoreEmployee updateEmployee(@PathVariable Long petStoreId, @PathVariable Long employeeId,
			@RequestBody PetStoreEmployee petStoreEmployee) {
		log.info("Updating employee with ID={} at pet store with ID={}", employeeId, employeeId);
		petStoreEmployee.setEmployeeId(employeeId);
		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
	}

	// CUSTOMER REQUEST
	@PostMapping("/{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer insertCustomer(@PathVariable Long petStoreId,
			@RequestBody PetStoreCustomer petStoreCustomer) {
		log.info("Adding pet store customer {} to pet store with id {}", petStoreCustomer, petStoreId);

		return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
	}


	
}
