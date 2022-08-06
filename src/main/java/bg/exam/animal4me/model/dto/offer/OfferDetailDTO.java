package bg.exam.animal4me.model.dto.offer;

import bg.exam.animal4me.model.enums.SellOrBuy;
import bg.exam.animal4me.model.enums.TypeAnimal;

import java.math.BigDecimal;
import java.util.UUID;

public class OfferDetailDTO {
    private UUID id;
    private String imageUrl;
    private Integer year;
    private String specie;
    private String model;
    private BigDecimal price;
    private TypeAnimal animalType;
    private SellOrBuy sellOrBuy;
    private String description;
    private String sellerFirstName;
    private String sellerLastName;
    private String telephone;

    public String getSellerFullName(){
        return sellerFirstName+" "+sellerLastName;
    }
    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public OfferDetailDTO setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
        return this;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public OfferDetailDTO setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public OfferDetailDTO setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public OfferDetailDTO() {
    }

    public UUID getId() {
        return id;
    }

    public OfferDetailDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferDetailDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferDetailDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getSpecie() {
        return specie;
    }

    public OfferDetailDTO setSpecie(String specie) {
        this.specie = specie;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferDetailDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDetailDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TypeAnimal getAnimalType() {
        return animalType;
    }

    public OfferDetailDTO setAnimalType(TypeAnimal animalType) {
        this.animalType = animalType;
        return this;
    }

    public SellOrBuy getSellOrBuy() {
        return sellOrBuy;
    }

    public OfferDetailDTO setSellOrBuy(SellOrBuy sellOrBuy) {
        this.sellOrBuy = sellOrBuy;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDetailDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getOfferHighlight() {
        return this.specie + " " + this.model;
    }
}