package bg.exam.animal4me.repository;

import bg.exam.animal4me.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface OfferRepository extends
        JpaRepository<OfferEntity, UUID>,
        JpaSpecificationExecutor<OfferEntity> {

}