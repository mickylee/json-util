package com.mickyli.util.json.fastjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.mickyli.util.json.Address;
import com.mickyli.util.json.Order;

/**
 * 
 * fastjson生成json字符串(JavaBean,List<JavaBean>,List<String>,List<Map<String,Object>)
 * 		String jsonStrng = JSON.toJSONString(object);
 * 
 * fastjson 解析json字符串为四种类型:
 * 
 * 	1. JavaBean
 * 
 * 		Person person = JSON.parseObject(jsonString, Person.class);
 * 
 * 	2. List<JavaBean>
 * 
 * 		List<Person> listPerson =JSON.parseArray(jsonString, Person.class);
 * 
 * 	3. List<String>
 * 
 * 		List<String> listString = JSON.parseArray(jsonString, String.class);
 * 
 * 	4. List<Map<String,Object>>
 * 
 * 		List<Map<String, Object>> listMap = JSON.parseObject(jsonString, new TypeReference<List<Map<String,Object>>>(){});
 * 
 * @author liqian
 *
 */
public class FastJsonTest {

	//无序
	public static void jsonObject() {
		String jsonStr = "{\"JACKIE_ZHANG\":\"张学友\",\"ANDY_LAU\":\"刘德华\",\"LIMING\":\"黎明\",\"Aaron_Kwok\":\"郭富城\"}";

		// 做5次测试
		for (int i = 0; i < 5; i++) {
			JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			for (java.util.Map.Entry<String, Object> entry : jsonObject.entrySet()) {
				System.out.print(entry.getKey() + "-" + entry.getValue() + "\t");
			}
			System.out.println();// 用来换行
		}
	}

	// 有序
	public static void jsonArray() {
		String jsonStr = "[{\"JACKIE_ZHANG\":\"张学友\"},{\"ANDY_LAU\":\"刘德华\"},{\"LIMING\":\"黎明\"},{\"Aaron_Kwok\":\"郭富城\"}]";
		// 做5次测试
		for (int i = 0; i < 5; i++) {
			JSONArray jsonArray = JSONArray.parseArray(jsonStr);

			for (int k = 0; k < jsonArray.size(); k++) {
				System.out.print(jsonArray.get(k) + "\t");
			}
			System.out.println();// 用来换行
		}
	}
	
	public static void main(String[] args) {
		jsonArray();
		jsonObject();
		
		// 准备数据
		Address a1 = new Address("上海", "上海","浦东");
		Address a2 = new Address("江苏", "苏州","昆山");
		Address a3 = new Address("浙江", "杭州","萧山");
		Order o1 = new Order(1,49.9,a1);
		Order o2 = new Order(2,132.8,a2);
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

		/* Object --> JSON */
		String object2json = JSON.toJSONString(o1);
		System.out.println("Object ----> JSON");
		System.out.println(object2json);
		System.out
				.println("------------------------------------------------------");

		/* List<Object> --> JSON */
		String listforjson = JSON.toJSONString(orderList);
		System.out.println("List<Object> ----> JSON");
		System.out.println(listforjson);
		System.out
				.println("------------------------------------------------------");

		/* Map<String,Object> ----> JSON */
		String map4json = JSON.toJSONString(orderMap);
		System.out.println("Map<String,Object> ----> JSON");
		System.out.println(map4json);
		System.out
				.println("------------------------------------------------------");

		/* JSON --> Object */
		json2object = JSON.parseObject(object2json, Order.class);
		System.out.println("JSON ----> Object");
		System.out.println(json2object);
		System.out
				.println("------------------------------------------------------");
		/* JSON --> List<Object> */
		json2list = JSON.parseArray(listforjson, Order.class);
		System.out.println("JSON --> List<Object>");
		System.out.println(json2list.toString());
		System.out
				.println("------------------------------------------------------");
		/* JSON --> Map<String,Object> */
		json2map = JSON.parseObject(map4json, new TypeReference<Map<String, Order>>() {});
		System.out.println("JSON --> Map<String,Object>");
		System.out.println(json2map.toString());
	}
}
