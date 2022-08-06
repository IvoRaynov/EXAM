package bg.exam.animal4me.util;

import bg.exam.animal4me.model.entity.*;
import bg.exam.animal4me.model.enums.SellOrBuy;
import bg.exam.animal4me.model.enums.TypeAnimal;
import bg.exam.animal4me.model.enums.UserRoleEnum;
import bg.exam.animal4me.repository.*;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;

@Component
public class TestDataUtils {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final SpecieRepository specieRepository;

    public TestDataUtils(UserRepository userRepository,
                         UserRoleRepository userRoleRepository,
                         OfferRepository offerRepository,
                         ModelRepository modelRepository,
                         SpecieRepository specieRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.specieRepository = specieRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);
            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public UserEntity createTestAdmin(String email) {
        initRoles();
        var admin = new UserEntity().
                setEmail(email).
                setFirstName("Ivo").
                setLastName("Raynov").
                setTelephone("0888000000").
                setActive(true).
                setUserRoles(userRoleRepository.findAll());

        return userRepository.save(admin);
    }

    public UserEntity createTestUser(String email) {
        initRoles();
        var user = new UserEntity().
                setEmail(email).
                setFirstName("Gosho").
                setLastName("Georgiev").
                setActive(true).
                setTelephone("0888000000").
                setUserRoles(userRoleRepository.findAll().stream().filter(r->r.getUserRole()!=UserRoleEnum.ADMIN).toList());

        return userRepository.save(user);
    }

    public OfferEntity createTestOffer(UserEntity seller, ModelEntity model) {
        var offerEntity = new OfferEntity().
                setAnimalType(TypeAnimal.FARM).
                setModel(model).
                setPrice(BigDecimal.TEN).
                setSellOrBuy(SellOrBuy.SELL).
                setYear(2000).
                setDescription("Test description").
                setSeller(seller);
        return offerRepository.save(offerEntity);
    }

    public SpecieEntity createTestSpecie() {
        var brandEntity = new SpecieEntity().setName("Cow");
        return specieRepository.save(brandEntity);
    }

    public ModelEntity createTestModel(SpecieEntity specieEntity) {

        ModelEntity model = new ModelEntity().
                setName("Guernsey").
                setSpecie(specieEntity);
        return modelRepository.save(model);
    }

    public void cleanUpDatabase() {
        offerRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        modelRepository.deleteAll();
        specieRepository.deleteAll();
    }
}
