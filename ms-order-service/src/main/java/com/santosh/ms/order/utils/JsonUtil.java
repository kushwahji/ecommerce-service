/**
 * 
 */
package com.santosh.ms.order.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author santosh.kushwah
 *
 */
public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	private JsonUtil() {

	}

	public static String writeValuesAsJson(Object obj) {
		String res = "";
		try {
			res = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public static Object readValuesAsObject(String json, Class type) {
		Object ob = null;
		try {
			ob = objectMapper.readValue(json, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ob;
	}
}
