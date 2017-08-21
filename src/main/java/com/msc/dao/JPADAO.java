package com.msc.dao;

import com.msc.model.Friendship;
import com.msc.model.User;
import com.msc.model.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
    public void addUser(User u, UserRole ur) {
        Session session = this.sessionFactory.getCurrentSession();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(u.getPassword());
        u.setPassword(hashedPassword);
        ++id;
        u.setId(id);
        ur.setUser(u);
        ur.setRole("ROLE_USER");
        session.persist(u);
        session.persist(ur);
    }

    /*@Override
    public void addUserRole(UserRole ur) {
        Session session = this.sessionFactory.getCurrentSession();
        ur
        session.persist(ur);
    }*/

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
        //UserRole ur = (UserRole) session.load(UserRole.class, new String(u.getUsername()));
        if(null != u){
            session.delete(u);
        }
        /*if(ur != null){
            session.delete(new Integer(ur.getUserRoleId()));
        }*/
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

    @Override
    @Transactional
    public void addFriend(String u1, String u2) {
        Session session = this.sessionFactory.getCurrentSession();
        User u = (User) session.load(User.class, new String(u1));
        User x = (User) session.load(User.class, new String(u2));
        Friendship f = new Friendship(u,x,0,u);
        session.persist(f);
    }

    @Override
    @Transactional
    public void acceptFriendRequest(String u1, String u2) {
        /*sessionFactory.getCurrentSession().createQuery("update Friendship set status=1" +
                "where user1=? AND user2=?").setParameter(0, u1).setParameter(1,u2)
                .list();*/
    }

    @Override
    @Transactional
    public List<Friendship> listFriendRequests(String currentUser) {
        //Session session = this.sessionFactory.getCurrentSession();
        //List<Friendship> userList = session.createQuery("FROM Friendship as f WHERE f.user2='user10'").list();
        //.setParameter("name",currentUser)
        //Query query = session.createQuery("FROM Friendship WHERE Friendship.user2  = :name");
        //query.setParameter("name", currentUser);
        Session session = this.sessionFactory.getCurrentSession();
        List<Friendship> userList = session.createQuery("from Friendship f " +
                "where f.user2 = 'user10'").list();
        return userList;
    }

}
