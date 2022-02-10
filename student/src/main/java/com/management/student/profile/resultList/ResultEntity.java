package com.management.student.profile.resultList;

import javax.persistence.*;

@Entity
@Table(name = "result")
public class ResultEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


//    @Column(nullable = true)

    private String status;

    public ResultEntity() {

    }

    public ResultEntity(int id, String status) {
        this.id = id;
        this.status = status;
    }

//    public Integer getStudent_sequence() {
//        return student_sequence;
//    }
//
//    public void setStudent_sequence(Integer student_sequence) {
//        student_sequence = student_sequence;
//    }
//
//    //    @ManyToOne


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
