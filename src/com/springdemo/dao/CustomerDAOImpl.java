package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired  //no need to load hibernate file 
	private SessionFactory sessionFactory;
			
	
	@Override 
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}
	@Override
	public void saveCustomer(Customer theCustomer) {
		//get the current hibernate session 
		Session currentSession =sessionFactory.getCurrentSession();
		
		
		//save the customer finaaly 
		//hibernate will figure out if it is a new customer then save 
		//If  it is old customer then  update it
		
		currentSession.saveOrUpdate(theCustomer);;//actual save in database
		//saveorUpdate is used if this customer is already exist in the database
	}
	@Override
	public Customer getCustomers(int theId) {
		// get the current hibernate session 
		Session currentSession = sessionFactory.getCurrentSession();
		//now retrieve /read  from database using the primary key
		Customer thecustomer = currentSession.get(Customer.class,theId );
		return thecustomer;
	}
	@Override
	public void deleteCustomer(int theId) {
		// get the current hibernate session 
		Session currentSession = sessionFactory.getCurrentSession();
		//delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
	}

}






