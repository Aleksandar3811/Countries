package bg.softUni.Countries.service;

import bg.softUni.Countries.entity.User;
import bg.softUni.Countries.entity.dto.UserRegisterDTO;
import bg.softUni.Countries.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }


    public void register(UserRegisterDTO userRegisterDto) {

        User user = this.modelMapper.map(userRegisterDto, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        this.userRepository.save(user);

    }
}
