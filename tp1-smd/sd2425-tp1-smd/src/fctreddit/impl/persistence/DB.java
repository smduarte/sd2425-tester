package fctreddit.impl.persistence;

import java.util.List;
import java.util.function.Function;

import fctreddit.api.java.Result;


public class DB {

	public static Result<Integer> exec(String query) {
		return Hibernate.getInstance().sql(query);
	}

	public static <T> Result<List<T>> query(String query, Class<T> clazz) {
		return Hibernate.getInstance().sql(query, clazz);
	}	
	
	public static <T> Result<T> getOne(String id, Class<T> clazz) {
		return Hibernate.getInstance().getOne(id, clazz);
	}
	
	public static <T> Result<T> deleteOne(T obj) {
		return Hibernate.getInstance().deleteOne(obj);
	}
	
	public static <T> Result<T> deleteOne(String id, Class<T> clazz) {
		return Hibernate.getInstance().deleteOne(id, clazz);
	}
	
	public static <T> Result<T> updateOne(T obj) {
		return Hibernate.getInstance().updateOne(obj);
	}
	
	public static <T> Result<T> insertOne( T obj) {
		return Hibernate.getInstance().persistOne(obj).mapValue((__) -> obj );
	}
	
	public static Result<Integer> sqlUpdate( String sqlStatement ) {
		return Hibernate.getInstance().transaction(null);
	}
	
	public static <T> Result<T> transaction( Function<Hibernate.ORM, Result<T>> f) {
		return Hibernate.getInstance().transaction(f);
	}
}
