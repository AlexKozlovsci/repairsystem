package repairSystem.model;

/**
 * Created by Юрий on 28.04.2017.
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    public void setId(long id)
    {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    @Column(name = "name")
    private String name;
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Column(name = "count")
    private long count;
    public void setCount(long count)
    {
        this.count = count;
    }
    public long getCount() {
        return count;
    }

    @Column(name = "cost")
    private long cost;
    public void setCost(long cost)
    {
        this.cost = cost;
    }
    public long getCost() {
        return cost;
    }

    @ManyToMany(mappedBy = "details")
    private Set<Workorder> workorder;
    public void setWorkorder(Set<Workorder> workorder)
    {
        this.workorder = workorder;
    }
    public Set<Workorder> getWorkorder() {
        return workorder;
    }
}
