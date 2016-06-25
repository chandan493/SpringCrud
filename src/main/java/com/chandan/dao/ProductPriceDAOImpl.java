package com.chandan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chandan.model.ProductPrice;

@Repository
public class ProductPriceDAOImpl implements ProductPriceDAO {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public ProductPrice findPriceByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		String sql = "SELECT * FROM products_price WHERE product_name=:name";
		ProductPrice result = namedParameterJdbcTemplate.queryForObject(sql,
				params, new ProductMapper());
		return result;
	}

	private static final class ProductMapper implements RowMapper<ProductPrice> {
		public ProductPrice mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			ProductPrice productPrice = new ProductPrice();
			productPrice.setProductId(Integer.parseInt(rs
					.getString("product_id")));
			productPrice.setProductName(rs.getString("product_name"));
			productPrice.setProductPrice(Integer.parseInt(rs
					.getString("product_price")));
			return productPrice;
		}
	}

}