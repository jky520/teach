package io.jky.entity;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jky
 * @email jky1988@qq.com
 * @date 2017-06-05 16:30:12
 */
public class ClassRegistrationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//日期分类ID
	private Long timeClassId;
	//日期
	private String day;
	//星期
	private String week;
	//起止日期
	private String startFinishDate;
	//班级
	private String classRoom;
	//授课地点
	private String adress;
	//课程内容
	private String content;
	//课时折算（节）
	private Integer classCount;
	
	private String yearMont;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：日期分类ID
	 */
	public void setTimeClassId(Long timeClassId) {
		this.timeClassId = timeClassId;
	}
	/**
	 * 获取：日期分类ID
	 */
	public Long getTimeClassId() {
		return timeClassId;
	}
	/**
	 * 设置：日期
	 */
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * 获取：日期
	 */
	public String getDay() {
		return day;
	}
	/**
	 * 设置：星期
	 */
	public void setWeek(String week) {
		this.week = week;
	}
	/**
	 * 获取：星期
	 */
	public String getWeek() {
		return week;
	}
	/**
	 * 设置：起止日期
	 */
	public void setStartFinishDate(String startFinishDate) {
		this.startFinishDate = startFinishDate;
	}
	/**
	 * 获取：起止日期
	 */
	public String getStartFinishDate() {
		return startFinishDate;
	}
	/**
	 * 设置：班级
	 */
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	/**
	 * 获取：班级
	 */
	public String getClassRoom() {
		return classRoom;
	}
	/**
	 * 设置：授课地点
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * 获取：授课地点
	 */
	public String getAdress() {
		return adress;
	}
	/**
	 * 设置：课程内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：课程内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：课时折算（节）
	 */
	public void setClassCount(Integer classCount) {
		this.classCount = classCount;
	}
	/**
	 * 获取：课时折算（节）
	 */
	public Integer getClassCount() {
		return classCount;
	}
	
	public String getYearMont() {
		return yearMont;
	}
	
	@Transient
	public void setYearMont(String yearMont) {
		this.yearMont = yearMont;
	}
}
