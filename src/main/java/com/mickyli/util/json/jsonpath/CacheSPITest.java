package com.mickyli.util.json.jsonpath;

import java.util.HashMap;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.cache.Cache;
import com.jayway.jsonpath.spi.cache.CacheProvider;

/**
 * 可以自定义缓存策略
 *  JsonPath提供了两种缓存策略
 * 	默认
 * com.jayway.jsonpath.spi.cache.LRUCache,线程安全
 * 其他：
 * com.jayway.jsonpath.spi.cache.NOOPCache 不缓存
 * @author liqian
 *
 */
public class CacheSPITest {

	public static void main(String[] args) {
		//自定义缓存策略
		CacheProvider.setCache(new Cache() {
		    //Not thread safe simple cache
		    private Map<String, JsonPath> map = new HashMap<String, JsonPath>();

		    @Override
		    public JsonPath get(String key) {
		        return map.get(key);
		    }

		    @Override
		    public void put(String key, JsonPath jsonPath) {
		        map.put(key, jsonPath);
		    }
		});
	}
}
