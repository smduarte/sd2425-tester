package fctreddit.impl.persistence;

import static fctreddit.api.java.Result.error;
import static fctreddit.api.java.Result.ok;
import static fctreddit.api.java.Result.ErrorCode.CONFLICT;
import static fctreddit.api.java.Result.ErrorCode.INTERNAL_ERROR;
import static fctreddit.api.java.Result.ErrorCode.NOT_FOUND;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import fctreddit.api.java.Result;
import fctreddit.api.java.Result.ErrorCode;


/**
 * A helper class to perform POJO (Plain Old Java Objects) persistence, using
 * Hibernate and a backing relational database.
 * 
 * @param <Session>
 */
public class Hibernate {
//	private static Logger Log = Logger.getLogger(Hibernate.class.getName());

	private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";
	private SessionFactory sessionFactory;
	private static Hibernate instance;

	private Hibernate() {
		try {
			sessionFactory = new Configuration().configure(new File(HIBERNATE_CFG_FILE)).buildSessionFactory();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the Hibernate instance, initializing if necessary. Requires a
	 * configuration file (hibernate.cfg.xml)
	 * 
	 * @return
	 */
	synchronized public static Hibernate getInstance() {
		if (instance == null)
			instance = new Hibernate();
		return instance;
	}

	public Result<Void> persistOne(Object  obj) {
		return execute( (hibernate) -> {
			hibernate.persist( obj );
		});
	}

	public <T> Result<T> updateOne(T obj) {
		return execute( hibernate -> {
			var res = hibernate.merge( obj );
			if( res == null)
				return Result.error( ErrorCode.NOT_FOUND );
			
			return Result.ok( res );
		});
	}
	
	public <T> Result<T> deleteOne(String id, Class<T> clazz) {
		return execute( hibernate -> {
			var res = hibernate.find(clazz, id);
			if( res != null ) {
				hibernate.remove( res );
				return Result.ok( res );
			}
			else
				return error( NOT_FOUND );
		});
	}
	
	public <T> Result<T> deleteOne( T obj ) {
		return execute( hibernate -> {
			if( hibernate.contains( obj ) ) {
				hibernate.remove( obj );
				return ok( obj );
			} else
				return error(NOT_FOUND);
		});
	}
	
	
//	public <T> Result<T> getOne(T obj, Class<T> clazz) {
//		try (var session = sessionFactory.openSession()) {
//			var res = session.get(clazz, obj);
//			if (res == null)
//				return Result.error(ErrorCode.NOT_FOUND);
//			else
//				return Result.ok(res);
//		} catch (Exception e) {
//			throw e;
//		}
//	}
	
	public <T> Result<T> getOne(String id, Class<T> clazz) {
		try (var session = sessionFactory.openSession()) {
			var res = session.find(clazz, id);
			if (res == null)
				return Result.error(ErrorCode.NOT_FOUND);
			else
				return Result.ok(res);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Result<Integer> sql(String sqlStatement) {
		Transaction tx = null;
		try (var session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			var query = session.createNativeMutationQuery(sqlStatement);
			var modified = query.executeUpdate();
			session.flush();
			tx.commit();
			return Result.ok(modified);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			if( tx != null )
				tx.rollback();
			
			return Result.error(ErrorCode.INTERNAL_ERROR);
		}
	}

	public <T> Result<List<T>> sql(String sqlStatement, Class<T> clazz) {
		try (var session = sessionFactory.openSession()) {
			var query = session.createNativeQuery(sqlStatement, clazz);
			return Result.ok(query.list());
		} catch (Exception e) {
			return Result.error(ErrorCode.INTERNAL_ERROR);
		}
	}
	
	private <T> Result<T> execute(Consumer<Session> proc) {
		return execute( (hibernate) -> {
			proc.accept( hibernate);
			return Result.ok();
		});
	}
	
	private <T> Result<T> execute(Function<Session, Result<T>> func) {
		Transaction tx = null;
		try (var session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			var res = func.apply( session );
			session.flush();
			tx.commit();
			return res;
		}
		catch (ConstraintViolationException __) {
			return Result.error(ErrorCode.CONFLICT);
		}  
		catch (Exception e) {
			e.printStackTrace();
			
			if( tx != null )
				tx.rollback();
			
			throw e;
		}
	}
	
	public <T> Result<T> transaction(Function<ORM, Result<T>> func) {
		Transaction tx = null;
		try (var session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			var res = func.apply( new ORM(session) );
			session.flush();
			tx.commit();
			return res;
		}
		catch (Exception e) {
			e.printStackTrace();
			
			if( tx != null )
				tx.rollback();
			
			return error( INTERNAL_ERROR );
		}
	}
	
	
	public static class ORM {
		final Session session;
		ORM( Session s ) {
			this.session = s;
		}
		
		public <T> Result<T> persistOne( T obj ) {
			try { 
				session.persist(obj);
				return ok( obj );
			} catch( ConstraintViolationException __) {
				return error( CONFLICT );
			}
		}
		
		public <T> Result<T> getOne(String id, Class<T> clazz) {
			var res = session.find(clazz, id );				
			return res != null ? ok( res ) : error( NOT_FOUND );			
		}
		
		public <T> Result<T> updateOne( T obj ) {
			var res = session.merge( obj );
			return res != null ? ok( res ) : error( NOT_FOUND );			
		}
		
		public <T> Result<T> deleteOne( T obj ) {
			session.remove(obj);
			return Result.ok( obj );
		}
		
		public <T> Result<List<T>> select(String sqlStatement, Class<T> clazz) {
			try {
				var query = session.createNativeQuery(sqlStatement, clazz);
				return ok(query.list());
			} catch (Exception e) {
				return error(ErrorCode.INTERNAL_ERROR);
			}
		}
		
		public Result<Integer> update(String sqlStatement) {
			try {
				var query = session.createNativeMutationQuery(sqlStatement);
				return ok(query.executeUpdate());
			} catch (Exception e) {
				return error(ErrorCode.INTERNAL_ERROR);
			}
		}
	}
}