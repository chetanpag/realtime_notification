package com.collective.message;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSonHelper {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static String ObjectToJSonString(Object object) throws JsonGenerationException,
			JsonMappingException, IOException {
		StringWriter sw = new StringWriter();
		mapper.writeValue(sw, object);
		return sw.toString();
	}

	@SuppressWarnings("unchecked")
	public static Object JSONStringToObject(String string, Class clazz) throws JsonParseException,
			JsonMappingException, IOException {
		return mapper.readValue(string, clazz);
	}
}
