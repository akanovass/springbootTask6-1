package com.example.springbootTask61;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="appRequests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="userName")
    private String userName;

    @Column(name="courseName")
    private String courseName;

    @Column(name="commentary")
    private String commentary;

    @Column(name="phone")
    private String phone;

    @Column(name="handled")
    private boolean handled;
}
