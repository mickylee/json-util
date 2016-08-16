package com.mickyli.util.json.orgjson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public class OrgJsonTest {
	
	/**
	 * JSONObject 
	 * 
	 * 	是一个无序的键/值对集合
	 * 	使用get()和opt()方法通过键来访问值，和使用put()方法通过键来添加或者替代值的对象
	 *  值可以是任何这些类型：Boolean,JSONArray,JSONObject,Number和String，或者JOSONObject.NULL对象
	 * @return
	 */
	public static String prepareJSONObject(){  
        JSONObject studentJSONObject = new JSONObject();  
        try {  
            studentJSONObject.put("name", "Jason");  
            studentJSONObject.put("id", 20130001);  
            studentJSONObject.put("phone", "13579246810");  
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
          
        return studentJSONObject.toString();  
    }  
	
	/**
	 * JSONObject有很多optXXX方法，比如optBoolean, optString, optInt... 
	 * 如果这个jsonObject有这个属性，则返回这个属性，否则返回一个默认值
	 */
	public static void parseJSONObject(){
		JSONObject jsonObj = new JSONObject("{'name':'xiazdong','age':20,'status':true}"); 
	    String name = jsonObj.getString("name"); 
	    int age = jsonObj.getInt("age"); 
	    boolean status = jsonObj.getBoolean("status");
	    System.out.println("name = " + name + ",age = " + age + ",status = " + status); 
	}
	/**
	 * JSONArray
	 * 	有序的序列值
	 * 	使用get()和opt()方法通过索引来访问值，和使用put()方法来添加或修改值的对象
	 *  值可以是任何这些类型：Boolean,JSONArray,JSONObject,Number,和String，或者JSONObject.NULL对象
	 */
	public static void parseJSONArray(){
		JSONArray jsonarray = new JSONArray("[{'name':'xiazdong','age':20},{'name':'xzdong','age':15}]"); 
		for (int i = 0; i < jsonarray.length(); i++) { 
			JSONObject jsonobj = jsonarray.getJSONObject(i); 
	        String name = jsonobj.getString("name"); 
	        int age = jsonobj.getInt("age"); 
	        System.out.println("name = " + name + ",age = " + age); 
	    } 
	}
    
	public static void parseJSONObjectAndArray() { 
		String jsonstring = "{'name':'xiazdong','age':20,'book':['book1','book2']}";
		JSONObject jsonobj = new JSONObject(jsonstring);

		String name = jsonobj.getString("name");
		System.out.println("name" + ":" + name);

		int age = jsonobj.getInt("age");
		System.out.println("age" + ":" + age);

		JSONArray jsonarray = jsonobj.getJSONArray("book");
		for (int i = 0; i < jsonarray.length(); i++) {
			String book = jsonarray.getString(i);
			System.out.println("book" + i + ":" + book);
		}
	}
	
	public static String prepareJSONObjectAndArray(){  
        JSONObject studentJSONObject = new JSONObject(); 
        JSONObject book1 = new JSONObject();
        JSONObject book2 = new JSONObject();
        try {  
            studentJSONObject.put("name", "Jason");  
            studentJSONObject.put("id", 20130001);  
            studentJSONObject.put("phone", "13579246810");
            
            book1.put("no", 156);
            book1.put("price", 39.9);
            
            book2.put("no", 43);
            book2.put("price", 21);
            JSONArray jsonarray = new JSONArray();
            jsonarray.put(book1);
            jsonarray.put(book2);
            
            studentJSONObject.put("book", jsonarray);
            
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
          
        return studentJSONObject.toString();  
    }
	
	/**
	 * 快速构造JSON文本的工具
	 * JSONWriter的子类 
	 * object():开始一个对象,endObject()结束一个对象
	 * array():开始一个数组,endArray():结束一个数组
	 * key():表示添加一个key；value():表示添加一个value 
	 * @return
	 */
    public static String prepareJSONStringer(){  
        JSONStringer jsonStringer = new JSONStringer();
        try {  
            jsonStringer.object();  
            jsonStringer.key("name");  
            jsonStringer.value("Jason");  
            jsonStringer.key("id");  
            jsonStringer.value(20130001);  
            jsonStringer.key("phone");  
            jsonStringer.value("13579246810");  
            
            jsonStringer.key("statuses"); 
            jsonStringer.array();
            
            jsonStringer.object();
            jsonStringer.key("status");  
            jsonStringer.value(true); 
            jsonStringer.endObject();
            
            jsonStringer.object();
            jsonStringer.key("status");  
            jsonStringer.value(false); 
            jsonStringer.endObject();
            
            jsonStringer.endArray();
            
            jsonStringer.endObject();  
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
        return jsonStringer.toString();  
    }  
	
    public static String prepareMultiJSONStringer(){ 
		JSONStringer jsonStringer = new JSONStringer();
		JSONObject obj6 = new JSONObject();
		obj6.put("title", "book1").put("price", "$11");
		JSONObject obj3 = new JSONObject();
		obj3.put("book", obj6);
		obj3.put("author", new JSONObject().put("name", "author-1"));

		JSONObject obj5 = new JSONObject();
		obj5.put("title", "book2").put("price", "$22");
		JSONObject obj4 = new JSONObject();
		obj4.put("book", obj5);
		obj4.put("author", new JSONObject().put("name", "author-2"));

		JSONArray obj2 = new JSONArray();
		obj2.put(obj3).put(obj4);

		JSONObject obj1 = new JSONObject();
		obj1.put("title", "BOOK");
		obj1.put("signing", obj2);

		jsonStringer.object().key("session").value(obj1).endObject();
		
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream("data/1.txt"));
			out.println(jsonStringer.toString()); 
		    out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		return jsonStringer.toString();
	}
    /**
     * JSONTokener 
     *  解析JSON源字符串 
     */
    public static void JSONTokenerTest1() { 
		try {
			JSONObject jsonobj = new JSONObject(new JSONTokener(new FileReader(new File("data/1.txt"))));
			System.out.println(jsonobj.getJSONObject("session").getJSONArray("signing").getJSONObject(1).getJSONObject("book").getString("title")); 
		} catch (JSONException | FileNotFoundException e) {
			e.printStackTrace();
		} 
        
    }
    
	public static void main(String[] args) {
		
		System.out.println(prepareJSONObject());  
        System.out.println(prepareJSONStringer());
        
        parseJSONObject();
        
        parseJSONArray();
        
        parseJSONObjectAndArray();
        
        System.out.println(prepareJSONObjectAndArray());
        System.out.println(prepareMultiJSONStringer());
        
        JSONTokenerTest1();
	}

}
