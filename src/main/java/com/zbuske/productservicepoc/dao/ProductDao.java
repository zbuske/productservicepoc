package com.zbuske.productservicepoc.dao;

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
