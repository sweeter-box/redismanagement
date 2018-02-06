package com.biz.model;

import java.util.Date;

/**   
* @version 1.0   
* @author TianMengJun
* @since JDK 1.8.0_20
* Create at:   2018年2月5日 下午3:33:33   
* Description:  
*
*@param     
*/
/*
 *  Id 字符串类型长度 40,
姓名 (name)字符串类型长度 40,
出生日期(birthday) 日期类型,
备注 (description)字符串类型长度 255,
平均分(avgscore) 整数类型,

 */
public class Student {
	private String id;
	private String name;
	private Date birthday;
	private String description;
	private int avgscore;
	
	public Student() {
	}
	public Student(String id, String name, Date birthday, int avgscore,String description) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.description = description;
		this.avgscore = avgscore;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAvgscore() {
		return avgscore;
	}
	public void setAvgscore(int avgscore) {
		this.avgscore = avgscore;
	}
	

}
