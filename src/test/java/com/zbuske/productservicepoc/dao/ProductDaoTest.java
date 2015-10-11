package com.zbuske.productservicepoc.dao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.zbuske.productservicepoc.DBBuilder;
import com.zbuske.productservicepoc.model.Product;

public class ProductDaoTest {
	ProductDao productDao;

	@Before
	public void setup() throws Exception {

		productDao = new ProductDao();
		DBBuilder.buildSampleDB();
	}

	@Test
	public void testGetById()  throws Exception {
		Integer productId = 5555;
		Product product = productDao.selectById(productId);

		assertEquals(productId, product.getId());
	}

}
