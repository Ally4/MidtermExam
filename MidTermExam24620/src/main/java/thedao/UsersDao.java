package com.auca.thedao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.auca.themodel.Users;
import com.auca.theutil.HibernateUtil;
import org.hibernate.query.Query;

public class UsersDao {

    public void saveUser(Users user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Save the user object
            session.save(user);
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Users getUserByEmailAndPassword(String email, String password) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            // Create a query to find the user by email
            String hql = "FROM Users WHERE email = :email";
            Query<Users> query = session.createQuery(hql, Users.class);
            query.setParameter("email", email);
            Users user = query.uniqueResult();

            // Check if user exists and password matches
            if (user != null && user.getPassword().equals(password)) {
                return user; // User found and password matches
            }

            return null; // User not found or incorrect password
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
