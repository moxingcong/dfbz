package com.ktc.spit.service;

import com.ktc.spit.dao.SpitDao;
import com.ktc.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;
/**
 * Description:
 * date: 2020/11/26 17:13
 *
 * @author mxc
 */
@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    public void update(Spit spit){
        spitDao.save(spit);
    }

    public void save(Spit spit){
        spit.setId(idWorker.nextId()+"");
        spit.setPublishtime(new Date());//发布时间
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数

        if(spit.getComment()==0){
            spit.setComment(0);//回复数
        }
        spit.setState("1");//状态

        if (spit.getParentid()!=null && !spit.getParentid().equals("")){
            //说明是评论，那么对应的吐槽回复数量应该自增1

            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));

            Update update = new Update();
            update.inc("comment",1);

            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    public void deleteById(String id){
        spitDao.deleteById(id);
    }

    //根据吐槽id查询评论
    public Page<Spit> comment(String parentid, int page, int size) {
        return spitDao.findByParentid(parentid, PageRequest.of(page-1,size));
    }

    public void thumbup(String spitid) {
        //创建查询对象
        Query query = new Query();
        //拼接条件
        query.addCriteria(Criteria.where("_id").is(spitid));

        //创建修改对象
        Update update = new Update();
        update.inc("thumbup", 1);

        //db.spit.update({_id:"1144814063860137984"},{$inc:{thumbup:NumberInt(1)}})
        //拼接条件和修改结果,选中要改的文档(集合)
        mongoTemplate.updateFirst(query,update,"spit");
    }

    //取消对应的点赞
    public void delthumbup(String spitid) {

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitid));

        Update update = new Update();
        //让thumbup-1
        update.inc("thumhup",-1);

        mongoTemplate.updateFirst(query,update,"spit");
    }
}
