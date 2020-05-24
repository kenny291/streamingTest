package com.kafkaDemo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
import java.io.FileReader;
import java.util.Iterator;
 
/**
 * @author Crunchify.com
 * How to Read JSON Object From File in Java?
 * https://crunchify.com/how-to-read-json-object-from-file-in-java/
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class JsonHandle {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse("{\"name\": \"John\"}");
			// JsonObject jsonObject = new JsonParser().parse("{\"name\": \"John\"}").getAsJsonObject();

			// Object obj = parser.parse(new FileReader("/Users/fimplus/data.text"));
 
			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;
 
			// A JSON array. JSONObject supports java.util.List interface.
            System.out.println(jsonObject.get("receive_time"));
			System.out.println(jsonObject);
			
 
			// An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
			// Iterators differ from enumerations in two ways:
			// 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
			// 2. Method names have been improved.
			// Iterator<JSONObject> iterator = companyList.iterator();
			// while (iterator.hasNext()) {
			// 	System.out.println(iterator.next());
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}