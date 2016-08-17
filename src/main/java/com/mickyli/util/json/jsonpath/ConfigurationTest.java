package com.mickyli.util.json.jsonpath;

import java.util.List;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

public class ConfigurationTest {

	/**
	 * [
		   {
		      "name" : "john",
		      "gender" : "male"
		   },
		   {
		      "name" : "ben"
		   }
		]
	 */
	private static String json = "[{\"name\":\"john\",\"gender\":\"male\"},{\"name\":\"ben\"}]";
	
	public static void main(String[] args) {
		//缺省配置
		Configuration conf = Configuration.defaultConfiguration();

		//Works fine
		String gender0 = JsonPath.using(conf).parse(json).read("$[0]['gender']");
		System.out.println(gender0);
		//PathNotFoundException thrown
		try {
			String gender1 = JsonPath.using(conf).parse(json).read("$[1]['gender']");
			System.out.println(gender1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果找不到返回null
		Configuration conf2 = conf.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);

		//Works fine
		String gender3 = JsonPath.using(conf2).parse(json).read("$[0]['gender']");
		System.out.println(gender3);
		//Works fine (null is returned)
		String gender4 = JsonPath.using(conf2).parse(json).read("$[1]['gender']");
		System.out.println(gender4);
		
		
		/**
		 * Option.ALWAYS_RETURN_LIST
		 * 
		 * 返回空的list
		 */
//		//Works fine
//		List<String> genders0 = JsonPath.using(conf).parse(json).read("$[0]['gender']");
//		System.out.println(genders0);
//		//PathNotFoundException thrown
//		List<String> genders1 = JsonPath.using(conf).parse(json).read("$[1]['gender']");
//		System.out.println(genders1);
	}
}
