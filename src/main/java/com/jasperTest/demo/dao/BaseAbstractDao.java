package com.jasperTest.demo.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.core.GenericTypeResolver;




public abstract class BaseAbstractDao<E> {

    private final SessionFactory sessionFactory;
    private final Class<?> entityClass;

    @Inject
    public BaseAbstractDao(SessionFactory factory) 
	{
        this.sessionFactory = factory;
        this.entityClass = GenericTypeResolver.resolveTypeArgument(getClass(), BaseAbstractDao.class);
    }

    protected E get(Serializable id) {
        return (E) currentSession().get(entityClass,id);
    }

    public E findById(Long id)
	{
		return get(id);
	}
	
	public E findById(Integer id)
	{
		return get(id);
	}
	public E findByName(String Name) {
		return get(Name);
	}

	public E create(E entity) {
		return persist(entity);
	}

	public List<E> create(List<E> entityList) {

		int DEFAULT_BATCH_SIZE = 500;
		Session currentSession = currentSession();
		for ( int i = 0; i < entityList.size(); i++ ) {
			currentSession.persist(entityList.get(i));
		    if ( i % DEFAULT_BATCH_SIZE == 0 ) { 
		    	currentSession.flush();
		    	currentSession.clear();
		    }
		}
		System.out.println(entityList.get(0));
		return entityList;
	}
    public E persist(E entity)
    {
        currentSession().saveOrUpdate(entity);
        return entity;
    }
   
    public void delete(Long id) 
    {
        currentSession().delete(findById(id));
    }

    public void delete(Integer id) 
    {
        currentSession().delete(findById(id));
    }
    
    public E update(E entity)
    {
        return (E) currentSession().merge(entity);
    }

    public List<E> findAll(){
        return list(criteria());
    }

    public List getQuery(String query){
    	return currentSession().getNamedQuery(query).list();
    }

    protected Session currentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    protected Criteria criteria() {
        return currentSession().createCriteria(entityClass);
    }

    protected Query namedQuery(String queryName) throws HibernateException {
        return currentSession().getNamedQuery(queryName);
    }

    protected E uniqueResult(Criteria criteria) throws HibernateException {
        return (E) criteria.uniqueResult();
    }

    protected List<E> list(Criteria criteria) throws HibernateException {
        return criteria.list();
    }

}
