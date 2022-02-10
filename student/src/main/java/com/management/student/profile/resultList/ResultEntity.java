package com.management.student.profile.resultList;

import javax.persistence.*;

@Entity
@Table(name = "result")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer Student_sequence;

    @Column(nullable = true)

    private String status;

    public ResultEntity() {

    }

    public ResultEntity(int i, int i1, String str) {
        this.id = i;
        this.Student_sequence = i1;
        this.status = str;
    }

    public Integer getStudent_sequence() {
        return Student_sequence;
    }

    public void setStudent_sequence(Integer student_sequence) {
        Student_sequence = student_sequence;
    }

    //    @ManyToOne


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
