package com.management.student.profile;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DepartmentEntity {

    @Id
    public int id;
    public String name;


    public DepartmentEntity(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public DepartmentEntity() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
