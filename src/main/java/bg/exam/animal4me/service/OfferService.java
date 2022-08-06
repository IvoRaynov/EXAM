package bg.exam.animal4me.service;
import bg.exam.animal4me.model.dto.offer.AddOrEditOfferDTO;
import bg.exam.animal4me.model.dto.offer.OfferDetailDTO;
import bg.exam.animal4me.model.dto.offer.SearchOfferDTO;
import bg.exam.animal4me.model.entity.ModelEntity;
import bg.exam.animal4me.model.entity.OfferEntity;
import bg.exam.animal4me.model.entity.UserEntity;
import bg.exam.animal4me.model.enums.UserRoleEnum;
import bg.exam.animal4me.model.mapper.OfferMapper;
import bg.exam.animal4me.repository.ModelRepository;
import bg.exam.animal4me.repository.OfferRepository;
import bg.exam.animal4me.repository.OfferSpecification;
import bg.exam.animal4me.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final OfferMapper offerMapper;

    public OfferService(OfferRepository offerRepository,
                        UserRepository userRepository,
                        ModelRepository modelRepository,
                        OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.offerMapper = offerMapper;
    }

    public boolean isOwner(String userName, UUID offerId) {

        boolean isOwner = offerRepository.
                findById(offerId).
                filter(o -> o.getSeller().getEmail().equals(userName)).
                isPresent();

        if (isOwner) {
            return true;
        }

        return userRepository.
                findByEmail(userName).
                filter(this::isAdmin).
                isPresent();
    }

    private boolean isAdmin(UserEntity user) {
        return user.getUserRoles().
                stream().
                anyMatch(r -> r.getUserRole() == UserRoleEnum.ADMIN);
    }

    public void deleteOfferById(UUID offerId) {
        offerRepository.deleteById(offerId);
    }


    public Page<OfferDetailDTO> getAllOffers(Pageable pageable) {
        return offerRepository.findAll(pageable).map(offerMapper::offerEntityToOfferDetailDto);
    }

    public Optional<OfferDetailDTO> findOfferByUUID(UUID offerId) {
        return offerRepository.findById(offerId).map(offerMapper::offerEntityToOfferDetailDto);
    }


    public void addOffer(@Valid AddOrEditOfferDTO addOrEditOfferDTO, UserDetails userDetails) {
        OfferEntity newOffer = offerMapper.
                addOrEditOfferDtoToOfferEntity(addOrEditOfferDTO);

        UserEntity seller = userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow();

        ModelEntity model = modelRepository.findById(addOrEditOfferDTO.getModelId()).
                orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }


    public List<OfferDetailDTO> searchOffer(SearchOfferDTO searchOfferDTO) {
        return this.offerRepository.findAll(new OfferSpecification(searchOfferDTO))
                .stream()
                .map(offerMapper::offerEntityToOfferDetailDto)
                .toList();
    }
}