package com.zbuske.productservicepoc;

import java.sql.Date;

import com.zbuske.productservicepoc.dao.ProductDao;
import com.zbuske.productservicepoc.model.Product;

public class DBBuilder {
	private static ProductDao productDao;

	public static void buildSampleDB() throws Exception {
		productDao = new ProductDao();
		System.out.println("*********** creating database");

		try {
			int dropTable = productDao.dropTable();
			System.out.println("drop table " + dropTable);
		} catch (Exception ex) {
			// There is no easy way in Derby to see if a table already exists,
			// so I'm just dropping it and handling the exception if it doesn't
			// I obviously wouldn't do this in production code.
		}
		int createTable = productDao.createTable();
		System.out.println("created table " + createTable);

		seedData();

	}

	private static void seedData() {
		Product product1 = new Product(5555, "AEX143", "Stroller", "baby", 199.99, Date.valueOf("2014-05-23"));
		Product product2 = new Product(5543, "IOL123", "Optimus Prime", "toys", 13.37, Date.valueOf("2014-02-01"));
		Product product3 = new Product(7563, "XYZ904", "Sega Genesis", "toys", 149.99, Date.valueOf("1989-04-01"));

		productDao.insertProduct(product1);
		productDao.insertProduct(product2);
		productDao.insertProduct(product3);
	}

}
