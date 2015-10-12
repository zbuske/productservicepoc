package com.zbuske.productservicepoc.service;


import java.util.ArrayList;
import java.util.List;

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
		Integer intId = null;
	    try {
	    	intId = Integer.parseInt(id);
	    } catch (Exception e) {
	        return id + "is invlid. id must be numberic ";
	    }
		return productDao.selectById(intId).toString();
	}
	
	@GET
	@Path("byIds")
	@Produces(MediaType.TEXT_PLAIN)
	public String getByIds(@QueryParam("ids") String ids){	
		List<Integer>intIds = new ArrayList<Integer>();
		String[] stringIds = ids.split(",");
		for(int i = 0; i < stringIds.length; i++){
			try {
		    intIds.add(Integer.parseInt(stringIds[i]));
		    } catch (NumberFormatException e) {
		    	
		        return stringIds[i] + "is invlid. id must be numberic ";
		    }
		}
		System.out.println("************ ids = " + ids);
		System.out.println("************ intIds = " + intIds);
		return productDao.selectByIds(intIds).toString();
	}

	
	@GET
	@Path("byCategory")
	@Produces(MediaType.TEXT_PLAIN)
	public String getByCategory(@QueryParam("category") String category){	
		return productDao.selectByCategory(category).toString();
	}

}
