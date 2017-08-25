package com.msc.dao;

import com.msc.model.Friendship;
import com.msc.model.User;
import com.msc.model.UserRole;
import com.msc.model.Wall;
import org.hibernate.Query;
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
    public void addFriend(String currentUser, String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        User u = (User) session.load(User.class, new String(currentUser));
        User x = (User) session.load(User.class, new String(userName));
        Friendship f = new Friendship(u,x,0,u);
        session.persist(f);
    }

    @Override
    @Transactional
    public void acceptFriendRequest(String currentUser, String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Friendship f set f.status = 1"+
                " where f.user2 =:current and f.user1 =:user")
                .setString("current", currentUser)
                .setString("user",userName);
        query.executeUpdate();
    }

    @Override
    public void declineFriendRequest(String currentUser, String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Friendship f set f.status = 2"+
                " where f.user2 =:current and f.user1 =:user")
                .setString("current", currentUser)
                .setString("user",userName);
        query.executeUpdate();
    }

    @Override
    public void blockUser(String currentUser, String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Friendship f set f.status = 3"+
                " where f.user2 =:current and f.user1 =:user")
                .setString("current", currentUser)
                .setString("user",userName);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public List<Friendship> listFriendRequests(String currentUser) {
        Session session = this.sessionFactory.getCurrentSession();

        // Must use setString here as setParameter throws reflection getter error
        List<Friendship> userList = session.createQuery("from Friendship f " +
                "where f.user2 = :name and f.status = 0").setString("name",currentUser).list();
        return userList;
    }

    @Override
    @Transactional
    public List<Wall> showUserWall(String currentUser) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Wall> commentList = session.createQuery("from Wall w " +
                "where w.wallOwner = :name").setString("name",currentUser).list();
        return commentList;
    }

    @Override
    public void addComment(String currentUser, String userName, String comment) {
        Session session = this.sessionFactory.getCurrentSession();
        User u = (User) session.load(User.class, new String(currentUser));
        User x = (User) session.load(User.class, new String(userName));
        Wall w = new Wall(x, u, comment);
        session.persist(w);
    }

    @Override
    public List<User> searchUsers(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User u " +
                "where u.username like :name").setString("name", "%" + username + "%").list();
        return userList;
    }

}
