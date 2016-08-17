package com.mickyli.util.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	private String category;
	private String author;
	private String title;
	private String isbn;
	private double price;
}
