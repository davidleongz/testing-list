package com.example.arraylist.testinglist;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class JsonUtils<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

	/**
	 * Convert JSON String to object
	 *
	 * @param <T>
	 *
	 * @param jsonString
	 * @return object
	 */
	@SuppressWarnings("hiding")
	public <T> Object jsonStringToDto(final String jsonString, final Class<T> object) {

		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		Object ojbect = null;

		try {

			ojbect = mapper.readValue(jsonString, object);

		} catch (final JsonGenerationException e) {

			LOGGER.error("Error in object generation", e);

		} catch (final JsonMappingException e) {

			LOGGER.error("Error in object mapping", e);

		} catch (final IOException e) {

			LOGGER.error("Error writing object", e);
		}

		return ojbect;
	}


	@SuppressWarnings("hiding")
	public final <T> String dtoToJsonString(final T object) {

		String jsonAsString = null;

		try {

			final ObjectMapper mapper = new ObjectMapper();

			jsonAsString = mapper.writeValueAsString(object);

		} catch (final JsonGenerationException e) {

			LOGGER.error("Error in object generation ", e);

		} catch (final JsonMappingException e) {

			LOGGER.error("Error in object mapping ", e);

		} catch (final IOException e) {

			LOGGER.error("Error writing object ", e);
		}

		return jsonAsString;
	}



}
