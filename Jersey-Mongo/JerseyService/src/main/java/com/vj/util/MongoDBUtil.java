package com.vj.util;

import java.net.UnknownHostException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDBUtil {
	
	private static MongoDBUtil MongoDBUtil = null;
	
	private static DB DBInstance = null; 
	
	private MongoDBUtil(){
		
	}
	
	public static DB getDBInstance(){
		if(DBInstance == null){
			MongoClient mongo;
			try {
				mongo = new MongoClient("localhost", 27017);
				DBInstance = mongo.getDB("master");
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return DBInstance;
	}
	
	public static MongoDBUtil getInstance(){
		if(MongoDBUtil == null){
			MongoDBUtil = new MongoDBUtil();
		}
		return MongoDBUtil;
	}
	
	//Getting Mongo Operation - Using Spring
	public static MongoOperations getMongoOperation(){
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		return mongoOperation;
	}

}
