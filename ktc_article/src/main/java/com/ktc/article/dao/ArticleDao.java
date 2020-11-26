package com.ktc.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ktc.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description 文章 数据访问接口
 * @date 2020-11-25 19:42:19
*/
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying
    @Query("update Article a set a.state=1 where a.id=?1")
    void examine(String articleid);

    @Modifying
    @Query("update Article a set a.thumbup=a.thumbup+1 where a.id=?1")
    void thumbup(String articleid);
}

