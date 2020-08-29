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

		final String jsonWith33Orders = getJsonFromFile.extractJsonFromFile("json", "jsonWith33Orders.json");
		final String jsonWith35Orders = getJsonFromFile.extractJsonFromFile("json", "jsonWith35Orders.json");

		final OrderListDTO orderListWith33Orders = (OrderListDTO) jsonUtils.jsonStringToDto(jsonWith33Orders, OrderListDTO.class);
		final OrderListDTO orderListWith35Orders = (OrderListDTO) jsonUtils.jsonStringToDto(jsonWith35Orders, OrderListDTO.class);

		final List<String> ordersNotRepeated = new ArrayList<String>();

		if (!CollectionUtils.isEmpty(orderListWith33Orders.getOrderList()) && !CollectionUtils.isEmpty(orderListWith35Orders.getOrderList())) {

			for (final String order : orderListWith35Orders.getOrderList()) {

				if (!orderListWith33Orders.getOrderList().contains(order)) {
					ordersNotRepeated.add(order);
				}
			}

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
