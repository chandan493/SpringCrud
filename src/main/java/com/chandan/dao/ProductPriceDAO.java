package com.chandan.dao;


import com.chandan.model.ProductPrice;

public interface ProductPriceDAO {

	ProductPrice findPriceByName(String productName);


}