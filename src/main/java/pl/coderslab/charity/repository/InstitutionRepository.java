package pl.coderslab.charity.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.domain.Institution;

@Repository
@Transactional
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
