package com.management.student.profile.studentList;

import javax.persistence.*;

@Entity(name = "StudentProfile")
@Table(name = "student_profile")
public class StudentProfile {

    @Id
    @SequenceGenerator(
            name = "Student_sequence",
            sequenceName = "Student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "gender",
            nullable = false,
            columnDefinition = "VARCHAR(6)"
    )
    private String gender;

    public StudentProfile() {
    }

    public StudentProfile(Long id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public StudentProfile(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "StudentProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' + '}';
    }
}
