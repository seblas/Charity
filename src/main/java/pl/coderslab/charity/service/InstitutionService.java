package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.repository.CategoryRepository;

@Service
public class InstitutionService {

    private final CategoryRepository categoryRepository;

    public InstitutionService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
