package bg.softUni.Countries.service;

import bg.softUni.Countries.entity.Level;
import bg.softUni.Countries.entity.User;
import bg.softUni.Countries.entity.dto.UserProfileDto;
import bg.softUni.Countries.entity.dto.UserRegisterDTO;
import bg.softUni.Countries.repository.UserRepository;
import bg.softUni.Countries.service.helper.UserHelperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserService toTest;

    private UserHelperService helperServiceTest;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {

        helperServiceTest = new UserHelperService(mockUserRepository);

        toTest = new UserService(
                mockUserRepository,
                mockPasswordEncoder,
                new ModelMapper(),
                helperServiceTest
        );

    }

    @Test
    void testUserRegistration() {

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername("zzzxxxx");
        userRegisterDTO.setFullName("zzzxxx");
        userRegisterDTO.setEmail("zzzxxxx@gmail.com");
        userRegisterDTO.setAge(45);
        userRegisterDTO.setPassword("12345");
        userRegisterDTO.setConfirmPassword("12345");
        userRegisterDTO.setLevel(Level.INTERMEDIATE);


        when(mockPasswordEncoder.encode(userRegisterDTO.getPassword()))
                .thenReturn(userRegisterDTO.getPassword() + userRegisterDTO.getPassword());



        toTest.register(userRegisterDTO);


        verify(mockUserRepository).save(userCaptor.capture());

        User actualSavedEntity = userCaptor.getValue();

        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(userRegisterDTO.getUsername(), actualSavedEntity.getUsername());
        Assertions.assertEquals(userRegisterDTO.getFullName(), actualSavedEntity.getFullName());
        Assertions.assertEquals(userRegisterDTO.getAge(), actualSavedEntity.getAge());
        Assertions.assertEquals(userRegisterDTO.getLevel(), actualSavedEntity.getLevel());
        Assertions.assertEquals(userRegisterDTO.getPassword() + userRegisterDTO.getPassword(),
                actualSavedEntity.getPassword());
        Assertions.assertEquals(userRegisterDTO.getEmail(), actualSavedEntity.getEmail());
    }


}
