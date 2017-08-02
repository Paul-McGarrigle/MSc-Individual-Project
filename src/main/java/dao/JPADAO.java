package dao;

import model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by Paul on 24/07/2017.
 */
@Repository
public class JPADAO implements DAO {
    //@PersistenceContext
    private EntityManager em;

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }


    @Override
    public Collection<Users> getUsers() {
        Query query = em.createQuery("from Users");
        return (List<Users>)query.getResultList();
    }

    @Override
    public void addUser(Users u) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(u);
    }

    @Override
    public void updateUser(Users u) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(u);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Users> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Users> userList = session.createQuery("from Users").list();
        return userList;
    }

    @Override
    public Users getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Users u = (Users) session.load(Users.class, new Integer(id));
        return u;
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Users u = (Users) session.load(Users.class, new Integer(id));
        if(null != u){
            session.delete(u);
        }
    }

}
