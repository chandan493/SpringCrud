package com.chandan.web.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chandan.dao.ProductPriceDAO;
import com.chandan.model.ProductPrice;
import com.chandan.vo.ProductPriceVO;

@Controller
public class ProductController {

	private static final Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@Autowired
	ProductPriceDAO productPriceDAO;

	@RequestMapping(value = "/product/price", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getPriceByProductName(
			@RequestBody ProductPriceVO product) {
		HashMap<String, Object> finalResult = new HashMap<String, Object>();
		logger.debug("getting product price");
		ProductPrice productPrice = productPriceDAO.findPriceByName(product
				.getName());
		if (null != productPrice) {
			finalResult.put("status", "OK");
			finalResult.put("result", productPrice);
			return finalResult;
		}
		finalResult.put("status", "OK");
		finalResult.put("result", "No product found");
		return finalResult;
	}

}