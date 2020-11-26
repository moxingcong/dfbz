package com.ktc.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ktc.recruit.pojo.Recruit;

import java.util.List;

/**
 * @Description 职位 数据访问接口
 * @date 2020-11-25 12:03:19
*/
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);

    List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}

