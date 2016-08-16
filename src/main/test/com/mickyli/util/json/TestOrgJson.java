package com.mickyli.util.json;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.mickyli.util.json.orgjson.OrgJsonTest;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestOrgJson {

	@Test
	public void buildJSONObjectTest(){
		OrgJsonTest.buildJSONObject();
	}
	@Test
	public void parseJSONObjectTest(){
		OrgJsonTest.parseJSONObject();
	}
	@Test
	public void parseJSONArrayTest(){
		OrgJsonTest.parseJSONArray();
	}
	@Test
	public void parseJSONObjectAndArrayTest(){
		OrgJsonTest.parseJSONObjectAndArray();
	}
	@Test
	public void buildJSONObjectAndArrayTest(){
		OrgJsonTest.buildJSONObjectAndArray();
	}
	@Test
	public void buildJSONStringerTest(){
		OrgJsonTest.buildJSONStringer();
	}
	@Test
	public void buildMultiJSONStringerTest(){
		OrgJsonTest.buildMultiJSONStringer();
	}
	@Test
	public void JSONTokenerTest(){
		OrgJsonTest.JSONTokener();
	}
	@Test
	public void buildJsonTest(){
		OrgJsonTest.buildJson();
	}
}
