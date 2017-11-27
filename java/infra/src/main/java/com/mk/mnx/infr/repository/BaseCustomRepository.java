package com.mk.mnx.infr.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class BaseCustomRepository {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	protected MongoTemplate getTemplate()
	{
		return mongoTemplate;
	}

}
