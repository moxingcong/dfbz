package com.ktc.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ktc.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description 问题 数据访问接口
 * @date 2020-11-25 18:22:37
*/
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    @Query("select p from Problem p where p.id in ( select pl.problemid from Pl pl where pl.labelid = ?1 ) order by p.replytime desc ")
    Page<Problem> findNewlistByLabelid(String labelid, PageRequest of);

    @Query("select p from Problem p where p.id in ( select pl.problemid from Pl pl where pl.labelid = ?1 ) order by p.reply desc")
    Page<Problem> findHotlistByLabelid(String labelid, PageRequest of);

    @Query("select p from Problem p where p.id in ( select pl.problemid from Pl pl where pl.labelid = ?1 ) and p.reply=0 ")
    Page<Problem> findWaitlistByLabelid(String labelid, PageRequest of);
}

