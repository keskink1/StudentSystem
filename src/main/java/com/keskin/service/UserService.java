package com.keskin.service;

import com.keskin.exception.EmailAlreadyExistsException;
import com.keskin.model.Course;
import com.keskin.model.User;
import com.keskin.model.UserCourse;
import com.keskin.util.HashAlgorithm;
import com.keskin.util.SecurityUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class UserService {
    @PersistenceContext
    EntityManager entityManager;

    public void registerUserToDatabase(User user) throws EmailAlreadyExistsException{
        boolean result = isEmailExistOnDatabase(user.getEmail());

        if(result) {
            throw new EmailAlreadyExistsException("Email is already registered!");
        }
        user.setPassword(SecurityUtils.hashPassword(user.getPassword(), HashAlgorithm.SHA256).toString());
        entityManager.persist(user);
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public void deleteUser(User user) {
        User foundUser = entityManager.find(User.class, user.getId());
        entityManager.remove(foundUser);
    }

    public User findUser(int userId) {
        return entityManager.find(User.class, userId);
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public boolean isEmailExistOnDatabase(String email) {
        List<User> userList = entityManager.createQuery("from User u WHERE u.email=:email", User.class).setParameter("email",email).getResultList();
        if(userList.size() > 0){
            return true;
        } else {
            return false;
        }
    }

    public List<User> getAllStudents() {
        return entityManager.createQuery("from User u WHERE u.role=:userrole", User.class).setParameter("userrole",User.Role.STUDENT).getResultList();
    }

    public Map<User, Course> assignCourseToStudent(int userId, int courseId) {
        Map<User,Course> assignMap = new HashMap<>();

        User foundUser = entityManager.find(User.class, userId);
        Course foundCourse = entityManager.find(Course.class, courseId);

        UserCourse userCourse = new UserCourse();
        userCourse.setUser(foundUser);
        userCourse.setCourse(foundCourse);
        entityManager.merge(userCourse);


        assignMap.put(foundUser, foundCourse);
        return assignMap;
    }

    public List<User> getAllInstructors() {
        return entityManager.createQuery("from User u WHERE u.role=:userrole", User.class).setParameter("userrole",User.Role.INSTRUCTOR).getResultList();
    }
}
