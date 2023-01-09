package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;

import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("select sum(quantity) from Donation")
    Optional<Integer> getNumberOfBags();

    boolean existsDonationByInstitutionId(Long id);

    boolean existsDonationByCategoriesId(Long id);

}
