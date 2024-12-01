package com.sms.sms.db.repository;

import com.sms.sms.db.HibernateUtil;
import com.sms.sms.exceptions.FailedToStartHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class JpaRepositoryImpl<T, ID> implements JpaRepository<T, ID> {
    private final Class<T> entityClass;

    public JpaRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T save(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new FailedToStartHibernate("Failed on update: "+e.getMessage());
        }
    }

    @Override
    public T findById(ID id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(entityClass, id);
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM " + entityClass.getName(), entityClass).list();
        }
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new FailedToStartHibernate("Failed on update: "+e.getMessage());
        }
    }

    @Override
    public void deleteById(ID id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            T entity = session.get(entityClass, id);
            if (entity != null) {
                session.remove(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new FailedToStartHibernate("Failed on update: "+e.getMessage());
        }
    }
    @Override
    public T update(ID id, T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            T existingEntity = session.get(entityClass, id);
            if (existingEntity != null
                    && existingEntity.equals(findById(id))){
                deleteById(id);
                session.persist(entity);
            }
            transaction.commit();
            return existingEntity;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new FailedToStartHibernate("Failed on update: "+e.getMessage());
        }
    }
}
