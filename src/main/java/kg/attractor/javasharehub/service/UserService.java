package kg.attractor.javasharehub.service;

import kg.attractor.javasharehub.dto.UserDto;
import kg.attractor.javasharehub.exceptions.SuchEmailAlreadyExistsException;

import javax.management.relation.RoleNotFoundException;

public interface UserService {
    void addUser(UserDto userDto) throws SuchEmailAlreadyExistsException, RoleNotFoundException;

    UserDto getUserByEmail(String email);
}
