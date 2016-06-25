package com.sapient.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.chandan.dao.ProductPriceDAO;
import com.chandan.dao.ProductPriceDAOImpl;
import com.chandan.model.ProductPrice;

public class ProductDAOTest {

	private EmbeddedDatabase db;

	ProductPriceDAO productPriceDAO;

	@Before
	public void setUp() {
		db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("db/sql/create-db.sql")
				.addScript("db/sql/insert-data.sql").build();
	}

	@Test
	public void testFindByProductName() {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
		ProductPriceDAOImpl priceDAO = new ProductPriceDAOImpl();
		priceDAO.setNamedParameterJdbcTemplate(template);

		ProductPrice productPrice = priceDAO
				.findPriceByName("Samsung Galaxy Note 5");

		Assert.assertNotNull(productPrice);
		Assert.assertEquals("28000", productPrice.getProductPrice());
		Assert.assertEquals("2", productPrice.getProductId());

	}

	@After
	public void tearDown() {
		db.shutdown();
	}

}