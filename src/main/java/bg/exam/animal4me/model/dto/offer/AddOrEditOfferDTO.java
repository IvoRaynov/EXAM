package bg.exam.animal4me.model.dto.offer;

import bg.exam.animal4me.model.enums.SellOrBuy;
import bg.exam.animal4me.model.enums.TypeAnimal;

import javax.validation.constraints.*;

public class AddOrEditOfferDTO {

    @NotNull
    @Min(1)
    private Long modelId;
    @NotNull
    private TypeAnimal animalType;

    @Positive
    @NotNull
    private Integer price;


    @Min(1900)
    @NotNull
    private Integer year;

    @NotEmpty
    private String description;

    @NotNull
    private SellOrBuy sellOrBuy;

    @NotEmpty
    private String imageUrl;

    public Long getModelId() {
        return modelId;
    }

    public AddOrEditOfferDTO setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public TypeAnimal getAnimalType() {
        return animalType;
    }

    public AddOrEditOfferDTO setAnimalType(TypeAnimal animalType) {
        this.animalType = animalType;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public AddOrEditOfferDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public AddOrEditOfferDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOrEditOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public SellOrBuy getSellOrBuy() {
        return sellOrBuy;
    }

    public AddOrEditOfferDTO setSellOrBuy(SellOrBuy sellOrBuy) {
        this.sellOrBuy = sellOrBuy;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddOrEditOfferDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

}