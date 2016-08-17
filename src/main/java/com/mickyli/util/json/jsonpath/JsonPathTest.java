package com.mickyli.util.json.jsonpath;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.ReadContext;
import com.mickyli.util.json.Book;

import static com.jayway.jsonpath.JsonPath.parse;
import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

public class JsonPathTest {

	/**
	 *{
	    "store": {
	        "book": [
	            {
	                "category": "reference",
	                "author": "Nigel Rees",
	                "title": "Sayings of the Century",
	                "price": 8.95
	            },
	            {
	                "category": "fiction",
	                "author": "Evelyn Waugh",
	                "title": "Sword of Honour",
	                "price": 12.99
	            },
	            {
	                "category": "fiction",
	                "author": "Herman Melville",
	                "title": "Moby Dick",
	                "isbn": "0-553-21311-3",
	                "price": 8.99
	            },
	            {
	                "category": "fiction",
	                "author": "J. R. R. Tolkien",
	                "title": "The Lord of the Rings",
	                "isbn": "0-395-19395-8",
	                "price": 22.99
	            }
	        ],
	        "bicycle": {
	            "color": "red",
	            "price": 19.95
	        }
	    },
	    "expensive": 10
	  }
	 */
	private static String json = "{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"Nigel Rees\",\"title\":\"Sayings of the Century\",\"price\":8.95},{\"category\":\"fiction\",\"author\":\"Evelyn Waugh\",\"title\":\"Sword of Honour\",\"price\":12.99},{\"category\":\"fiction\",\"author\":\"Herman Melville\",\"title\":\"Moby Dick\",\"isbn\":\"0-553-21311-3\",\"price\":8.99},{\"category\":\"fiction\",\"author\":\"J. R. R. Tolkien\",\"title\":\"The Lord of the Rings\",\"isbn\":\"0-395-19395-8\",\"price\":22.99}],\"bicycle\":{\"color\":\"red\",\"price\":19.95}},\"expensive\":10}";

	public static void main(String[] args) {
		
		System.out.println(JsonPath.read(json, "$.store.book[*].author"));//The authors of all books
		System.out.println(JsonPath.read(json, "$..author"));//All authors
		System.out.println(JsonPath.read(json, "$.store.*"));//All things, both books and bicycles
		System.out.println(JsonPath.read(json, "$.store..price"));//The price of everything
		System.out.println(JsonPath.read(json, "$..book[2]"));//The third book
		System.out.println(JsonPath.read(json, "$..book[0,1]"));//The first two books
		System.out.println(JsonPath.read(json, "$..book[:2]"));//All books from index 0 (inclusive) until index 2 (exclusive)
		System.out.println(JsonPath.read(json, "$..book[1:2]"));//All books from index 1 (inclusive) until index 2 (exclusive)
		System.out.println(JsonPath.read(json, "$..book[-2:]"));//Last two books
		System.out.println(JsonPath.read(json, "$..book[2:]"));//Book number two from tail
		System.out.println(JsonPath.read(json, "$..book[?(@.isbn)]"));//All books with an ISBN number
		System.out.println(JsonPath.read(json, "$.store.book[?(@.price < 10)]"));//All books in store cheaper than 10
		System.out.println(JsonPath.read(json, "$..book[?(@.price <= $['expensive'])]"));//All books in store that are not "expensive"
		System.out.println(JsonPath.read(json, "$..book[?(@.author =~ /.*REES/i)]"));//All books matching regex (ignore case)
		System.out.println(JsonPath.read(json, "$..*"));//Give me every thing
		System.out.println(JsonPath.read(json, "$..book.length()"));//The number of books
		
		List<String> authors = JsonPath.read(json, "$.store.book[*].author");
		System.out.println(authors);
		
		//先读后解析，不用每次都读
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

		String author0 = JsonPath.read(document, "$.store.book[0].author");
		String author1 = JsonPath.read(document, "$.store.book[1].author");
		
		System.out.println(author0);
		System.out.println(author1);
		
		//流式api
		ReadContext ctx = JsonPath.parse(json);

		List<String> authorsOfBooksWithISBN = ctx.read("$.store.book[?(@.isbn)].author");
		Configuration configuration = Configuration.defaultConfiguration();
		List<Map<String, Object>> expensiveBooks = JsonPath
				.using(configuration)
				.parse(json)
				.read("$.store.book[?(@.price > 10)]", List.class);
		
		System.out.println(authorsOfBooksWithISBN);
		System.out.println(expensiveBooks);
		
		//JsonPath可以自动转换类型
		//Will throw an java.lang.ClassCastException    
		//List<String> list = JsonPath.parse(json).read("$.store.book[0].author");

		//Works fine
		String author = JsonPath.parse(json).read("$.store.book[0].author");
		
		//指定返回类型
		String dateJson = "{\"date_as_long\" : 1411455611975}";
		Date date = JsonPath.parse(dateJson).read("$['date_as_long']", Date.class);
		System.out.println(date);
		
		Book book = JsonPath.parse(json).read("$.store.book[0]", Book.class);
		System.out.println(book);
		
		//&&和||
		List<Map<String, Object>> books1 =  JsonPath.parse(json)
                .read("$.store.book[?(@.price < 10 && @.category == 'fiction')]");
		
		List<Map<String, Object>> books2 =  JsonPath.parse(json)
                .read("$.store.book[?(@.category == 'reference' || @.price > 10)]");
		System.out.println(books1);
		System.out.println(books2);
		
		//自定义filter
		Filter cheapFictionFilter = filter(
				where("category").is("fiction").and("price").lte(10D));

		List<Map<String, Object>> books3 = parse(json).read("$.store.book[?]", cheapFictionFilter);
		
		System.out.println(books3);
		
		Filter fooOrBar = filter(
				where("foo").exists(true)).or(where("bar").exists(true));

		Filter fooAndBar = filter(
				where("foo").exists(true)).and(where("bar").exists(true));
		
		//筛选
		Predicate booksWithISBN = new Predicate() {
		    @Override
		    public boolean apply(PredicateContext ctx) {
		        return ctx.item(Map.class).containsKey("isbn");
		    }
		};

		List<Map<String, Object>> books4 = parse(json).read("$.store.book[?].isbn", List.class, booksWithISBN);
		System.out.println(books4);
		
		//获取path
		Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
		List<String> pathList = JsonPath.using(conf).parse(json).read("$..author");

		System.out.println(pathList);
	}


}
