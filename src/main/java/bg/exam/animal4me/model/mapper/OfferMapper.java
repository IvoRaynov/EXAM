package bg.exam.animal4me.model.mapper;


import bg.exam.animal4me.model.dto.offer.AddOrEditOfferDTO;
import bg.exam.animal4me.model.dto.offer.OfferDetailDTO;
import bg.exam.animal4me.model.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    OfferEntity addOrEditOfferDtoToOfferEntity(AddOrEditOfferDTO addOrEditOfferDTO);

    AddOrEditOfferDTO OfferEntityToAddOrEditOfferDto(OfferEntity offerEntity);

    @Mapping(source = "model.name", target = "model")
    @Mapping(source = "model.specie.name", target = "specie")
    @Mapping(source = "seller.firstName", target = "sellerFirstName")
    @Mapping(source = "seller.lastName", target = "sellerLastName")
    @Mapping(source = "seller.telephone", target = "telephone")
    OfferDetailDTO offerEntityToOfferDetailDto(OfferEntity offerEntity);
}