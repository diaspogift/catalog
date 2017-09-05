package com.dddtraining.catalog.infrastructure;

import com.dddtraining.catalog.domain.model.product.event.ProductPromoted;
import com.dddtraining.catalog.domain.model.product.event.ProductPromotedRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ProductPromotedRepositoryImpl implements ProductPromotedRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private Session session() {
        Session session = (Session) entityManager.unwrap(Session.class);;
        return session;
    }
    @Override
    public void add(ProductPromoted productPromoted) {

        this.session().save(productPromoted);
        return ;
    }
}
