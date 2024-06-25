package ru.nag.spring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "supports")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "sender_name", nullable = false)
    private String senderName;

    @Column(name = "sender_surname", nullable = false)
    private String senderSurname;

    @Column(name = "sender_email", nullable = false)
    private String senderEmail;

    @Column(name = "issue", length = 1500, nullable = false)
    private String issue;

}
