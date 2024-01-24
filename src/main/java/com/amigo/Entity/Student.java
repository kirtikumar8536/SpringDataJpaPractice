package com.amigo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "students")
@Table(name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique",
                        columnNames = "email")})
public class Student {
    @Id
    @SequenceGenerator(sequenceName = "student_sequence",
            name = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "student_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "age", nullable = false)
    private Integer age;

    @OneToOne(mappedBy = "student",orphanRemoval = true)
    private StudentIdCard studentIdCard;

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student(String firstName, String lastName, String email, Integer age, StudentIdCard studentIdCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.studentIdCard = studentIdCard;
    }


   /*  @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }*/

    @Override
    public String toString() {//work for all

        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", studentIdCard=" + studentIdCard +
                '}';
    }
}
