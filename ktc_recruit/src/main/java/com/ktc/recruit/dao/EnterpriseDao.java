package com.ktc.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ktc.recruit.pojo.Enterprise;

import java.util.List;

/**
 * @Description 企业 数据访问接口
 * @date 2020-11-25 12:01:15
*/
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{

    /**
     *	查询热门企业
     */
    List<Enterprise> findTop9ByIshot(String ishot);
}

