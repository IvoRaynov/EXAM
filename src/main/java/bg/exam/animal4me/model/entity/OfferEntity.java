package bg.exam.animal4me.model.entity;
import bg.exam.animal4me.model.enums.SellOrBuy;
import bg.exam.animal4me.model.enums.TypeAnimal;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "offers")
public class OfferEntity  {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeAnimal animalType;

    private String imageUrl;



    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)

    private SellOrBuy sellOrBuy;

    private int year;

    @ManyToOne
    private ModelEntity model;

    @ManyToOne
    private UserEntity seller;

    public UUID getId() {
        return id;
    }

    public OfferEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public TypeAnimal getAnimalType() {
        return animalType;
    }

    public OfferEntity setAnimalType(TypeAnimal animalType) {
        this.animalType = animalType;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public SellOrBuy getSellOrBuy() {
        return sellOrBuy;
    }

    public OfferEntity setSellOrBuy(SellOrBuy sellOrBuy) {
        this.sellOrBuy = sellOrBuy;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferEntity setYear(int year) {
        this.year = year;
        return this;
    }

    public ModelEntity getModel() {
        return model;
    }

    public OfferEntity setModel(ModelEntity model) {
        this.model = model;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    @Override
    public String toString() {
        return "OfferEntity{" +
                "id=" + id +
                ", animalType=" + animalType +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", sellOrBuy=" + sellOrBuy +
                ", year=" + year +
                ", model=" + model +
                ", seller=" + seller +
                '}';
    }
}