package com.keskin.controller;

import com.keskin.model.Course;
import com.keskin.service.CourseService;
import com.keskin.util.FacesMessageUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@ViewScoped
public class UpdateCourseBean {
    private Course course;

    @EJB
    private CourseService courseService;

    @PostConstruct
    public void init() {

        try {
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            int courseId = Integer.valueOf(req.getParameter("courseId"));
            course = courseService.findCourse(courseId);
        } catch (NumberFormatException e) {
            System.out.println("courseId is null.");
        }
    }

    public String updateCourse() {
        courseService.updateCourse(this.course);
        FacesMessageUtil.addInfo("Update success", "Course is updated!");

        return "updatecourse";
    }


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
