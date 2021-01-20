package br.com.alura.livraria.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.NonUniqueResultException;

import br.com.alura.livraria.dao.GenericDAO;
import br.com.alura.livraria.dao.UserDAO;
import br.com.alura.livraria.exception.EmailAlreadyRegisteredException;
import br.com.alura.livraria.model.User;
import br.com.alura.livraria.security.SecurityUtil;

@Transactional
public class UserService extends BasicCRUDService<User, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserDAO userDAO;
	
	public boolean credentialsAreCorrect(User user) {
		User userFromDB = this.findByEmail(user.getEmail());
		return userFromDB != null && userFromDB.getPassword().equals(SecurityUtil.hashPassword(user.getPassword()));
		
	}
	
	public boolean emailAlreadyRegistered(String email) {
		return this.findByEmail(email) != null;
	}
	
	@Override
	public Integer insert(User user) {
		if(this.emailAlreadyRegistered(user.getEmail())) {
			throw new EmailAlreadyRegisteredException(user.getEmail());
		}
		user.setPassword(SecurityUtil.hashPassword(user.getPassword()));
		return super.insert(user);
	}
	
	public User findByEmail(String email) {
		List<User> users = this.getDAO().findByProperty("email", email);
		if(users == null  || users.isEmpty()) {
			return null;
		}
		if(users.size() > 1) {
			throw new NonUniqueResultException(users.size());
		}
		return users.get(0);
	}

	@Override
	protected GenericDAO<User, Integer> getDAO() {
		return userDAO;
	}
	
}
