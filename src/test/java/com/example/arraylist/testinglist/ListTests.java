package com.example.arraylist.testinglist;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.example.arraylist.dto.OrderListDTO;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ListTests {


	private static final Logger LOGGER = LoggerFactory.getLogger(ListTests.class);

	@Autowired
	GetJsonFromFile getJsonFromFile;

	@Autowired
	JsonUtils jsonUtils;

	@SuppressWarnings("static-access")
	@Test
	public void getValuesNotExistFromArrayList() {

		final String jsonSmallList = getJsonFromFile.extractJsonFromFile("json", "jsonSmallList.json");
		final String jsonLargeList = getJsonFromFile.extractJsonFromFile("json", "jsonLargeList.json");

		final OrderListDTO orderSmallList = (OrderListDTO) jsonUtils.jsonStringToDto(jsonSmallList, OrderListDTO.class);
		final OrderListDTO orderLargeList = (OrderListDTO) jsonUtils.jsonStringToDto(jsonLargeList, OrderListDTO.class);

		final List<String> ordersNotRepeated = new ArrayList<String>();

		if (!CollectionUtils.isEmpty(orderSmallList.getOrderList()) && !CollectionUtils.isEmpty(orderLargeList.getOrderList())) {

			for (final String order : orderLargeList.getOrderList()) {

				if (!orderSmallList.getOrderList().contains(order)) {
					ordersNotRepeated.add(order);
				}
			}

			LOGGER.info("Number of orders: {}", ordersNotRepeated.size());
			LOGGER.info(ordersNotRepeated.toString());
		}

		assertEquals(ordersNotRepeated.size(), 2);
	}

	public String getJsonFromFile(final String jsonName) {

		String jsonFile = null;

		try {

			final String path = new File(".").getCanonicalPath();

			jsonFile = new String(Files.readAllBytes(Paths.get(path + "/src/test/resources/jsonCancellation/" + jsonName)));

		} catch (final IOException e) {
			LOGGER.error("ERROR: ", e);
		}

		return jsonFile;
	}

}
