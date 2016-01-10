package com.jasperTest.demo.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jasperTest.demo.model.AccountStatus;




@Repository
public class AccountStatusDao extends BaseAbstractDao<AccountStatus> {

	@Autowired
	public AccountStatusDao(SessionFactory factory) {
		super(factory);
		// TODO Auto-generated constructor stub
	}

	
	public List<AccountStatus> fetchAll(){
			
		
		return super.currentSession().createCriteria(AccountStatus.class)
			
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		
		
		
	}

}
