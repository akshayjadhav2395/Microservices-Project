package com.aj.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "micro-users")
public class User {

    @Id
    @Column(name = "uId")
    private String userId;
    private String name;
    private String email;
    private String about;
    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
