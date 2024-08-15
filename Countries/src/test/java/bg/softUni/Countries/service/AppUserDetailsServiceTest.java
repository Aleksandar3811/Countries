package bg.softUni.Countries.service;




import bg.softUni.Countries.repository.UserRepository;
import bg.softUni.Countries.service.session.AppUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.userdetails.UsernameNotFoundException;



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
    void testLoadUserByUsername_UserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTENT_USERNAME)
        );
    }
}
