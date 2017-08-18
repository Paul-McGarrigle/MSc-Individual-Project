package com.msc.dao;

import com.msc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 24/07/2017.
 */
@Repository
//@Service("userService")
public class JPADAO implements DAO {
    //@PersistenceContext
    //private EntityManager em;
    private static int id = 10;

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }


    /*@Override
    public Collection<User> getUsers() {
        Query query = em.createQuery("from User");
        return (List<User>)query.getResultList();
    }
*/
    @Override
    public void addUser(User u) {
        Session session = this.sessionFactory.getCurrentSession();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(u.getPassword());
        u.setPassword(hashedPassword);
        ++id;
        u.setId(id);
        u.setEnabled(true);
        session.persist(u);
    }

    @Override
    public void updateUser(User u) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(u);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        System.out.println("DAO!!!!!!!!!!!!!!!!!");
        return userList;
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User u = (User) session.load(User.class, new Integer(id));
        return u;
    }

    @Override
    public void removeUser(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        User u = (User) session.load(User.class, new String(username));
        if(null != u){
            session.delete(u);
        }
    }

    @Override
    public User findByUserName(String username) {
        List<User> users = new ArrayList<User>();

        users = sessionFactory.getCurrentSession().createQuery("from User where username=?").setParameter(0, username)
                .list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

}
