package coder.teamplte.services.impls;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import coder.teamplte.dtos.UserDto;
import coder.teamplte.models.Role;
import coder.teamplte.models.User;
import coder.teamplte.models.repositories.UserRepository;
import coder.teamplte.services.UserService;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   private UserRepository userRepository;

   public UserServiceImpl(UserRepository userRepository) {
      super();
      this.userRepository = userRepository;
   }

   @Override
   public User save(UserDto userDto) {
      User user = new User();
      user.setName(userDto.getName());
      user.setEmail(userDto.getEmail());
      user.setPhone(userDto.getPhone());
      user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
      user.setRoles(Arrays.asList(new Role("ROLE_USER")));
      return userRepository.save(user);
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      System.out.println("Email is " + username);
      User user = userRepository.findByEmail(username);
      System.out.println("User is " + user);
      if (user == null) {
         throw new UsernameNotFoundException("Invalid username or password");
      }

      return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
            mapRolesAuthorities(user.getRoles()));
   }

   private Collection<? extends GrantedAuthority> mapRolesAuthorities(Collection<Role> roles) {
      return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
   }

   @Override
   public User get(String email) {
      return userRepository.findByEmail(email);
   }

}
