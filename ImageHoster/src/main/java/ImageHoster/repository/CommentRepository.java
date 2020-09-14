package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.CriteriaDeleteImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

@Repository
public class CommentRepository {
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public Comment createComment(Comment comment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return comment;
    }
    /*public Comment findComment(Comment comment) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Comment> typedQuery = em.createQuery("SELECT c from comment c where c.comment =:comment", Comment.class).setParameter("comment", comment);
            return typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }*/

    public boolean deleteComment(Comment comment) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.remove(comment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        return false;
    }


}
