package repairSystem.model;

/**
 * Created by Юрий on 28.04.2017.
 */

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workorder")
public class Workorder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    public void setId(long id)
    {
        this.id = id;
    }
    public long getId()
    {
        return id;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_detail", joinColumns = @JoinColumn(name = "id_order", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_detail", referencedColumnName = "id"))
    public Set<Detail> details;
    public Set<Detail> getDetail() {
        return details;
    }
    public void setDetail(Set<Detail> details){
        this.details = details;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_pricelist", joinColumns = @JoinColumn(name = "id_order", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_pricelist", referencedColumnName = "id"))
    public Set<Pricelist> pricelists;
    public Set<Pricelist> getPricelists() {
        return pricelists;
    }
    public void setPricelists(Set<Pricelist> pricelists){
        this.pricelists = pricelists;
    }

    @Column(name = "id_engineer")
    private long id_engineer;
    public void setId_engineer(long id_engineer)
    {
        this.id_engineer = id_engineer;
    }
    public long getId_engineer()
    {
        return id_engineer;
    }

    @Column(name = "id_manager")
    private long id_manager;
    public void setId_manager(long id_manager)
    {
        this.id_manager = id_manager;
    }
    public long getId_manager()
    {
        return id_manager;
    }

    @Column(name = "id_client")
    private long id_client;
    public void setId_client(long id_client)
    {
        this.id_client = id_client;
    }
    public long getId_client()
    {
        return id_client;
    }

    @Column(name = "description")
    private String description;
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getDescription()
    {
        return description;
    }


    @Column(name = "problem")
    private String problem;
    public String getProblem(){
        return problem;
    }

    public void setProblem(String problem){
        this.problem = problem;
    }

    @Column(name = "status")
    private String status;
    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    @Column(name = "create_at")
    private String create_at;
    public String getCreate_at(){
        return create_at;
    }

    public void setCreate_at(String create_at){
        this.create_at = create_at;
    }

    @Column(name = "complete_at")
    private String complete_at;
    public String getComplete_at(){
        return complete_at;
    }

    public void setComplete_at(String complete_at){
        this.complete_at = complete_at;
    }

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
