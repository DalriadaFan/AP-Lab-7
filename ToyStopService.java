
package lab;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Fahad Satti
 */
public class ToyStopService implements java.io.Serializable {
    public ArrayList<Employee> employees = new ArrayList<>();
    public ArrayList<Store> stores = new ArrayList<>();
    public int storeID = 0;
    
    public void initEmployees(){
        //Create a List of first 200 Employees
        for(int i=0; i<200; i++){
            Employee myEmployee = new Employee();
            myEmployee.setUID(i);
            myEmployee.setRandomName();
            myEmployee.setEmail(myEmployee.getName()+"@toystop.org");
            
            employees.add(myEmployee);
        }
    }
    
    public void initStores(){
        //Create a List of Stores in a region
        for(int i=0; i<100; i++){
            Store myStore = new Store();
            //myStore.setUID(Util.getSaltNum(-1));
            myStore.setUID(storeID); storeID++;
            myStore.addRandomEmployees(employees);
            stores.add(myStore);
            myStore.setAddress(Util.getSaltAlphaNumString());
            myStore.setContactNo("+92"+Util.getSaltNum(9));
            Email storeEmail = new Email();
            storeEmail.setEmailAddress(myStore.getUID()+"@toystop.org");
            myStore.setEmail(storeEmail);
            
        }
       
        while (!employees.isEmpty()) {
        	for (int i = 0; i < 100; i++) {
        		stores.get(i).addRandomEmployees(employees); }
        }
    }
    
    public void initToys(){
        //Add Toys in random stores
        for(int i=0; i<200000; i++){
            Toy newToy = new Toy();
            newToy.setUID(Util.getSaltNum(-1));
            newToy.setMinAge(Util.getSaltNum(1));
            newToy.setMaxAge(Util.getSaltNum(18));
            newToy.setPrice(Util.getSaltNum(1000));
            newToy.setName(Util.getSaltAlphaString());
            newToy.setAddedOn(LocalDateTime.now());
            
            Random randStore = new Random();
            int index = randStore.nextInt(stores.size());
            Store selectedStore = (Store)stores.get(index);
            selectedStore.addToy(newToy);
            
        }
    }
    //Only creates a new employee and returns the UID
    public int addEmployee(){
            Employee myEmployee = new Employee();
            
            myEmployee.setRandomName();
            myEmployee.setEmail(myEmployee.getName()+"@toystop.org");
            myEmployee.setUID(employees.size()+1);
            employees.add(myEmployee);
            return myEmployee.getUID();
    }
    
    //Creates a new store
    public int addStore(){
            Store myStore = new Store();
            myStore.setUID(Util.getSaltNum(-1));
            //This will assign any new employees or the ones remaining after previous store insertions.
            myStore.addRandomEmployees(employees);
            
            myStore.setAddress(Util.getSaltAlphaNumString());
            myStore.setContactNo("+92"+Util.getSaltNum(9));
            Email storeEmail = new Email();
            storeEmail.setEmailAddress(myStore.getUID()+"@toystop.org");
            myStore.setEmail(storeEmail);
            stores.add(myStore);
            return myStore.getUID();
            
    }

    public void showToyDistribution() {
    	System.out.println("Store       Number of Toys");
    	//int count = 0;
    	for (int i = 0; i < stores.size(); i++) {		
    		System.out.println(stores.get(i).getUID() + "    \t    " + stores.get(i).getToys().size());
    		//count++;
    	}
    	//System.out.println("count is: " + count);
    }
    

    // Searching and displaying store
    public void displayStore() {
    	System.out.println("Store IDs");
    	for (Store store : stores) {
    		System.out.println(store.getUID());
    	}
    }
    public void searchStore(int storeID) {
    	//System.out.println(employees.size());
    	if(!stores.isEmpty()) {
    		for (Store store : stores) {
    			if (store.getUID() == storeID) {
    				System.out.println("Store Found!: " + store.getContactNo());
    			}
    		}
    	}
    }
    

    // Searching and displaying Employee
    public void displayEmployee() {
    	System.out.println("Employee ID     Name");
    	for (Store store : stores) {
    		for (Employee employee : store.employees) {
    			System.out.println(employee.getUID() + " \t " + employee.getName());
    		}
    	}
    }
    public void searchEmployeeID(int empID) {
    	boolean fnd = false;
		for (Store store : stores) {
    		for (Employee employee : store.employees) {
    			if (employee.getUID() == empID) {
    				System.out.println("Employee found!!" + employee);
    				fnd = true;
    			}
    		}
    	}
		if (fnd) {
			System.out.println("not found");
		}
    }
    public void searchEmployeeName(String empName) {
    	boolean fnd = false;
		for (Store store : stores) {
    		for (Employee employee : store.employees) {
    			if (empName.equalsIgnoreCase(employee.getName())) {
    				fnd = true;
    				System.out.println("Employee found!!" + employee);
    			}
    		}
    	}
		if (fnd) {
			System.out.println("not found");
		}
	}
    

    // Searching and displaying Toys
    public void displayToys() {
    	System.out.println("Toys ID     Name");
    	for (Store store : stores) {
    		for (Toy toy : store.getToys()) {
    			System.out.println(toy.getUID() + " \t " + toy.getName());
    		}
    	}
    }
    public void searchToyID(int empID) {
    	boolean fnd = false;
		for (Store store : stores) {
    		for (Toy toy : store.getToys()) {
    			if (toy.getUID() == empID) {
    				System.out.println("Toy found!!" + toy);
    				fnd = true;
    			}
    		}
    	}
		if (fnd) {
			System.out.println("not found");
		}
    }
    public void searchToyName(String empName) {
    	boolean fnd = false;
		for (Store store : stores) {
    		for (Toy toy : store.getToys()) {
    			if (empName.equalsIgnoreCase(toy.getName())) {
    				fnd = true;
    				System.out.println("Toy found!!" + toy);
    			}
    		}
    	}
		if (fnd) {
			System.out.println("not found");
		}
	}
}
