package bg.exam.animal4me.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "species")
public class SpecieEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "specie",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<ModelEntity> models = new ArrayList<>();

    public String getName() {
        return name;
    }

    public SpecieEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelEntity> getModels() {
        return models;
    }

    public SpecieEntity setModels(List<ModelEntity> models) {
        this.models = models;
        return this;
    }

    @Override
    public String toString() {
        return "SpecieEntity{" +
                "name='" + name + '\'' +
                ", models=" + models +
                '}';
    }
}