package com.zbuske.productservicepoc.service;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

import com.zbuske.productservicepoc.model.Product;

public class ProductServiceTest {

	@Test
	public void testById() throws Exception{
		Client client = ClientBuilder.newClient();
        String expected = "Product [id=5555, sku=AEX143, name=Stroller, category=baby, price=199.99, lastUpdated=2014-05-23]";
			
		String product = client.target("http://localhost:8080/poc/product/byId?id=5555")
			.request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(expected, product);

		
	}

}
