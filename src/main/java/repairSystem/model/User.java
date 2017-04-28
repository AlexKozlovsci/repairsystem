package repairSystem.model;

/**
 * Created by Алексей on 23.04.2017.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "secondname")
    private String secondname;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "role")
    private String role;

    @Column(name = "password")
    private String password;

    public User(String name, String password, String secondname,
                String phone_number, String email, String login, String role) {
        this.name = name;
        this.secondname = secondname;
        this.phone_number = phone_number;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}