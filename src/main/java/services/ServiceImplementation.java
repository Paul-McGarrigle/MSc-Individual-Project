package services;

import Entities.Users;
import dao.DAO;
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
    public Collection<Users> getUsers() {
        return null;
    }

    @Override
    @Transactional
    public void addUser(Users u) {
        userDAO.addUser(u);
    }

    @Override
    @Transactional
    public void updateUser(Users u) {
        userDAO.updateUser(u);
    }

    @Override
    @Transactional
    public List<Users> listUsers() {
        return userDAO.listUsers();
    }

    @Override
    @Transactional
    public Users getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userDAO.removeUser(id);
    }
}
