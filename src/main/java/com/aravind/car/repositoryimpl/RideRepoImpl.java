package com.aravind.car.repositoryimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aravind.car.model.Ride;
import com.aravind.car.model.User;
import com.aravind.car.repository.RideRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class RideRepoImpl implements RideRepo {

	@Autowired
	EntityManager entityManager;

	@Override
	public String save(Ride ride) {
		if (ride != null) {
			System.out.println(ride.getUser().getUserId());
			System.out.println(ride.getVehicle().getVehicleId());
			
			entityManager.merge(ride);
			return "Success";
		} else {
			return "Failure";
		}
	}

	@Override
	public String update(Ride ride) {
		if (ride != null) {
			entityManager.merge(ride);
			return "Success";
		} else {
			return "Failure";
		}

	}

	@Override
	public String delete(int rideId) {
		Ride id = entityManager.find(Ride.class, rideId);
		if (id != null) {
			entityManager.remove(id);
			return "Success";
		}
		return "Failure";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ride> findAllRides() {
		String hql = "from Ride";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public Ride findById(int rideId) {
		return entityManager.find(Ride.class, rideId);
	}

	boolean canBookSeats(int rideId, int requestedSeats) {
		Ride ride = entityManager.find(Ride.class, rideId);
		return ride != null && ride.getAvailableSeats() >= requestedSeats;
	}

//	@Override
//	public List<Ride> findByUserId(int userId) {
//		String jpql = "SELECT r FROM Ride r WHERE r.userId = :userId";
//		TypedQuery<Ride> query = entityManager.createQuery(jpql, Ride.class);
//		query.setParameter("userId", userId);
//		return query.getResultList();
//	}

	public List<Ride> findByUserId(int userId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ride> cq = cb.createQuery(Ride.class);
		Root<Ride> ride = cq.from(Ride.class);
		Join<Ride, User> userJoin = ride.join("user");

		cq.select(ride).where(cb.equal(userJoin.get("id"), userId));
		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public List<String> findDistinctStartPoints() {
		String jpql = "SELECT DISTINCT r.startPoint FROM Ride r";
		TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
		return query.getResultList();
	}

	@Override
	public List<String> findDistinctEndPoints() {
		String jpql = "SELECT DISTINCT r.endPoint FROM Ride r";
		TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
		return query.getResultList();
	}

}
