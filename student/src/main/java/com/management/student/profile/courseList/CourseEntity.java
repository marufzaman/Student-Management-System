package com.management.student.profile.courseList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CourseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Long courseId;
    @Column(name="course_name")
    private String courseName;

    /*@ManyToOne
    private Department department;*/

    public CourseEntity() {
    }

    public CourseEntity(String courseName) {
        this.courseName = courseName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
