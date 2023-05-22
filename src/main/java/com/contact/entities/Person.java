package com.contact.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "person")
public class Person {
    @Id
    private String personId;
    @Column(name = "name")
    private String personName;
    @Column(name = "email",unique = true)
    private String personEmail;
    @Column(name = "mobile",unique = true)
    private String personMobile;
}
