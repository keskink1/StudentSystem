package com.keskin.service;

import com.keskin.model.Course;
import com.keskin.model.User;
import com.keskin.util.HashAlgorithm;
import com.keskin.util.SecurityUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class InitializerService {
    @PersistenceContext
    EntityManager entityManager;

    private static final Logger logger = Logger.getLogger(InitializerService.class);

    public void saveTestUserAndCourseData() {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);

        List<User> userList = entityManager.createQuery("from User",User.class).getResultList();

        if(userList.size() == 0){
            User user1 = new User("Kaan","Keskin","kaan@gmail.com", SecurityUtils.hashPassword("1234", HashAlgorithm.SHA256).toString(), User.Role.ADMIN);
            User user2 = new User("John","Doe","john@gmail.com",SecurityUtils.hashPassword("1234", HashAlgorithm.SHA256).toString(), User.Role.STUDENT);
            User user3 = new User("Jane","Doe","jane@gmail.com",SecurityUtils.hashPassword("1234", HashAlgorithm.SHA256).toString(), User.Role.INSTRUCTOR);
            User user4 = new User("Ayse","Bostancı","ayse@gmail.com",SecurityUtils.hashPassword("1234", HashAlgorithm.SHA256).toString(), User.Role.STUDENT);
            User user5 = new User("Mehmet","Keles","mehmet@gmail.com",SecurityUtils.hashPassword("1234", HashAlgorithm.SHA256).toString(), User.Role.ADMIN);
            User user6 = new User("Elon","Musk","elon@gmail.com",SecurityUtils.hashPassword("1234", HashAlgorithm.SHA256).toString(), User.Role.STUDENT);

            entityManager.persist(user1);
            entityManager.persist(user2);
            entityManager.persist(user3);
            entityManager.persist(user4);
            entityManager.persist(user5);
            entityManager.persist(user6);
        }


        List<Course> courseList = entityManager.createQuery("from Course",Course.class).getResultList();

        if(courseList.size() == 0) {
            Course course1 = new Course("MATH1", "Math101");
            Course course2 = new Course("PR101", "Introduction to Programming");
            Course course3 = new Course("PH200", "Physics");

            entityManager.persist(course1);
            entityManager.persist(course2);
            entityManager.persist(course3);
        }

        logger.info("All test data inserted to db...");

    }
}
