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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.example.arraylist.dto.OrderListDTO;
import com.example.utils.JsonTools;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ListTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(ListTests.class);

	@Test
	public void getValuesNotExistFromArrayList() {

		OrderListDTO orderSmallList = JsonTools.convertJsonFileToObjecDto("json", "jsonSmallList.json", OrderListDTO.class);
		OrderListDTO orderLargeList = JsonTools.convertJsonFileToObjecDto("json", "jsonLargeList.json", OrderListDTO.class);

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

		assertEquals(ordersNotRepeated.size(), 14);
	}

}
