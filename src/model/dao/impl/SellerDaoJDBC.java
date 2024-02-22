package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbExeception;
import model.dao.sellerDao;
import model.etities.Department;
import model.etities.Seller;

public class SellerDaoJDBC implements sellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		 try {
			 st = conn.prepareStatement(
				 "INSERT INTO seller "
	          	+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) " 
				+ "	 VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			 st.setString(1, obj.getName());
			 st.setString(2, obj.getEmail());
			 st.setDate(3, new java.sql.Date(obj.getBrithDate().getTime()));
			 st.setDouble(4, obj.getBaseSalary());
			 st.setInt(5,obj.getDepartment().getId());
			 
			 int rowsAffected = st.executeUpdate();
			   if(rowsAffected > 0) {
				   ResultSet rs = st.getGeneratedKeys();
				    if(rs.next()) {
				    	int id = rs.getInt(1);
				    	obj.setId(id);
				    }
				    DB.closeResultSet(rs);
			   } else {
				   throw new db.DbExeception("Unexpected error! No rows affected!");
			   }
		 } catch (SQLException e ) {
			 throw new DbExeception(e.getMessage());
		 } finally {
			 DB.closeStatement(st);
		 }

	}
	

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer Id) {
		// TODO Auto-generated method stub
		
	}
	
   //INSTANCIAS DEPARTAMENTO E SELLER
	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		  ResultSet rs = null;
		      try {
			    	  st = conn.prepareStatement(
			    			  "SELECT seller.*,department.Name as DepName "
			    			 + "FROM seller INNER JOIN department "
			    			 + "ON seller.DepartmentId = department.Id "
			    			 + "WHERE seller.Id = ?");
			    	  
			    	  st.setInt(1, id);
			    	  rs = st.executeQuery();
			    	  if(rs.next()) {
			    		  Department  dep = instantiateDepartment(rs);
			    		  Seller seller =  instantiateSeller(rs, dep);
				    	 return seller;
			    	  }
			    	  return null;
		    	  
		       } catch (SQLException e ) {
		    	   throw new DbExeception(e.getMessage());
		       } finally {
		    	   DB.closeResultSet(rs);
		     	   DB.closeStatement(st);
	 	       }
		    	 
	     }	
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		  Seller obj = new Seller();
			  obj.setId(rs.getInt("Id"));
			  obj.setName(rs.getString("Name"));
			  obj.setEmail(rs.getString("Email"));
			  obj.setBaseSalary(rs.getDouble("BaseSalary"));
			  obj.setBrithDate(rs.getDate("BirthDate"));
			  obj.setDepartment(dep);
		  return obj;
	}
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		  Department dep = new Department();
			  dep.setId(rs.getInt("DepartmentId"));
			  dep.setName(rs.getString("DepName"));
		  return dep;
	}


	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		  ResultSet rs = null;
		      try {
			    	  st = conn.prepareStatement(
			    			  "select seller.*,department.Name as DepName "
			    			 +"from seller inner join department "
			    			 +"on seller.DepartmentId = department.Id "
			    			 +"order by Name");
			    	  
			    	  rs = st.executeQuery();
			    	  List<Seller> list = new ArrayList<Seller>();
			    	  Map<Integer, Department> map = new HashMap<Integer, Department>();
					    	  while (rs.next()) {
					    		  Department dep = map.get(rs.getInt("DepartmentId")); 
					    		   if(dep == null) {
					    			   dep = instantiateDepartment(rs);
							    	   map.put(rs.getInt("DepartmentId"), dep);
					    		   }
					    		   Seller obj = instantiateSeller(rs, dep);
					    		   list.add(obj);
					    		  
					    	  }
			    	  return list;
		    	  
		       } catch (SQLException e ) {
		    	   throw new DbExeception(e.getMessage());
		       } finally {
		    	   DB.closeResultSet(rs);
		     	   DB.closeStatement(st);
	 	       }
		  
		  
	}


	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		  ResultSet rs = null;
		      try {
			    	  st = conn.prepareStatement(
			    			  "select seller.*,department.Name as DepName "
			    			 +"from seller inner join department "
			    			 +"on seller.DepartmentId = department.Id "
			    			 +"where DepartmentId = ? "
			    			 +"order by Name");
			    	  
			    	  st.setInt(1, department.getId() );
			    	  rs = st.executeQuery();
			    	  List<Seller> list = new ArrayList<Seller>();
			    	  Map<Integer, Department> map = new HashMap<Integer, Department>();
					    	  while (rs.next()) {
					    		  Department dep = map.get(rs.getInt("DepartmentId")); 
					    		   if(dep == null) {
					    			   dep = instantiateDepartment(rs);
							    	   map.put(rs.getInt("DepartmentId"), dep);
					    		   }
					    		   Seller obj = instantiateSeller(rs, dep);
					    		   list.add(obj);
					    		  
					    	  }
			    	  return list;
		    	  
		       } catch (SQLException e ) {
		    	   throw new DbExeception(e.getMessage());
		       } finally {
		    	   DB.closeResultSet(rs);
		     	   DB.closeStatement(st);
	 	       }
	}


	
	
	
}
