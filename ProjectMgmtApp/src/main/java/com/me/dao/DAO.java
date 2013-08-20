package com.me.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Repository;

@SuppressWarnings("deprecation")
@Repository
public class DAO {

	private static final Logger log = Logger.getAnonymousLogger();
    @SuppressWarnings("rawtypes")
	private static final ThreadLocal session = new ThreadLocal();
    private static final SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();	
	
    protected DAO() {
    }

    @SuppressWarnings("unchecked")
	public static Session getSession() {
        Session session = (Session) DAO.session.get();
       // System.out.println("******session"+session);
        if (session == null) {
            session = sessionFactory.openSession();
           // getSession().beginTransaction();
            DAO.session.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
	protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        DAO.session.set(null);
    }

    @SuppressWarnings("unchecked")
	public static void close() {
        getSession().close();
        DAO.session.set(null);
    }
    
    	
}