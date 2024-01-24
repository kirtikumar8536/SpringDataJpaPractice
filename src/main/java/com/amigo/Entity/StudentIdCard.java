package com.amigo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "StudentIdCard")
@Table(name = "student_id_card", uniqueConstraints = {
        @UniqueConstraint(name = "student_id_card_number_unique", columnNames = "card_number")
})
public class StudentIdCard {
    @Id
    @SequenceGenerator(name = "student_card_id_sequence",
            sequenceName = "student_card_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "student_card_id_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "card_number", nullable = false, length = 15)
    private String cardNumber;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    @JoinColumn(name = "student_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "student_id_card_student_id_fk"))
    private Student student;

    public StudentIdCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

  /* @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", student=" + student +
                '}';
    }*/

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
