package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Customer;
import com.revature.dao.CustomerDAO;
import com.revature.dao.CustomerDAOImpl;


@Service
public class CustomerService {

	@Autowired
	private CustomerDAO cdao;
	
	@Autowired
	public CustomerService(CustomerDAOImpl cd) {
		this.cdao = cd;
	}
	
	public Customer getCustomerByCredentialId(int id) {
		return cdao.getCustomerByCredentialId(id);
		
	}

}
