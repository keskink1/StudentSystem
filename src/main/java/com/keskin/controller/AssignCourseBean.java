package com.keskin.controller;

import com.keskin.model.Course;
import com.keskin.model.User;
import com.keskin.service.CourseService;
import com.keskin.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
        // null kontrolu
        if (selectedUserId == null || selectedCourseId == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "All fields are required.", null));
            return null; // formu yeniden göster
        }

        Map<User, Course> result = userService.assignCourseToStudent(selectedUserId, selectedCourseId);

        if (!result.isEmpty()) {
            Map.Entry<User, Course> entry = result.entrySet().iterator().next();
            user = entry.getKey();
            course = entry.getValue();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Assign success", user.getFirstname() + " is assigned to " + course.getCourseName()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Assignment failed", "The assignment could not be completed."));
        }

        return "assigncourse"; // islem başarili
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
