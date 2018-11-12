package com.qingteng.demo.respository;


import com.qingteng.demo.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @DESC:PersonRepository
 * @author:timebusker
 * @date:2018/9/6
 */
public interface PersonRepository extends AbstractBasicRepository, MongoRepository<Person, Integer> {

}