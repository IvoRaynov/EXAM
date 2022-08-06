package bg.exam.animal4me.model.mapper;

import bg.exam.animal4me.model.dto.user.UserRegisterDTO;
import bg.exam.animal4me.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    UserEntity userDtoToUserEntity(UserRegisterDTO registerDTO);
}