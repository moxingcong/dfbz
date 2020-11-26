package com.ktc.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description tb_pl实体类
 * @author admin
 * @date 2020-11-25 18:21:41
 */
@Entity
@Table(name="tb_pl")
@IdClass(Pl.class)
public class Pl implements Serializable{
	@Id
	private String problemid;

	@Id
	private String labelid;


	public String getProblemid() {
		return problemid;
	}

	public void setProblemid(String problemid) {
		this.problemid = problemid;
	}

	public String getLabelid() {
		return labelid;
	}

	public void setLabelid(String labelid) {
		this.labelid = labelid;
	}

}



