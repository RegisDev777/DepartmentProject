package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbExeception;
import model.dao.DaoFactory;
import model.dao.sellerDao;
import model.etities.Seller;

public class program {

    public static void main(String[] args) {
      
    	    sellerDao sellerDao = DaoFactory.createSellerDao();
    	    Seller seller = sellerDao.findById(3);
    	    System.out.println(seller);
          
    }
}