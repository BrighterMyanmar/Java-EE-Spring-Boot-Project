package coder.teamplte.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import coder.teamplte.dtos.UserDto;
import coder.teamplte.models.User;

public interface UserService extends UserDetailsService{
   User save(UserDto userDto);
   User get(String email);
}
