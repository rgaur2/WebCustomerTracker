 package com.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.dao.CustomerDAO;
import com.springdemo.entity.Customer;

@Service //To notify Spring this service class 
public class CustomerServiceImpl implements CustomerService {
	//need to inject customer dao
	@Autowired
	private CustomerDAO customerDao;
	
	@Override
	@Transactional  
	public List<Customer> getCustomers() {

		return customerDao.getCustomers();
	}
	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		customerDao.saveCustomer(theCustomer);
}
	@Override
	@Transactional 
	public Customer getCustomers(int theId) {
		// TODO Auto-generated method stub
		return customerDao.getCustomers(theId) ;
	}
	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		// TODO Auto-generated method stub
		
		customerDao.deleteCustomer(theId);
	}
}