package com.keskin.controller;

import com.keskin.model.Course;
import com.keskin.service.CourseService;
import com.keskin.util.FacesMessageUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean
public class CourseListBean {

    private List<Course> courseList;

    @EJB
    private CourseService courseService;

    @PostConstruct
    public void init(){
        courseList = courseService.getAllCourses();
    }

    public String deleteCourse(Course course){
        courseService.deleteCourse(course);
        FacesMessageUtil.addInfo("Delete success", "Course deleted from database!");

        return "courselist?faces-redirect=true";
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
