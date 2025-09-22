package com.exemplo.produtos.repository;

import com.exemplo.produtos.config.JPAUtil;
import com.exemplo.produtos.entity.Produto;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {

    public List<Produto> findAll() {
        try (var em = JPAUtil.getEntityManager()) {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);
            return query.getResultList();
        }
    }

    public Optional<Produto> findById(Long id) {
        try (var em = JPAUtil.getEntityManager()) {
            Produto produto = em.find(Produto.class, id);
            return Optional.ofNullable(produto);
        }
    }

    public Produto save(Produto produto) {
        var em = JPAUtil.getEntityManager();
        var transaction = em.getTransaction();

        try {
            transaction.begin();

            if (produto.getId() == null) {
                em.persist(produto);  // INSERT
            } else {
                produto = em.merge(produto);  // UPDATE
            }

            transaction.commit();
            return produto;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void deleteById(Long id) {
        var em = JPAUtil.getEntityManager();
        var transaction = em.getTransaction();

        try {
            transaction.begin();

            var produto = em.find(Produto.class, id);
            if (produto != null) {
                em.remove(produto);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}