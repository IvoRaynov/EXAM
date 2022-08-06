package bg.exam.animal4me.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String imageUrl;



    @ManyToOne
    private SpecieEntity specie;

    public String getName() {
        return name;
    }

    public ModelEntity setName(String name) {
        this.name = name;
        return this;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public ModelEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }


    public SpecieEntity getSpecie() {
        return specie;
    }

    public ModelEntity setSpecie(SpecieEntity brand) {
        this.specie = brand;
        return this;
    }

    @Override
    public String toString() {
        return "ModelEntity{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", specie=" + specie +
                '}';
    }
}