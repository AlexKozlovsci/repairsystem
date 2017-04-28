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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "id_engineer")
    private String idEngineer;

    @Column(name = "id_manager")
    private String idManager;

    @Column(name = "id_client")
    private String idClient;

    @Column(name = "problem")
    private String problem;

    @Column(name = "create_at")
    private String createDate;

    @Column(name = "complete_at")
    private String completeDate;
}
