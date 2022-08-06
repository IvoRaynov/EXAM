package bg.exam.animal4me.model.dto.specie;

import bg.exam.animal4me.model.dto.model.ModelDTO;

import java.util.ArrayList;
import java.util.List;

public class SpecieDTO {

    private String name;
    private List<ModelDTO> models;

    public String getName() {
        return name;
    }

    public SpecieDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelDTO> getModels() {
        return models;
    }

    public SpecieDTO setModels(List<ModelDTO> models) {
        this.models = models;
        return this;
    }

    public SpecieDTO addModel(ModelDTO model) {
        if (this.models == null) {
            this.models = new ArrayList<>();
        }
        this.models.add(model);
        return this;
    }
}