package com.zbuske.productservicepoc;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zbuske.productservicepoc.model.Product;

public class ProductMapperTest {
	private SqlSession session;
	

	@Before
	public void setup() throws IOException {

		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		session = sqlSessionFactory.openSession();

		seedData();

	}

	private void seedData() {
		int dropTable = session.update("ProductMapper.dropTable");
		System.out.println("drop table " + dropTable);
		int createTable = session.update("ProductMapper.createTable");
		System.out.println("created table " + createTable);
		Product product1 = new Product(5555, "AEX143", "Stroller", "baby", 199.99, Date.valueOf("2014-05-23"));
		Product product2 = new Product(5543, "IOL123", "Optimus Prime", "toys", 13.37, Date.valueOf("2014-02-01"));
		Product product3 = new Product(7563, "XYZ904", "Sega Genesis", "toys", 149.99, Date.valueOf("1989-04-01"));
		
		session.insert("insert", product1);
		
		session.insert("insert",product2);
		session.insert("insert", product3);
	}

	@Test
	public void testGetById() {
		Integer productId = 5555;
		Product product = session.selectOne("ProductMapper.getById", productId);

		assertEquals(productId, product.getId());
	}
	@Test
	public void testGetByCategory() {
		String category = "toys";
		List<Object> products = session.selectList("ProductMapper.getByCategory", category);

		assertEquals(2, products.size());
	}

	
	public void testGetByIds() {
		String[] productIds = {"5555","5543"};
		List products= (List) session.selectList("ProductMapper.getByIds", productIds);

		assertEquals(2, products.size());
	}

	
	@After
	public void tearDown() {
		if (session != null) {
			session.close();
		}
	}
}
