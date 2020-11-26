package com.ktc.base.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description 标签实体类
 * @author admin
 * @date 2020-11-18 10:57:34
 */
@Entity
@Table(name="tb_label")
public class Label implements Serializable{

	@Id
	private String id;//主键ID

	private String labelname; //标签名称
	private String state; //状态
	private Long count; //使用数量
	private String recommend; //是否推荐
	private Long fans; //粉丝数

	public String getId() {
		return this.id;
	}

	public void setId(String aValue) {
		this.id = aValue;
	}
	
	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}

	public String getLabelname() {
		return this.labelname;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}
	public void setCount(Long count) {
		this.count = count;
	}

	public Long getCount() {
		return this.count;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getRecommend() {
		return this.recommend;
	}
	public void setFans(Long fans) {
		this.fans = fans;
	}

	public Long getFans() {
		return this.fans;
	}

}



