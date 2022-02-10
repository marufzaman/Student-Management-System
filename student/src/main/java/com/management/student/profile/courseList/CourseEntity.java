package com.management.student.profile.courseList;

import javax.persistence.*;

@Entity
public class CourseEntity {

    @Id
    @SequenceGenerator(
            name = "Course_sequence",
            sequenceName = "Course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Course_sequence"
    )
    @Column(
            name = "courseId",
            updatable = false
    )
    private Long courseId;

    @Column(
            name = "courseName",
            nullable = false,
            columnDefinition = "TEXT"
    )
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
