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
@Table(name = "pricelist")
public class Pricelist {
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

    @Column(name = "device_type")
    private String deviceType;
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }
    public String getDeviceType() {
        return deviceType;
    }

    @Column(name = "action")
    private String action;
    public void setAction(String action)
    {
        this.action = action;
    }
    public String getAction() {
        return action;
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

    @ManyToMany(mappedBy = "pricelists")
    private Set<Workorder> workorder;
    public void setWorkorder(Set<Workorder> workorder)
    {
        this.workorder = workorder;
    }
    public Set<Workorder> getWorkorder() {
        return workorder;
    }
}
