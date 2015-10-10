package com.zbuske.productservicepoc.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.zbuske.productservicepoc.model.Product;

public class ProductDaoTest {
	ProductDao productDao;

	@Before
	public void setup() throws IOException, SQLException {

		productDao = new ProductDao();
		int dropTable = productDao.dropTable();
		System.out.println("drop table " + dropTable);
		int createTable = productDao.createTable();
		System.out.println("created table " + createTable);
		
		seedData();

	}

	private void seedData() {
		Product product1 = new Product(5555, "AEX143", "Stroller", "baby", 199.99, Date.valueOf("2014-05-23"));
		Product product2 = new Product(5543, "IOL123", "Optimus Prime", "toys", 13.37, Date.valueOf("2014-02-01"));
		Product product3 = new Product(7563, "XYZ904", "Sega Genesis", "toys", 149.99, Date.valueOf("1989-04-01"));
		
		productDao.insertProduct(product1);	
		productDao.insertProduct(product2);
		productDao.insertProduct(product3);
	}

	@Test
	public void testGetById() {
		Integer productId = 5555;
		Product product = productDao.selectById(productId);

		assertEquals(productId, product.getId());
	}

}
