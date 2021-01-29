package com.example.utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class JsonTools {

	private JsonTools() {}


	public static final String extractJsonFromFile(final String nameFolder, final String jsonName) {

		String jsonFile = StringUtils.EMPTY;

		try {

			final String path = new File(".").getCanonicalPath();

			jsonFile = new String(Files.readAllBytes(Paths
				.get(path + "/src/test/resources/" + nameFolder + File.separator + jsonName)));

		} catch (final IOException e) {

			log.error("Error extacting Json from file", e);
		}

		return jsonFile;
	}


	public static final String convertObjectDtoToJsonString(final Object object) {

		String jsonAsString = null;

		final ObjectMapper mapper = new ObjectMapper();

		try {

			jsonAsString = mapper.writeValueAsString(object);

		} catch (final Exception e) {

			log.error("Error convert dto to string", e);
		}

		return jsonAsString;
	}


	public static <T> T convertJsonFileToObjecDto(final String folderName, final String jsonFileName, final Class<T> dtoClass) {

		T result = null;

		try {

			final String jsonFile = extractJsonFromFile(folderName, jsonFileName);

			final ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, Boolean.TRUE);

			result = mapper.readValue(jsonFile, dtoClass);

		} catch (final IOException e) {

			log.error("Error writing object ", e);
		}

		return result;
	}

}