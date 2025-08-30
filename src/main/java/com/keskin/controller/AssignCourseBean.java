package com.keskin.controller;

import com.keskin.model.Course;
import com.keskin.model.User;
import com.keskin.service.CourseService;
import com.keskin.service.UserService;
import com.keskin.util.FacesMessageUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.Map;

@ManagedBean
public class AssignCourseBean {
    private User user;
    private Course course;

    private Integer selectedUserId;
    private Integer selectedCourseId;

    @EJB
    private UserService userService;

    @EJB
    private CourseService courseService;

    @PostConstruct
    public void init() {
        user = new User();
        course = new Course();
    }

    public String assign() {
        if (selectedUserId == null || selectedCourseId == null) {
            FacesMessageUtil.addError("Validation failed", "All fields are required.");
            return null; // formu yeniden göster
        }

        Map<User, Course> result = userService.assignCourseToStudent(selectedUserId, selectedCourseId);

        if (!result.isEmpty()) {
            Map.Entry<User, Course> entry = result.entrySet().iterator().next();
            user = entry.getKey();
            course = entry.getValue();

            FacesMessageUtil.addInfo("Assign success",
                    user.getFirstname() + " is assigned to " + course.getCourseName());
        } else {
            FacesMessageUtil.addError("Assignment failed", "The assignment could not be completed.");
        }

        return "assigncourse";
    }

    public void resetForm() {
        selectedUserId = null;
        selectedCourseId = null;
        user = new User();
        course = new Course();
    }

    public List<User> getAllInstructors() {
        return userService.getAllInstructors();
    }

    public List<User> getAllStudents() {
        return userService.getAllStudents();
    }

    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    public Integer getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(Integer selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public Integer getSelectedCourseId() {
        return selectedCourseId;
    }

    public void setSelectedCourseId(Integer selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
