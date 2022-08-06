package bg.exam.animal4me.repository;


import bg.exam.animal4me.model.entity.SpecieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecieRepository extends JpaRepository<SpecieEntity, Long> {
}