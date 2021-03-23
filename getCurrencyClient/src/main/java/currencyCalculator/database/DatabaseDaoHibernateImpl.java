package currencyCalculator.database;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import currencyCalculator.service.Exchange;

import java.time.LocalDate;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;

public class DatabaseDaoHibernateImpl implements DatabaseDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseDaoHibernateImpl.class);
    private static final SessionFactory sessionFactory;

    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
                configure("hibernate.cfg.xml").
                build();
        final Metadata metadata = new MetadataSources(registry)
                .buildMetadata();
        sessionFactory = metadata.buildSessionFactory();
    }


    @Override
    public Exchange getByCriteria(String currencyFrom, String currencyTo, LocalDate day) {
        Session session = sessionFactory.openSession();
        Query<ExchangeEntity> query = session.createQuery("FROM ExchangeEntity e WHERE e.currencyFrom = :currencyFrom AND e.currencyTo = :currencyTo AND e.exchangeDate = :day", ExchangeEntity.class);
        query.setParameter("currencyFrom", currencyFrom);
        query.setParameter("currencyTo", currencyTo);
        query.setParameter("day", day);
        ExchangeEntity value = null;
        try {
            value = query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage());
        }

        session.close();
        if (value == null) {
            return null;
        } else {
            return new Exchange(value.getCurrencyFrom(), value.getCurrencyTo(), value.getExchangeRate(), value.getExchangeDate());
        }
    }

    @Override
    public void save(Exchange data) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            ExchangeEntity value = new ExchangeEntity(null, data.getCurrencyFrom(), data.getCurrencyTo(),data.getExchangeRate(),data.getExchangeDate());
            session.save(value);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error(e.getMessage());
        }
        session.close();
    }
}
