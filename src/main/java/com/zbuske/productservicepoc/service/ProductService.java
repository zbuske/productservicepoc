package com.zbuske.productservicepoc.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.zbuske.productservicepoc.dao.ProductDao;
import com.zbuske.productservicepoc.model.Product;

@Path("/product")
public class ProductService {
	private static ProductDao productDao;

	public ProductService() {
		productDao = new ProductDao();
	}

	@GET
	@Path("byId/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getById(@PathParam("id") String id) {
		Integer intId = null;
		try {
			intId = Integer.parseInt(id.trim());
		} catch (Exception e) {
			return id + " is invlid. id must be numeric ";
		}
		Product product = productDao.selectById(intId);
		if (product != null) {
			return product.toString();
		}
		return "product not found for id " + id;
	}

	@GET
	@Path("byIds/{ids}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getByIds(@PathParam("ids") String ids) {
		List<Integer> intIds = new ArrayList<Integer>();
		String[] stringIds = ids.split(",");
		for (int i = 0; i < stringIds.length; i++) {
			try {
				intIds.add(Integer.parseInt(stringIds[i].trim()));
			} catch (NumberFormatException e) {

				return stringIds[i] + " is invlid. id must be numeric ";
			}
		}
		List<Product> products = productDao.selectByIds(intIds);
		if (products != null && !products.isEmpty()) {
			return products.toString();
		}
		return "products not found for ids " + ids;
	}

	@GET
	@Path("byCategory/{category}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getByCategory(@PathParam("category") String category) {
		List<Product> products = productDao.selectByCategory(category);
		if (products != null && !products.isEmpty()) {
			return products.toString();
		}
		return "product not found for category " + category;
	}

}
