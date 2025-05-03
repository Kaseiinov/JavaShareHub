package kg.attractor.javasharehub.service.impl;

import kg.attractor.javasharehub.dto.UserDto;
import kg.attractor.javasharehub.exceptions.SuchEmailAlreadyExistsException;
import kg.attractor.javasharehub.model.Role;
import kg.attractor.javasharehub.model.User;
import kg.attractor.javasharehub.repository.RoleRepository;
import kg.attractor.javasharehub.repository.UserRepository;
import kg.attractor.javasharehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public void addUser(UserDto userDto) throws SuchEmailAlreadyExistsException, RoleNotFoundException {

        boolean isUserExists = userRepository.existsUserByEmail(userDto.getEmail());
        if(isUserExists){
            throw new SuchEmailAlreadyExistsException();
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setEnabled(true);

        Role role = roleRepository.findRoleByRole("USER").orElseThrow(RoleNotFoundException::new);

        user.setRoles(Arrays.asList(role));
        role.setUsers(Arrays.asList(user));

        userRepository.save(user);
    }
}
