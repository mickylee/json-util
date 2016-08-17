package com.mickyli.util.json;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBean {

	private String id;
	private String name;
	private Date date;
	private List cardNum;
	private String[] cardType = {"身份证", "银行卡" , "公车卡"};
	
}
