package pl.coderslab.charity.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.domain.Donation;

import java.util.List;

@Repository
@Transactional
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT d.quantity FROM Donation d")
    List<Integer> findAllBags();
}
