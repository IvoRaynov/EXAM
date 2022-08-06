package bg.exam.animal4me.service;
import bg.exam.animal4me.model.dto.model.ModelDTO;
import bg.exam.animal4me.model.dto.specie.SpecieDTO;
import bg.exam.animal4me.model.entity.ModelEntity;
import bg.exam.animal4me.model.entity.SpecieEntity;
import bg.exam.animal4me.repository.SpecieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecieService {

    private final SpecieRepository specieRepository;

    public SpecieService(SpecieRepository specieRepository) {
        this.specieRepository = specieRepository;
    }

    public List<SpecieDTO> getAllSpecies() {
        return specieRepository.
                findAll().
                stream().
                map(this::mapSpecie).
                collect(Collectors.toList());
    }

    private SpecieDTO mapSpecie(SpecieEntity specieEntity) {
        List<ModelDTO> models = specieEntity.
                getModels().
                stream().
                map(this::mapModel).
                toList();

        return new SpecieDTO().
                setModels(models).
                setName(specieEntity.getName());
    }

    private ModelDTO mapModel(ModelEntity modelEntity) {
        return new ModelDTO().
                setId(modelEntity.getId()).
                setName(modelEntity.getName());
    }
}