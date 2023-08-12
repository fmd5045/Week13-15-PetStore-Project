package pet.store.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.cglib.core.LocalVariablesSorter;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {
	//creating the variables for this class
	private Long petStoreId;
	private String petStoreName;
	private String petStoreAddress;
	private String petStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;
	private Set<PetStoreCustomer> customers = new HashSet<PetStoreCustomer>();
	private Set<PetStoreEmployee> employees = new HashSet<PetStoreEmployee>();

	public PetStoreData(PetStore petStore) {
		//filling the variables with data
		petStoreId = petStore.getPetStoreId();
		petStoreName = petStore.getPetStoreName();
		petStoreAddress = petStore.getPetStoreAddress();
		petStoreCity = petStore.getPetStoreCity();
		petStoreState = petStore.getPetStoreState();
		petStoreZip = petStore.getPetStoreZip();
		petStorePhone = petStore.getPetStorePhone();

		for (Customer customer : petStore.getCustomers()) {
			customers.add(new PetStoreCustomer(customer));

			for (Employee employee : petStore.getEmployees()) {
				employees.add(new PetStoreEmployee(employee));

			}
		}
	}

	@Data
	@NoArgsConstructor
	//Associating the petstorecustomers with petstore
	public static class PetStoreCustomer {
		private Long customerId;
		private String customerFirstName;
		private String customerLastName;
		private String customerEmail;

		private PetStoreCustomer(Customer customer) {
			customerId = customer.getCustomerId();
			customerFirstName = customer.getCustomerFirstName();
			customerLastName = customer.getCustomerLastName();
			customerEmail = customer.getCustomerEmail();
		}
	}

	@Data
	@NoArgsConstructor
	//Associating the petstoreemployees with petstore
	public static class PetStoreEmployee {
		private Long petStoreEmployeeId;
		private String petStoreEmployeeFirstName;
		private String petStoreEmployeeLastName;
		private String petStoreEmployeePhone;
		private String petStoreEmployeeJobTitle;

		private PetStoreEmployee(Employee employee) {
			petStoreEmployeeId = employee.getEmployeeId();
			petStoreEmployeeFirstName = employee.getEmployeeFirstName();
			petStoreEmployeeLastName = employee.getEmployeeLastName();
			petStoreEmployeePhone = employee.getEmployeePhone();
			petStoreEmployeeJobTitle = employee.getEmployeeJobTitle();
		}
	}
}
