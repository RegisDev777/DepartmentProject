package model.dao;

import java.util.List;

import model.etities.Department;

public interface sellerDao {

	void insert(sellerDao obj);
	void update(sellerDao obj);
	void deleteById(Integer Id);
	sellerDao findById(Integer id);
	List<sellerDao> findAll();
	
}
