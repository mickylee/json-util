package com.mickyli.util.json.gson;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mickyli.util.json.Address;
import com.mickyli.util.json.Order;

public class GsonTest {

	public static void main(String[] args) {
		Gson gson = new Gson();
		List<Address> addresses = new ArrayList<Address>();
		for (int i = 0; i < 5; i++) {
			 Address a = new Address();
		     a.setProvince("p" + i);
		     a.setCity("c" + i);
		     a.setArea("a" + i);
		     addresses.add(a);
		}
		String str = gson.toJson(addresses);
		System.out.println(str);
		
		//TypeToken，它是gson提供的数据类型转换器，可以支持各种数据集合类型转换
		List<Address> ps = gson.fromJson(str, new TypeToken<List<Address>>(){}.getType());
		for (int i = 0; i < ps.size(); i++) {
			Address a = ps.get(i);
			System.out.println(a.toString());
		}
		
		
		Address address = new Address("上海","浦东","张江");
		Order order = new Order(1,33.2,address);
		
		String s = gson.toJson(order);
		System.out.println(s);
		
		Order o = gson.fromJson(s, Order.class);
		System.out.println(o.toString());
	}
}
