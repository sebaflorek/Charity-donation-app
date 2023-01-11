package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("select sum(quantity) from Donation")
    Optional<Integer> getNumberOfBags();

    boolean existsDonationByInstitutionId(Long id);

    boolean existsDonationByCategoriesId(Long id);

    boolean existsDonationByUserId(Long id);

    List<Donation> findAllByUserId(Long userId);

    @Modifying
    @Query("UPDATE Donation d SET d.user = null WHERE d.user.id = :id")
    void setDonationUserAsNull(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Donation d SET d.user = :user WHERE d.user.id = :id")
    void updateDonationUser(@Param("id") Long id, @Param("user") User user);

    @Modifying
    @Query("UPDATE Donation d SET d.status = 1 WHERE d.id = :id")
    void setDonationReceivedStatus(@Param("id") Long donationId);

}
