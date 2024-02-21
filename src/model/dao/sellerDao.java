package model.dao;

import java.util.List;

import model.etities.Department;
import model.etities.Seller;

public interface sellerDao {

	void insert(sellerDao obj);
	void update(sellerDao obj);
	void deleteById(Integer Id);
	Seller findById(Integer id);
	List<sellerDao> findAll();
	
}
