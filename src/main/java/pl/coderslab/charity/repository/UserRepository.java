package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findUserByEmail(String email);
    User findUserByToken(String token);
    List<User> findAllByRolesName(String role);

    @Modifying
    @Query("UPDATE User u SET u.enabled = :status WHERE u.id = :id")
    void updateUserStatus(@Param("id") Long id, @Param("status") int status);

}
