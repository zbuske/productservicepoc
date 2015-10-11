package com.zbuske.productservicepoc.service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.zbuske.productservicepoc.dao.ProductDao;

@Path("/product")
public class ProductService {
	private static ProductDao productDao;
	public ProductService() {
		productDao = new ProductDao();
	}

	@GET
	@Path("byId")
	@Produces(MediaType.TEXT_PLAIN)
	public String getById(@QueryParam("id") String id){	
		return productDao.selectById(Integer.parseInt(id)).toString();
	}
	
	@GET
	@Path("byCategory")
	@Produces(MediaType.TEXT_PLAIN)
	public String getByCategory(@QueryParam("category") String category){	
		return productDao.selectByCategory(category).toString();
	}

}
