package com.qingteng.demo.respository.impl;

import com.qingteng.demo.entity.Person;
import com.qingteng.demo.respository.AbstractBasicRepository;

import com.qingteng.demo.utils.MongoDBPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @DESC:AbstractBasicRepositoryImpl
 * @author:timebusker
 * @date:2018/9/6
 */
@Repository("personRepository")
public class PersonRepositoryImpl implements AbstractBasicRepository<Person> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Person findOneByParams(Map params) {
        Query query = new Query();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            query.addCriteria(Criteria.where(key).is(params.get(key)));
        }
        Person personEntity = mongoTemplate.findOne(query, Person.class);
        return personEntity;
    }

    @Override
    public List<Person> findByParams(Map params) {
        Query query = new Query();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            query.addCriteria(Criteria.where(key).lt(params.get(key)));
        }
        List<Person> list = mongoTemplate.find(query, Person.class);
        return list;
    }

    @Override
    public List<Person> findWithPageByParams(Map params) {
        Query query = new Query();
        MongoDBPageable pageable = new MongoDBPageable(2);
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            query.addCriteria(Criteria.where(key).lt(params.get(key)));
        }
        Long allcount = mongoTemplate.count(query, Person.class);
        List<Person> list = mongoTemplate.find(query.with(pageable), Person.class);
        Page<Person> page = new PageImpl<Person>(list, pageable, allcount);
        return page.getContent();
    }
}