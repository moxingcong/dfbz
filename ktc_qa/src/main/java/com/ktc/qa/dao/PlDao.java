package com.ktc.qa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ktc.qa.pojo.pl;

/**
 * @Description tb_pl 数据访问接口
 * @date 2020-11-25 18:21:41
*/
public interface PlDao extends JpaRepository<pl,String>,JpaSpecificationExecutor<pl>{

}

