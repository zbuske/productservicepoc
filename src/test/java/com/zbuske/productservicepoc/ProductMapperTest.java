package com.zbuske.productservicepoc;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.zbuske.productservicepoc.model.Product;

public class ProductMapperTest {
	private SqlSession session;

	@BeforeClass
	public static void buildDB() throws Exception{
		DBBuilder.buildSampleDB();
	}
	
	@Before
	public void setup() throws Exception {

		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		session = sqlSessionFactory.openSession();

	}


	@Test
	public void testGetById() {
		Integer productId = 5555;
		Product product = session.selectOne("ProductMapper.getById", productId);

		assertEquals(productId, product.getId());
	}

	@Test
	public void testGetByIds() {
		List<Integer> productIds = Arrays.asList(5555, 5543);
		@SuppressWarnings("rawtypes")
		List products = (List) session.selectList("ProductMapper.getByIds", productIds);

		assertEquals(2, products.size());
		assertEquals(((Product) products.get(0)).getId(), productIds.get(0));
		assertEquals(((Product) products.get(1)).getId(), productIds.get(1));

	}

	@Test
	public void testGetByCategory() {
		String category = "toys";
		List<Object> products = session.selectList("ProductMapper.getByCategory", category);

		assertEquals(2, products.size());
	}

	@After
	public void tearDown() {
		if (session != null) {
			session.close();
		}
	}
}
