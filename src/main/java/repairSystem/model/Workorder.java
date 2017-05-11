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
public class Workorder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "id_engineer")
    private long id_engineer;

    @Column(name = "id_manager")
    private long id_manager;

    @Column(name = "id_client")
    private long id_client;

    @Column(name = "description")
    private String description;

    @Column(name = "problem")
    private String problem;

    @Column(name = "status")
    private String status;

    @Column(name = "create_at")
    private String create_at;

    @Column(name = "complete_at")
    private String complete_at;


    public Workorder(long id_engineer, long id_manager, long id_client, String description,
                     String problem, String status, String create_at, String complete_at) {
        this.id_engineer = id_engineer;
        this.id_manager = id_manager;
        this.id_client = id_client;
        this.description = description;
        this.problem = problem;
        this.status = status;
        this.create_at = create_at;
        this.complete_at = complete_at;
    }
}
