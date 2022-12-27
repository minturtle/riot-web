package com.riot.web.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="_user")
@Getter
@ToString(of = {"name", "email", "picture"})
@NoArgsConstructor
public class User {
    @Getter
    @RequiredArgsConstructor
    public static enum Role{
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");
        private final String key;
    }
    @Id @GeneratedValue
    private Long idx;

    @Column(unique = true, nullable = false)
    private String uuid;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String uuid, String name, String email, String picture, Role role) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }



    public String getRoleKey(){
        return this.role.getKey();
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
