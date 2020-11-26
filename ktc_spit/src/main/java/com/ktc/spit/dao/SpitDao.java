package com.ktc.spit.dao;

import com.ktc.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * Description:
 * date: 2020/11/26 17:13
 *
 * @author mxc
 */
public interface SpitDao extends MongoRepository<Spit, String> {

    public Page<Spit> findByParentid(String parentid, Pageable pageable);

}
