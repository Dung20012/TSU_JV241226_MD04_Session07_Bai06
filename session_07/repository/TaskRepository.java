package com.data.session_07.repository;

import com.data.session_07.model.entity.Task;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TaskRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Task> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Task",  Task.class)
                .list();
    }

    public Task findById(Long id) {
        return sessionFactory.getCurrentSession()
                .get(Task.class, id);
    }

    public void save(Task task) {
        sessionFactory.getCurrentSession().persist(task);
    }

    public void update(Task task) {
        sessionFactory.getCurrentSession().merge(task);
    }

    public void delete(Long id){
        Task task = findById(id);
        if (task != null) {
            sessionFactory.getCurrentSession().remove(task);
        }
    }
}
