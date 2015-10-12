package com.zbuske.productservicepoc.dao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

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
	public void testGetById() throws Exception {
		Integer productId = 5555;
		Product product = productDao.selectById(productId);

		assertEquals(productId, product.getId());
	}
	
	@Test
	public void testGetByIds()  throws Exception {
		List<Integer>productIds = Arrays.asList(5555,5543);
		List<Product> products = productDao.selectByIds(productIds);

		assertEquals(2, products.size());
		assertEquals(((Product)products.get(0)).getId(), productIds.get(0));
		assertEquals(((Product)products.get(1)).getId(), productIds.get(1));
	}

	@Test
	public void testGetByCategory() throws Exception {
		String category = "toys";
		Integer id1 = 5543;
		Integer id2 = 7563;
		List<Product> products = productDao.selectByCategory(category);

		assertEquals(2, products.size());
		assertEquals(id1, products.get(0).getId());
		assertEquals(id2, products.get(1).getId());
	}

}
