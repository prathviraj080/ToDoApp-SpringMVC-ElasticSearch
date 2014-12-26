package com.util;

import java.io.BufferedReader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtil {
	private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	/**
	 * This method converts a java object into a json string and returns the
	 * json output.
	 *
	 * @param src
	 * @return json string
	 */
	public static String toJson(Object src) {
		return GSON.toJson(src);
	}

	/**
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		return GSON.fromJson(json, clazz);
	}

	/**
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String json, Type type) {
		return GSON.fromJson(json, type);
	}
	
	/***
	 * 
	 * @param bufferedReader
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(BufferedReader br, Type type) {
		return GSON.fromJson(br, type);
	} 
}
