package pl.javastart.weekop.service;

import pl.javastart.weekop.dao.DAOFactory;
import pl.javastart.weekop.dao.UserDAO;
import pl.javastart.weekop.model.User;

public class UserService {
	
	public void addUser(String username, String email, String password) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setActive(false);
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		UserDAO userDAO = daoFactory.getUserDAO();
		userDAO.create(user);
	}
	
	public User getUserById(long id) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDAO = factory.getUserDAO();
		User user = userDAO.read(id);
		return user;
	}
	
	public User getUserByUsername(String username) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDAO = factory.getUserDAO();
		User user = userDAO.getUserByUsername(username);
		return user;
	}

}
