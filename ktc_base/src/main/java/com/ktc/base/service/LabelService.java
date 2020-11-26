package com.ktc.base.service;

import com.ktc.base.dao.LabelDao;
import com.ktc.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加标签
     * @param label
     */
    public void add(Label label) {
        //分布式id
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }
    /**
     * 根据id删除标签
     * @param id
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }
    /**
     * 修改标签
     * @param label
     */
    public void update(Label label) {
        labelDao.save(label);
    }
    /**
     * 根据id查询单个标签
     * @param id
     * @return
     */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }
    /**
     * 查询全部标签
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    private Specification<Label> createSpecification(Map searchMap){

        Specification<Label> spec = new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> preList = new ArrayList<>();
                if (searchMap.get("labelname") != null && !searchMap.get("labelname").equals("")){
                    preList.add(cb.like(root.get("labelname").as(String.class), "%" + searchMap.get("labelname") + "%"));
                }
                if (searchMap.get("state") != null && !searchMap.get("state").equals("")) {
                    preList.add(cb.equal(root.get("state").as(String.class), searchMap.get("state")));
                }
                if (searchMap.get("recommend") != null && !searchMap.get("recommend").equals("")) {
                    preList.add(cb.equal(root.get("recommend").as(String.class), searchMap.get("recommend")));
                }

                Predicate[] predArr = new Predicate[preList.size()];
                return cb.and(preList.toArray(predArr));
            }
        };

        return spec;
    }

    /**
     * @return:java.util.List<com.ktc.base.pojo.Label>
     * @Author mxc
     * @Description 根据条件查询标签
     * @Date 2020/11/25 10:40
     **/
    public List<Label> search(Map searchMap) {
        Specification<Label> spec = createSpecification(searchMap);
        return labelDao.findAll(spec);
    }

    /**
     * 条件查询分页
     * @param searchMap
     * @param page
     * @param pageSize
     * @return
     */
    public Page<Label> findPage(Map searchMap, Integer page, Integer pageSize) {
        Specification<Label> spec = createSpecification(searchMap);
        return labelDao.findAll(spec, PageRequest.of(page-1, pageSize));
    }
}
