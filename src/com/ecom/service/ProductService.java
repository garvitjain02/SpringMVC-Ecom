package com.ecom.service;

import java.util.List;
import java.util.Scanner;

import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.model.Vendor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ProductService {
	Scanner sc;
	EntityManager entityManager;
	EntityTransaction entityTransaction;
	
	public ProductService(Scanner sc, EntityManager entityManager, EntityTransaction entityTransaction) {
		super();
		this.sc = sc;
		this.entityManager = entityManager;
		this.entityTransaction = entityTransaction;
	}
	
	public Product getProductById() {
		System.out.println("Enter Product Id: ");
		int id = sc.nextInt();
		
		entityTransaction.begin();
		Product p = entityManager.find(Product.class, id);
		entityTransaction.commit();
		
		return p;
	}

	public void delete(Product product) {
		entityTransaction.begin();
		entityManager.remove(product);
		entityTransaction.commit();
		
	}

	public List<Product> getAllProducts() {
		entityTransaction.begin();
		String jpql = "select p from Product p";
		TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
		List<Product> list = query.getResultList();
		entityTransaction.commit();
		return list;
	}

	public Product takeInput() {
		Product p = new Product();
		
		sc.nextLine();
		p.setId((int)(Math.random()*1000000000));
		System.out.println("Enter Product Title: ");
		p.setTitle(sc.nextLine());
		System.out.println("Enter Product Description: ");
		p.setDescription(sc.nextLine());
		System.out.println("Enter Product Price: ");
		p.setPrice(sc.nextDouble());
		System.out.println("Enter Product Quantity: ");
		p.setStock_qty(sc.nextInt());
		
		System.out.println("---- Category Details ----");
		Category c = new Category();
		c.setId((int)(Math.random()*1000000000));
		System.out.println("Enter Name: ");
		c.setName(sc.nextLine());
		System.out.println("Enter Sequence: ");
		c.setSequence(sc.nextInt());
		
		p.setCategory(c);
		
		System.out.println("---- Vendor Details ----");
		Vendor v = new Vendor();
		v.setId((int)(Math.random()*1000000000));
		System.out.println("Enter Vendor Name: ");
		v.setName(sc.nextLine());
		System.out.println("Enter Vendor City: ");
		v.setCity(sc.nextLine());
		
		p.setVendor(v);
		return p;
	}

	public User getUserById() {
		System.out.println("Enter User Id: ");
		int id = sc.nextInt();
		
		entityTransaction.begin();
		User u = entityManager.find(User.class, id);
		entityTransaction.commit();
		
		return u;
	}

	public void saveProduct(Product product) {
		entityTransaction.begin();
		entityManager.persist(product);
		entityTransaction.commit();
	}

	public Product updateProduct(Product p) {
		System.out.println("Current Details: ");
		System.out.println(p.toString());
		
		sc.nextLine();
		System.out.println("Enter Title ("+ p.getTitle() +") \nPress 'Y' to retain current Title: ");
		String title = sc.nextLine();
		if (!title.equals("Y"))
			p.setTitle(title);
		
		System.out.println("Enter Description\nPress 'Y' to retain current Description: ");
		String desc = sc.nextLine();
		if (!desc.equals("Y"))
			p.setDescription(desc);
		
		System.out.println("Enter Price ("+ p.getPrice() +") \nPress 'Y' to retain current Price: ");
		String price = sc.next();
		if (!price.equals("Y"))
			p.setPrice(Double.parseDouble(price));
		
		System.out.println("Enter Quantity ("+ p.getStock_qty() +") \nPress 'Y' to retain current Quantity: ");
		String qty = sc.next();
		if (!qty.equals("Y"))
			p.setPrice(Integer.parseInt(qty));
		
		return p;
	}
	
	
	
}
