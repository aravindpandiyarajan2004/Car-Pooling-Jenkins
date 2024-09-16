package com.aravind.car.repositoryimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aravind.car.repository.UserRepo;
import com.aravind.car.DTO.UserDto;
import com.aravind.car.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserRepoImpl implements UserRepo {

	@Autowired
	EntityManager entityManager;

	@Override
	public String save(User user) {
		if (user != null) {
			entityManager.merge(user);
			return "Success";
		} else {
			return "Failure";
		}
	}

	@Override
	public String update(UserDto userDto) {
		if (userDto != null) {
			// Find the existing user entity
			User existingUser = entityManager.find(User.class, userDto.getUserId());

			if (existingUser != null) {
				// Update the fields of the existing user entity
				existingUser.setName(userDto.getName());
				existingUser.setEmail(userDto.getEmail());
				existingUser.setMobile(userDto.getMobile());
				existingUser.setDob(userDto.getDob());
				existingUser.setAddress(userDto.getAddress());
				existingUser.setCity(userDto.getCity());
				existingUser.setState(userDto.getState());
				existingUser.setGender(userDto.getGender());

				// Merge the updated entity back into the database
				entityManager.merge(existingUser);
				return "Success";
			}
		}
		return "Failure";
	}

	@Override
	public String delete(int userId) {
		User id = entityManager.find(User.class, userId);
		if (id != null) {
			entityManager.remove(id);
			return "Success";
		}
		return "Failure";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUser() {
		String hql = "from User";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public User findById(int userId) {
		return entityManager.find(User.class, userId);

	}

//	@Override
//	public User findByEmailAndPassword(String email, String password) {
//		String jpql = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password AND u.accountStatus != 'Pending'";
//		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
//		query.setParameter("email", email);
//		query.setParameter("password", password);
//		System.out.println(query.getResultStream().findFirst());
//		return query.getResultStream().findFirst().orElse(null);
//	    
//	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		String jpql = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public User findByEmail(String email) {
		String jpql = "SELECT u FROM User u WHERE u.email = :email";
		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		query.setParameter("email", email);
		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public List<User> findAllByStatus(String accountStatus) {
		String jpql = "SELECT u FROM User u WHERE u.accountStatus = :accountStatus";
		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		query.setParameter("accountStatus", accountStatus);
		return query.getResultList();
	}

}
