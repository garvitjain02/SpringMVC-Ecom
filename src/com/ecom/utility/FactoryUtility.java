package com.ecom.utility;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class FactoryUtility {
	static FactoryUtility factoryUtility;
	static {
		factoryUtility = new FactoryUtility();
	}
	
	private FactoryUtility () {}
	
	public static FactoryUtility getInstance () {
		return factoryUtility;
	}
	
	public EntityManager loadPersistence () {
		SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("ecom");
		
		EntityManager entityManager = sessionFactory.createEntityManager();
		
		return entityManager;
	}
}
