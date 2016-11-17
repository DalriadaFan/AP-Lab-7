package lab;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;

/**
 *
 * @author Fahad Satti
 */
public class ToyStopInventoryManagementSystem implements java.io.Serializable {
    public static ToyStopService tsService = new ToyStopService();
    public void init(){
        
        tsService.initEmployees();
        tsService.initStores();
        tsService.initToys();
        System.out.println("Init complete");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner userRsp = new Scanner(System.in);
        Scanner userName = new Scanner(System.in);
        int num = 0;
        String name = null;
        ToyStopInventoryManagementSystem tsims = new ToyStopInventoryManagementSystem();
        //tsims.init();
        
        //load previous data
        //tsims.loadData();
        //tsims.showMenu();
        for (int i = 0; i < 60; i++) {
        	// Mimiking The first time start of the system
        	if (i == 0) {
        		tsims.init();
        	// Mimiking the start of the system on rest of the days
        	} else {
        		tsims.deserialize();
        	}
        	tsims.showMenu();
        	//tsims.printAll();
	        switch(userRsp.nextInt()) {
	        case 6:
	        	System.out.println("Enter store Number to display");
	        	tsService.displayStore();
	        	System.out.println("Enter store Number to search it");
	        	tsService.searchStore(userRsp.nextInt());
	        	break;
	        case 7:
	        	tsService.displayEmployee();
	        	break;
	        case 8:
	        	System.out.println("Enter employee ID");
	        	num = userRsp.nextInt();
	        	tsService.searchEmployeeID(num);
	        	break;
	        case 9:
	        	System.out.println("Enter employee Name");
	        	name= userName.nextLine();
	        	tsService.searchEmployeeName(name);
	        	break;
	        case 10:
	        	tsService.displayToys();
	        	break;
	        case 11:
	        	System.out.println("Enter Toy ID");
	        	num = userRsp.nextInt();
	        	tsService.searchToyID(num);
	        	break;
	        case 12:
	        	System.out.println("Enter Toy Name");
	        	name= userName.nextLine();
	        	tsService.searchToyName(name);
	        	break;
	        }
	        tsims.serialize();
	        // Mimicing restart of the system
	        tsims.tsService = null;
        }
        //tsService.showToyDistribution();
        
        //tsims.printAll();
        
    }

    private void loadData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void showMenu() {
        System.out.println("Welcome to Toy Stop Inventory Management System");
        System.out.println("Enter 1 to show all data");
        System.out.println("Enter 2 to add a new Store");
        System.out.println("Enter 3 to add a new Employee");
        System.out.println("Enter 4 to add a new Toy");
        System.out.println("Enter 5 to see Toys distribution");
        System.out.println("Enter 6 to search The Store");
        System.out.println("Enter 7 to display employees");
        System.out.println("Enter 8 to search The Employee using ID");
        System.out.println("Enter 9 to search The Employee using Name");
        System.out.println("Enter 10 to Display The Toy");
        System.out.println("Enter 11 to search The Toy using ID");
        System.out.println("Enter 12 to search The Toy using Name");
        System.out.println("Enter 0 to save state");
    }

    private void printAll() {
        System.out.println(this.tsService.stores.get(1).getUID());
    }
    
    public void serialize () {
        try {
            FileOutputStream fileOut =
            new FileOutputStream("ToyStore.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tsService);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in ToyStore.ser");
         }catch(IOException i) {
            i.printStackTrace();
         }
    }
    
    public void deserialize () {
    	try {
            FileInputStream fileIn = new FileInputStream("ToyStore.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tsService = (ToyStopService) in.readObject();
            in.close();
            fileIn.close();
         }catch(IOException i) {
            i.printStackTrace();
            return;
         }catch(ClassNotFoundException c) {
            System.out.println("ToyStore class not found");
            c.printStackTrace();
            return;
         }
    }
}
