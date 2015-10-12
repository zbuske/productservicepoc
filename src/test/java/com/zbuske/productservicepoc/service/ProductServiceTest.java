package com.zbuske.productservicepoc.service;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.junit.Ignore;
import org.junit.Test;

//These will only run if the Jetty server is running. Commented out so the tests can all run when I build
@Ignore
public class ProductServiceTest {
	
	
	@Test
	public void testById() throws Exception {
		Client client = ClientBuilder.newClient();
		String expected = "Product [id=5555, sku=AEX143, name=Stroller, category=baby, price=199.99, lastUpdated=2014-05-23]";

		String product = client.target("http://localhost:8080/poc/product/byId?id=5555").request(MediaType.TEXT_PLAIN)
				.get(String.class);
		assertEquals(expected, product);

	}
	
	
	@Test
	public void testByInvalidId() throws Exception {
		Client client = ClientBuilder.newClient();
		String expected = "xxx is invlid. id must be numeric ";

		String product = client.target("http://localhost:8080/poc/product/byId?id=xxx").request(MediaType.TEXT_PLAIN)
				.get(String.class);
		assertEquals(expected, product);

	}
	
	
	@Test
	public void testByNonexistentId() throws Exception {
		Client client = ClientBuilder.newClient();
		String expected = "product not found for id 123";

		String product = client.target("http://localhost:8080/poc/product/byId?id=123").request(MediaType.TEXT_PLAIN)
				.get(String.class);
		assertEquals(expected, product);

	}


	
	@Test
	public void testByIds() throws Exception {
		Client client = ClientBuilder.newClient();
		String expected = "[Product [id=5555, sku=AEX143, name=Stroller, category=baby, price=199.99, lastUpdated=2014-05-23], "
				+ "Product [id=5543, sku=IOL123, name=Optimus Prime, category=toys, price=13.37, lastUpdated=2014-02-01]]";

		String product = client.target("http://localhost:8080/poc/product/byIds?ids=5555,5543")
				.request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(expected, product);
	}
	
	
	@Test
	public void testByinvalidIds() throws Exception {
		Client client = ClientBuilder.newClient();
		String expected = "xxx is invlid. id must be numeric ";

		String product = client.target("http://localhost:8080/poc/product/byIds?ids=xxx,yyy").request(MediaType.TEXT_PLAIN)
				.get(String.class);
		assertEquals(expected, product);

	}

	
	@Test
	public void testByNonexistentIds() throws Exception {
		Client client = ClientBuilder.newClient();
		String expected = "products not found for ids 555,554";

		String product = client.target("http://localhost:8080/poc/product/byIds?ids=555,554").request(MediaType.TEXT_PLAIN)
				.get(String.class);
		assertEquals(expected, product);

	}

	
	@Test
	public void testByCategory() throws Exception {
		Client client = ClientBuilder.newClient();
		String expected = "[Product [id=5543, sku=IOL123, name=Optimus Prime, category=toys, price=13.37, lastUpdated=2014-02-01], "
				+ "Product [id=7563, sku=XYZ904, name=Sega Genesis, category=toys, price=149.99, lastUpdated=1989-04-01]]";

		String product = client.target("http://localhost:8080/poc/product/byCategory?category=toys")
				.request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(expected, product);
	}
	
	
	@Test
	public void testByNonExistentCategory() throws Exception {
		Client client = ClientBuilder.newClient();
		String expected = "product not found for category boats";

		String product = client.target("http://localhost:8080/poc/product/byCategory?category=boats")
				.request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(expected, product);
	}

}
