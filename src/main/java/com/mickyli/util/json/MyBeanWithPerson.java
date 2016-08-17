package com.mickyli.util.json;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBeanWithPerson {

	private List<Person> list;
	private Map<String,Person> map;

}
