package com.example.arraylist.testinglist;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class GetJsonFromFile {

	private GetJsonFromFile() {}

	private static final Logger LOGGER = LoggerFactory.getLogger(GetJsonFromFile.class);

	public static final String extractJsonFromFile(final String nameFolder, final String jsonName) {

		String jsonFile = StringUtils.EMPTY;

		try {

			final String path = new File(".").getCanonicalPath();

			jsonFile = new String(Files.readAllBytes(Paths
				.get(path + "/src/test/resources/" + nameFolder + File.separator + jsonName)));

		} catch (final IOException e) {

			LOGGER.info("Error extacting Json from file", e);
		}

		return jsonFile;
	}

}
