package com.zbuske.productservicepoc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.zbuske.productservicepoc.model.Product;

public class ProductDao {
	private SqlSessionFactory sqlSessionFactory;

	public ProductDao() {
		sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
	}

	public Product selectById(int id) {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			Product product = (Product) session.selectOne("ProductMapper.getById", id);
			return product;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Product> selectByIds(List<Integer> productIds) {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			@SuppressWarnings("rawtypes")
			List products = (List) session.selectList("ProductMapper.getByIds", productIds);
			return (List<Product>) products;
		} finally {
			session.close();
		}
	}

	public List<Product> selectByCategory(String category) {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			List<Product> product = session.selectList("ProductMapper.getByCategory", category);
			return product;
		} finally {
			session.close();
		}
	}

	public int insertProduct(Product product) {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			int id = session.insert("ProductMapper.insert", product);
			session.commit();
			return id;
		} finally {
			session.close();
		}
	}

	public int createTable() {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			int retVal = session.update("ProductMapper.createTable");
			session.commit();
			return retVal;
		} finally {
			session.close();
		}
	}

	public int dropTable() {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			int retVal = session.update("ProductMapper.dropTable");
			session.commit();
			return retVal;
		} finally {
			session.close();
		}
	}

}
