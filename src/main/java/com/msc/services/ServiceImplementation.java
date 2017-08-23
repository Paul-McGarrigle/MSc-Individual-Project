package com.msc.services;

import com.msc.dao.DAO;
import com.msc.model.Friendship;
import com.msc.model.User;
import com.msc.model.UserRole;
import com.msc.model.Wall;
import org.springframework.transaction.annotation.Transactional;

import javax.ejb.EJB;
import java.util.Collection;
import java.util.List;

/**
 * Created by Paul on 25/07/2017.
 */
public class ServiceImplementation implements ServiceUser {
    @EJB
    private DAO userDAO;

    // Constructors are for beans
    public void setUserDAO(DAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public Collection<User> getUsers() {
        return null;
    }

    @Override
    @Transactional
    public void addUser(User u, UserRole ur) {
        userDAO.addUser(u, ur);
    }

    @Override
    @Transactional
    public void updateUser(User u) {
        userDAO.updateUser(u);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userDAO.listUsers();
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void removeUser(String username) {
        userDAO.removeUser(username);
    }

    @Override
    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    @Override
    public void addFriend(String currentUser, String userName) {
        userDAO.addFriend(currentUser,userName);
    }

    @Override
    @Transactional
    public void acceptFriendRequest(String currentUser, String userName) {
        userDAO.acceptFriendRequest(currentUser,userName);
    }

    @Override
    public void declineFriendRequest(String currentUser, String userName) {
        userDAO.declineFriendRequest(currentUser, userName);
    }

    @Override
    public void blockUser(String currentUser, String userName) {
        userDAO.blockUser(currentUser, userName);
    }

    @Override
    @Transactional
    public List<Friendship> listFriendRequests(String currentUser) {
        return userDAO.listFriendRequests(currentUser);
    }

    @Override
    @Transactional
    public List<Wall> showUserWall(String currentUser) {
        return userDAO.showUserWall(currentUser);
    }

    @Override
    @Transactional
    public void addComment(String currentUser, String userName, String comment) {
        userDAO.addComment(currentUser, userName, comment);
    }

    /*@Override
    public void addUserRole(UserRole ur) {
        userDAO.addUserRole(ur);
    }*/
}
