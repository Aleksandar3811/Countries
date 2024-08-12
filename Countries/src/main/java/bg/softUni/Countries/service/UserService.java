package bg.softUni.Countries.service;

import bg.softUni.Countries.entity.User;
import bg.softUni.Countries.entity.dto.UserProfileDto;
import bg.softUni.Countries.entity.dto.UserRegisterDTO;
import bg.softUni.Countries.repository.UserRepository;
import bg.softUni.Countries.service.helper.UserHelperService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserHelperService userHelperService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, UserHelperService userHelperService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userHelperService = userHelperService;
    }


    public void register(UserRegisterDTO userRegisterDto) {

        User user = this.modelMapper.map(userRegisterDto, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        this.userRepository.save(user);

    }

    public UserProfileDto getProfileData() {
        return modelMapper.map(userHelperService.getUser(), UserProfileDto.class);
    }
}
