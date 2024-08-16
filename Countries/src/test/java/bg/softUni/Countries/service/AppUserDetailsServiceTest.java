package bg.softUni.Countries.service;




import bg.softUni.Countries.entity.Level;
import bg.softUni.Countries.entity.Role;
import bg.softUni.Countries.entity.User;
import bg.softUni.Countries.entity.UsersRoles;
import bg.softUni.Countries.repository.UserRepository;
import bg.softUni.Countries.service.session.AppUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AppUserDetailsServiceTest {
    private static final String TEST_USERNAME = "zzzxxx";
    private static final String NOT_EXISTENT_USERNAME = "noone";

    private AppUserDetailsService toTest;
    @Mock
    private UserRepository mockUserRepository;



    @BeforeEach
    void setUp() {
        toTest = new AppUserDetailsService(mockUserRepository);
    }
    @Test
    void  testLoadUserByUsername_UserFound() {
        Role role=new Role();
        role.setName(UsersRoles.USER);
        Role role2=new Role();
        role2.setName(UsersRoles.ADMIN);

        User testUser = new User();
        testUser.setUsername(TEST_USERNAME);
        testUser.setPassword("12345");
        testUser.setFullName("zzzxxx");
        testUser.setAge(45);
        testUser.setEmail("zzz@abv.bg");
        testUser.setRoles(Set.of(
                role,role2
        ));
        testUser.setLevel(Level.INTERMEDIATE);



        when(mockUserRepository.findByUsername(TEST_USERNAME))
                .thenReturn(Optional.of(testUser));


        UserDetails userDetails = toTest.loadUserByUsername(TEST_USERNAME);


        Assertions.assertEquals(TEST_USERNAME,userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(),userDetails.getPassword());




    }



    @Test
    void testLoadUserByUsername_UserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTENT_USERNAME)
        );
    }
}
