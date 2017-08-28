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
@Repository// Specifies this Class is to be used as a Repository/Link to Database
public class JPADAO implements DAO {
    private static int id = 10;

    // Used for user Sessions
    private SessionFactory sessionFactory;

    // Constructor for Beans
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    // This Method adds a User to the users & user_roles table
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

    // This Method updates an existing user record
    @Override
    public void updateUser(User u) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(u);
    }

    // This Method queries database for all records in users table
    @SuppressWarnings("unchecked")
    @Override
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        return userList;
    }

    // This Method returns a user by their associated ID
    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User u = (User) session.load(User.class, new Integer(id));
        return u;
    }

    // This Method removes a user record from the users table and all subsequent records of the user
    @Override
    public void removeUser(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        User u = (User) session.load(User.class, new String(username));
        if(null != u){
            session.delete(u);
        }
    }

    // This Method queries the Database to find users by their username
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

    // The next few Methods deal with adding or updating friendship records between users
    // friendship_status 0 = Pending
    // friendship_status 0 = Accepted
    // friendship_status 0 = Declined
    // friendship_status 0 = Blocked
    // friendship_status 0 = Unblocked
    // The design was inspired by http://www.codedodle.com/2014/12/social-network-friends-database.html
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
    public void unBlockUser(String currentUser, String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Friendship f set f.status = 4"+
                " where f.user2 =:current and f.user1 =:user")
                .setString("current", currentUser)
                .setString("user",userName);
        query.executeUpdate();
    }

    // This Method lists any outstanding user requests for the user, i.e friendship_status = 0
    @Override
    @Transactional
    public List<Friendship> listFriendRequests(String currentUser) {
        Session session = this.sessionFactory.getCurrentSession();

        // Must use setString here as setParameter throws reflection getter error
        List<Friendship> userList = session.createQuery("from Friendship f " +
                "where f.user2 = :name and f.status = 0").setString("name",currentUser).list();
        return userList;
    }

    // The next few Methods deal with returning and commenting on users walls and activity feeds,
    // The appropriate wall is returned via queries
    @Override
    @Transactional
    public List<Wall> showUserWall(String currentUser) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Wall> commentList = session.createQuery("from Wall w " +
                "where w.wallOwner = :name").setString("name",currentUser).list();
        return commentList;
    }

    @Override
    public List<Wall> activityFeed(String currentUser) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Wall> commentList = session.createQuery("from Wall w " +
                "where w.wallOwner = :name or w.commentOwner = :name").setString("name",currentUser).list();
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

    // This Method returns the results from user search bar specified values, as can be seen wildcards are used
    // for example if the user searched "use" all users whoc names contain this string will be returned
    @Override
    public List<User> searchUsers(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User u " +
                "where u.username like :name").setString("name", "%" + username + "%").list();
        return userList;
    }

    // This Method lists users friends, i.e. friendship_status = 1
    @Override
    public List<Friendship> listFriends(String currentUser) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Friendship> userList = session.createQuery("from Friendship f " +
                "where f.user2 = :name and f.status = 1").setString("name",currentUser).list();
        return userList;
    }

    // This Method lists users blocked by logged in user, i.e. friendship_status = 3
    @Override
    public List<Friendship> listBlock(String currentUser) {
        Session session = this.sessionFactory.getCurrentSession();

        // Must use setString here as setParameter throws reflection getter error
        List<Friendship> userList = session.createQuery("from Friendship f " +
                "where f.user2 = :name and f.status = 3").setString("name",currentUser).list();
        return userList;
    }

}
