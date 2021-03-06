package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.entities.Customer;
import com.revature.entities.Employee;
import com.revature.entities.Order;


@Repository(value = "OrderDAO")
@Transactional
public class OrderDAOImpl implements OrderDAO {
	
	
	private SessionFactory sessionFactory;

	@Autowired
	public OrderDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	@Override
	public Order createOrder(Order order) {
		sessionFactory.getCurrentSession().saveOrUpdate(order);
		return order;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getCustomerOrders(int customerId) {
		try {
		
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Order> query = builder.createQuery(Order.class);
		Root<Order> root = query.from(Order.class);
        query.select(root).where(builder.equal(root.get("customer"), customerId));
        
        Query<Order> q = session.createQuery(query);
        List<Order> o = (List<Order>) q.list();
        return o;	
        
		} catch (Exception e) {
		e.printStackTrace();
		return null;
	         
		}

		
//		List<Order> orders = new ArrayList<>();
//		Session s = sessionFactory.getCurrentSession();
//		orders= s.createQuery("from Orders where order.customerid = ").getResultList();
//		return orders;
	}

	@Override
	public void updateStatus(Order order) {
		sessionFactory.getCurrentSession().saveOrUpdate(order);
	}
	
	
	public void changeStatus(int orderId, Order order) {
		Session s = sessionFactory.getCurrentSession();
		Order oldOrder = s.byId(Order.class).load(orderId);
		oldOrder.setStatus(order.getStatus());
		
	}

//	@Override
//	public void updateTotal(Order order) {
//		sessionFactory.getCurrentSession().saveOrUpdate(order);
//	}
	
	@Override
	public Order cancelOrder(Order order) {
			sessionFactory.getCurrentSession().saveOrUpdate(order);
		try {
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaDelete<Order> delete = builder.createCriteriaDelete(Order.class);
			Root<Order> root = delete.from(Order.class);
	       	delete.where(builder.equal(root.get("orderid"), order));
	       	order.setStatus("cancelled");
	        
	        Query<Order> q = session.createQuery(delete);
	        Order o = (Order) q.list();
	        return o;	
	        
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		         
			}
		
	}
	

	
//	@Override
//	public Order deleteO(int orderid) {
//		try {
//			
//			Session session = sessionFactory.getCurrentSession();
//			CriteriaBuilder builder = session.getCriteriaBuilder();
//			CriteriaDelete<Order> delete = builder.createCriteriaDelete(Order.class);
//			Root<Order> root = delete.from(Order.class);
//	        delete.where(builder.equal(root.get("orderid"), orderid));
//	        
//	        Query<Order> q = session.createQuery(delete);
//	        Order o = (Order) q.list();
//	        return o;	
//	        
//			} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		         
//			}
//		
//	}
//	

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> pendingOrders() {
		List<Order> orders = new ArrayList<>();
		Session s = sessionFactory.getCurrentSession();
		orders =  s.createQuery("from Order where status = 'pending'").getResultList();
		//FROM Recipe r  WHERE r.user.userId = :id
		//q.setParameter("status", "pending");
		System.out.println(orders);
		
		return orders;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> acceptedOrdersByEmployee(int employeeId) {
		List<Order> orders = new ArrayList<>();
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from Order where status = 'accepted' and employee =" + employeeId);
		orders = q.getResultList();
		return orders;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrderByStatus(String status) {
		List<Order> orders = new ArrayList<>();
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from Order where status =" + "'" +status+"'");
		orders = q.getResultList();
		return orders;
	}


	@Override
	public Order getOrderById(int id) {	
		return sessionFactory.getCurrentSession().byId(Order.class).load(id);
       
	}


	
}
