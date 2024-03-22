package com.auca.thedao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.auca.themodel.Teacher;
import com.auca.theutil.HibernateUtil;
import com.mysql.cj.Query;


public class TeacherDao {

    public void addTeacher(Teacher teacher) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            
            // Save the teacher object
            session.save(teacher);
            
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public Teacher getTeacherByCode(String code) {
        Teacher teacher = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            String hql = "FROM Teacher T WHERE T.code = :code";
            org.hibernate.query.Query<Teacher> query = session.createQuery(hql, Teacher.class);
            query.setParameter("code", code);
            teacher = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher;
    }


}
