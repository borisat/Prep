package jm.task.core.jdbc.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test_jdbc.public.user_hiber")
public class User implements Serializable  {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "age")
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
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

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");

        stringBuilder.append("UserID: " + this.getId() + ", ")
                .append("Username:" + this.getName() + ", ")
                .append("Lastname: " + this.getLastName() + ", ")
                .append("age: " + this.age + ", ");

        return stringBuilder.toString();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }
}