package com.msc.services;

import com.msc.model.Friendship;
import com.msc.model.User;
import com.msc.model.UserRole;
import com.msc.model.Wall;

import java.util.Collection;
import java.util.List;

/**
 * Created by Paul on 25/07/2017.
 */
// Services Interface
public interface ServiceUser {
    public Collection<User> getUsers();
    public void addUser(User u, UserRole ur);
    public void updateUser(User u);
    public List<User> listUsers();
    public User getUserById(int id);
    public void removeUser(String username);
    public User findByUserName(String username);
    public void addFriend(String currentUser, String userName);
    public void acceptFriendRequest(String currentUser, String userName);
    public void declineFriendRequest(String currentUser, String userName);
    public void blockUser(String currentUser, String userName);
    public void unBlockUser(String currentUser, String userName);
    public List<Friendship> listFriendRequests(String currentUser);
    public List<Wall> showUserWall(String currentUser);
    public List<Wall> activityFeed(String currentUser);
    public void addComment(String currentUser, String userName, String comment);
    public List<User> searchUsers(String username);
    public List<Friendship> listFriends(String currentUser);
    public List<Friendship> listBlock(String currentUser);
}
