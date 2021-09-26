package coder.teamplte.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import coder.teamplte.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
   User findByEmail(String email);
}
