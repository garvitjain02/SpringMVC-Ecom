package com.ecom.controller;

import java.util.List;
import java.util.Scanner;

import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.service.ProductService;
import com.ecom.utility.FactoryUtility;

import jakarta.persistence.EntityManager;

public class ProductController {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EntityManager entityManager = FactoryUtility.getInstance().loadPersistence();
		
		ProductService productService = new ProductService(sc, entityManager, entityManager.getTransaction());
		
		while (true) {
			System.out.println("===== Product Menu ======");
			System.out.println("1 - Add Product with Vendor and Category");
			System.out.println("2 - Fetch all Products");
			System.out.println("3 - Update Product");
			System.out.println("4 - Delete Product");
			System.out.println("0 - Exit");
			
			int input = sc.nextInt();
			if (input == 0)
			{
				System.out.println("Exiting!! ");
				break;
			}
			
			switch (input) {
			case 1 :
				Product product = productService.takeInput ();
				User u = productService.getUserById ();
				product.getVendor().setUser(u);
				productService.saveProduct(product);
				System.out.println("Product Added to Db");
				break;
				
			case 2 :
				List<Product> list = productService.getAllProducts ();
				list.stream().forEach(p -> System.out.println(p));
				break;
				
			case 3 :
				product = productService.getProductById();
				product = productService.updateProduct(product);
				productService.saveProduct(product);
				System.out.println("Product Updated");
				break;
				
			case 4 :
				product = productService.getProductById();
				productService.delete (product);
				System.out.println("Product Deleted from Db");
				break;
				
			default:
				System.out.println("Invalid Input");
				break;
			}
		}

	}
}
