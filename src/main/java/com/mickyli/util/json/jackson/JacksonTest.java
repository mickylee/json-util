package com.mickyli.util.json.jackson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;

import com.mickyli.util.json.Address;
import com.mickyli.util.json.Order;


public class JacksonTest {
	
	public static ObjectMapper getDefaultObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		// 设置将对象转换成JSON字符串时候:包含的属性不能为空或"";
		// Include.Include.ALWAYS 默认
		// Include.NON_DEFAULT 属性为默认值不序列化
		// Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化
		// Include.NON_NULL 属性为NULL 不序列化
		mapper.setSerializationInclusion(Inclusion.NON_EMPTY);
		// 设置将MAP转换为JSON时候只转换值不等于NULL的
		mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
		mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// 设置有属性不能映射成PO时不报错
		mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		return mapper;
	}

	public static void main(String[] args) throws Exception {
		// 准备数据
		Address a1 = new Address("上海", "上海","浦东");
		Address a2 = new Address("江苏", "苏州","昆山");
		Address a3 = new Address("浙江", "杭州","萧山");
		Order o1 = new Order(1,49.9,a1);
		Order o2 = new Order(2,132,a2);
		Order o3 = new Order(3,19.9,a3);
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(o1);
		orderList.add(o2);
		orderList.add(o3);
		Map<String, Order> orderMap = new HashMap<String, Order>();
		orderMap.put("1", o1);
		orderMap.put("2", o2);
		orderMap.put("3", o3);
		Order json2object = null;
		List<Order> json2list = null;
		Map<String, Order> json2map = null;
		ObjectMapper mapper = getDefaultObjectMapper();

		/* Object --> JSON */
		String object2json = mapper.writeValueAsString(o1);
		System.out.println("Object ----> JSON");
		System.out.println(object2json);
		System.out
				.println("------------------------------------------------------");

		/* List<Object> --> JSON */
		String listforjson = mapper.writeValueAsString(orderList);
		System.out.println("List<Object> ----> JSON");
		System.out.println(listforjson);
		System.out
				.println("------------------------------------------------------");

		/* Map<String,Object> ----> JSON */
		String map4json = mapper.writeValueAsString(orderMap);
		System.out.println("Map<String,Object> ----> JSON");
		System.out.println(map4json);
		System.out
				.println("------------------------------------------------------");

		/* JSON --> Object */
		json2object = mapper.readValue(object2json, Order.class);
		System.out.println("JSON ----> Object");
		System.out.println(json2object);
		System.out
				.println("------------------------------------------------------");
		/* JSON --> List<Object> */
		json2list = mapper.readValue(listforjson, new TypeReference<List<Order>>() {});
		System.out.println("JSON --> List<Object>");
		System.out.println(json2list.toString());
		System.out
				.println("------------------------------------------------------");
		/* JSON --> Map<String,Object> */
		json2map = mapper.readValue(map4json, new TypeReference<Map<String, Order>>() {});
		System.out.println("JSON --> Map<String,Object>");
		System.out.println(json2map.toString());
	}
}
