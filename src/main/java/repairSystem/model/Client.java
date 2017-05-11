package repairSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Алексей on 07.05.2017.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
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

    @Column(name = "email")
    private String email;

    @Column(name = "discount")
    private long discount;

    public Client(String name, String secondname, String phone_number,
                String email, long discount) {
        this.name = name;
        this.secondname = secondname;
        this.phone_number = phone_number;
        this.email = email;
        this.discount = discount;
    }
}
