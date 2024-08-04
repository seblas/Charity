package pl.coderslab.charity.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.domain.Category;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
