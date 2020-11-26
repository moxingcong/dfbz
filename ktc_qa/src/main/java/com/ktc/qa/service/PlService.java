package com.ktc.qa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;
import com.ktc.qa.dao.PlDao;
import com.ktc.qa.pojo.pl;

/**
 * @Description tb_pl 服务层
 * @author admin
 * @date 2020-11-25 18:21:41
*/
@Service
public class PlService {

	@Autowired
	private PlDao plDao;

	@Autowired
	private IdWorker idWorker;

	/**
	* 查询全部列表
	* @return
	*/
	public List<pl> findAll() {
		return plDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<pl> findSearch(Map whereMap, int page, int size) {
		Specification<pl> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return plDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<pl> findSearch(Map whereMap) {
		Specification<pl> specification = createSpecification(whereMap);
		return plDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public pl findById(String id) {
		return plDao.findById(id).get();
	}

	/**
	* 增加
	* @param pl
	*/
	public void add(pl pl) {
		pl.setId( idWorker.nextId()+"" );
		plDao.save(pl);
	}

	/**
	* 修改
	* @param pl
	*/
	public void update(pl pl) {
		plDao.save(pl);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(String id) {
		plDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<pl> createSpecification(Map searchMap) {

		return new Specification<pl>() {

			@Override
			public Predicate toPredicate(Root<pl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

}
