package bg.exam.animal4me.service;



import bg.exam.animal4me.model.entity.UserEntity;
import bg.exam.animal4me.model.entity.UserRoleEntity;
import bg.exam.animal4me.model.enums.UserRoleEnum;
import bg.exam.animal4me.model.user.Animal4meUserDetails;
import bg.exam.animal4me.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Animal4meUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private Animal4meUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new Animal4meUserDetailsService(mockUserRepo);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        UserEntity testUserEntity = new UserEntity()
                .setEmail("test@test.com")
                .setPassword("11111")
                .setTelephone("0888000000")
                .setFirstName("Gosho")
                .setLastName("Georgiev")
                .setActive(true)
                .setImageUrl("http://image.com/image")
                .setUserRoles(List.of(new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN),
                                (new UserRoleEntity().setUserRole(UserRoleEnum.USER))));

        when(mockUserRepo.findByEmail(testUserEntity.getEmail())).thenReturn(Optional.of(testUserEntity));
        Animal4meUserDetails userDetails = (Animal4meUserDetails) toTest.loadUserByUsername(testUserEntity.getEmail());
        Assertions.assertEquals(testUserEntity.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getFirstName(), userDetails.getFirstName());
        Assertions.assertEquals(testUserEntity.getLastName(), userDetails.getLastName());
        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUserEntity.getTelephone(), userDetails.getTelephone());
        Assertions.assertEquals(testUserEntity.getFirstName() + " " + testUserEntity.getLastName(), userDetails.getFullName());

        var authorities = userDetails.getAuthorities();
        Assertions.assertEquals(2, authorities.size());
        var authoritiesIter = authorities.iterator();
        Assertions.assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(), authoritiesIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + UserRoleEnum.USER.name(), authoritiesIter.next().getAuthority());

    }

    @Test
    void testLoadUserByUsername_UserDoesNotExists() {

        Assertions.assertThrows(UsernameNotFoundException.class
                ,()-> toTest.loadUserByUsername("not-such-mail@test.com"));

    }
}
