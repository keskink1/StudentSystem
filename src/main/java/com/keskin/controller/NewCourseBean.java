package com.keskin.controller;

import com.keskin.model.Course;
import com.keskin.service.CourseService;
import com.keskin.util.FacesMessageUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class NewCourseBean {

    private Course course;

    @EJB
    private CourseService courseService;

    @PostConstruct
    public void init() {
        course = new Course();
    }

    public String saveCourse() {
        boolean result = courseService.isCourseOnDatabase(this.course.getCourseCode());
        if (!result) {
            courseService.saveNewCourse(this.course);

            FacesMessageUtil.addInfo("Success ", "Course is saved to database");
        } else {
            FacesMessageUtil.addWarn("WARNING ", "Course already exists!");
        }
        return "addcourse";
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
