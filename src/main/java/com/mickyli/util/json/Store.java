package com.mickyli.util.json;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {

	private Bicycle bicycle;
	private List<Book> books;
}
