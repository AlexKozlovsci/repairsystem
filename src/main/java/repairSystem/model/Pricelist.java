package repairSystem.model;

/**
 * Created by Юрий on 28.04.2017.
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
public class Pricelist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "action")
    private String action;

    @Column(name = "cost")
    private String cost;
}
