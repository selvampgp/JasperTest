package com.jasperTest.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jasperTest.demo.dao.AccountStatusDao;
import com.jasperTest.demo.model.AccountStatus;

@Component
@Service
public class AccountStatusService {
	
	@Autowired
	private AccountStatusDao dao;
	
	@Autowired
	public AccountStatusService(AccountStatusDao accountStatusDao) {
		this.dao=accountStatusDao;
	}
	@Transactional
	public List<AccountStatus> findAccountStatusById(){
		return dao.fetchAll();
		
	}
	@Transactional
	public List<AccountStatus> findAllStatus(){
		return dao.findAll();
	}
	
	@Transactional
	public AccountStatus create(AccountStatus accountStatusMaster) {
		
		return dao.persist(accountStatusMaster);
	}
	

	
}
