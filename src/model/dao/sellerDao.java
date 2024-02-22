package model.dao;

import java.util.List;

import model.etities.Department;
import model.etities.Seller;

public interface sellerDao {
    void insert(Seller obj);
	void update(Seller obj);
	void deleteById(Integer Id);
	Seller findById(Integer id);
	List<Seller> findAll();
	List<Seller> findByDepartment(Department department);
	

	
	
}
