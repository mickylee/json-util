package com.mickyli.util.json.jackson;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;


public class JacksonUtils {
	private static ObjectMapper mapper = new ObjectMapper(); 
	
	public static <T> T readFromJson(String json,Class<T> c){
		T t = null;
		 try {
			t = mapper.readValue(json, c);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		 return t;
	}
	
	public static String createJsonFromObject(Object obj){
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, obj);
			return sw.toString();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
    /**
     * 将对象转为JSON字符串
     *
     * @param obj
     * @return
     */
    public static String objToJsonString(Object obj) {
        String s = null;
        try {
            s = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return s;
    }

    /**
     * JSON到Map转化
     *
     * @param content
     * @return
     */
	@SuppressWarnings("rawtypes")
	public static Map jsonStringToMap(String content){
        Map map = null;
    	try {
			map = mapper.readValue(content, TypeFactory.type(Map.class));
		} catch (Exception e) {
			e.printStackTrace();
		} 
        return map;
    }
    /**
     * JSON到LIST转化
     *
     * @param 
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static List jsonStringToList(String content) {
        List list = null;
        try {
			list = mapper.readValue(content, TypeFactory.type(List.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
        return list;
    }
}
