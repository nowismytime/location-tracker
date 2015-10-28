package com.nearbuy.framework.springbootmongo.dao;

import org.springframework.stereotype.Component;

@Component
public abstract class BaseDao<T> {

public abstract String insertDemo(T model);
	
}
