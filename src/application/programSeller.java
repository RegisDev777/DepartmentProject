package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import db.DB;
import db.DbExeception;
import model.dao.DaoFactory;
import model.dao.sellerDao;
import model.etities.Department;
import model.etities.Seller;

public class programSeller {

    public static void main(String[] args) {
      
    Scanner sc = new Scanner(System.in);	
    	
    	
    	
    	    sellerDao sellerDao = DaoFactory.createSellerDao();    
    	    System.out.println("=== TEST 1: seller findById ===");
    	    Seller seller = sellerDao.findById(3);
            System.out.println(seller);
            
            
            
            System.out.println();
            
            
            
            System.out.println("=== TEST 2: seller findByDepartment ===");
            Department department = new Department(2, null);
            List<Seller> list = sellerDao.findByDepartment(department);
            for (Seller obj : list) {
				System.out.println(obj);
			}
            
            
            
            System.out.println();
            
            
            
            System.out.println("=== TEST 3: seller findAll ===");
            list = sellerDao.findAll();
            for (Seller obj : list) {
            	System.out.println(obj);
            }
            
            
            System.out.println();
            
            
            System.out.println("=== TEST 4: seller Insert ===");
            Seller newSeller = new Seller(null, "Greg", "greg@gamil.com", new Date(), 4000.00, department);
            sellerDao.insert(newSeller);
            System.out.println("Inserted! New id = " + newSeller.getId());
            
            
            System.out.println();
            
            System.out.println("=== TEST 5: seller Uptade ===");
            seller = sellerDao.findById(1);
            seller.setName("marta waine");
            sellerDao.update(seller);
            System.out.println("Update completed");
            
            
            System.out.println();
            
            
            System.out.println("=== TEST 6: seller Delete ===");
            System.out.print("Enter id for delete test: ");
            int id = sc.nextInt();
            sellerDao.deleteById(id);
            System.out.println("Delete completed");
            
            System.out.println();
            
            
            
            System.out.println("=== TEST PUSH GIT PARA TESTE ===");
            
            
            sc.close();
    } 
}