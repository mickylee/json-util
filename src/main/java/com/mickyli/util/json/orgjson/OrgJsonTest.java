package com.mickyli.util.json.orgjson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import com.mickyli.util.json.Student;

public class OrgJsonTest {
	
	/**
	 * JSONObject 
	 * 
	 * 	是一个无序的键/值对集合
	 * 	使用get()和opt()方法通过键来访问值，和使用put()方法通过键来添加或者替代值的对象
	 *  值可以是任何这些类型：Boolean,JSONArray,JSONObject,Number和String，或者JOSONObject.NULL对象
	 * @return
	 */
	public static void buildJSONObject(){  
        JSONObject studentJSONObject = new JSONObject();  
        try {  
            studentJSONObject.put("name", "Jason");  
            studentJSONObject.put("id", 20130001);  
            studentJSONObject.put("phone", "13579246810");  
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
        System.out.println(studentJSONObject.toString());
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
	
	public static String buildJSONObjectAndArray(){  
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
    public static void buildJSONStringer(){  
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
        System.out.println(jsonStringer.toString());
    }  
	
    public static void buildMultiJSONStringer(){ 
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
		System.out.println(jsonStringer.toString());
	}
    /**
     * JSONTokener 
     *  解析JSON源字符串 
     */
    public static void JSONTokener() { 
		try {
			JSONObject jsonobj = new JSONObject(new JSONTokener(new FileReader(new File("data/1.txt"))));
			System.out.println(jsonobj.getJSONObject("session").getJSONArray("signing").getJSONObject(1).getJSONObject("book").getString("title")); 
		} catch (JSONException | FileNotFoundException e) {
			e.printStackTrace();
		} 
        
    }
    
    /**
     * 构造Json数据
     * 
     * @return
     * @throws JSONException
     */
    public static void buildJson(){

        // JSON格式数据解析对象
        JSONObject jo = new JSONObject();

        // 下面构造两个map、一个list和一个Student对象
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "Alexia");
        map1.put("sex", "female");
        map1.put("age", "23");

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("name", "Edward");
        map2.put("sex", "male");
        map2.put("age", "24");

        List<Map> list = new ArrayList<Map>();
        list.add(map1);
        list.add(map2);
        
        Student student = new Student();
        student.setId(1);
        student.setName("jack");
        student.setPhone("1388888888");
        student.setStatus(true);

        // 将Map转换为JSONArray数据
        JSONArray ja = new JSONArray();
        ja.put(map1);

        System.out.println("JSONArray对象数据格式：");
        System.out.println(ja.toString());

        // 将Javabean转换为Json数据（需要Map中转）
        JSONObject jo1 = JsonHelper.toJSON(student);

        System.out.println("\n仅含Student对象的Json数据格式：");
        System.out.println(jo1.toString());

        // 构造Json数据，包括一个map和一个含Employee对象的Json数据
        jo.put("map", ja);
        jo.put("student", jo1.toString());
        System.out.println("\n最终构造的JSON数据格式：");
        System.out.println(jo.toString());
    }

    /**
     * 解析Json数据
     * 
     * @param jsonString
     *            Json数据字符串
     * @throws JSONException
     * @throws ParseException
     */
    public static void parseJson(String jsonString) throws JSONException,
            ParseException {

        JSONObject jo = new JSONObject(jsonString);
        JSONArray ja = jo.getJSONArray("map");

        System.out.println("\n将Json数据解析为Map：");
        System.out.println("name: " + ja.getJSONObject(0).getString("name")
                + " sex: " + ja.getJSONObject(0).getString("sex") + " age: "
                + ja.getJSONObject(0).getInt("age"));

        String jsonStr = jo.getString("student");
        Student stu = new Student();
        JsonHelper.toJavaBean(stu, jsonStr);

        System.out.println("\n将Json数据解析为Student对象：");
        System.out.println("name: " + stu.getName() + " sex: " + stu.getSex()
                + " age: " + stu.getAge());

    }
    
}
