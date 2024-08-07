package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.domain.Authority;
import pl.coderslab.charity.repository.AuthorityRepository;


import java.util.List;
import java.util.Optional;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority saveAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    public Optional<Authority> findById(Long id) {
        return authorityRepository.findById(id);
    }

    public Optional<Authority> findByName(String name) {
        return authorityRepository.findByName(name);
    }


    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    public void deleteAuthorityById(Long id) {
        if (!authorityRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Authority not found with id " + id);
        }
        authorityRepository.deleteById(id);
    }

    public void deleteAuthority(Authority authority) {
        if (!authorityRepository.existsById(authority.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Authority " + authority + "not found");
        }
        authorityRepository.delete(authority);
    }

    public Authority updateAuthority(Long id, Authority authority) {
        Optional<Authority> optionalAuthority = authorityRepository.findById(id);
        if (optionalAuthority.isPresent()) {
            authority.setId(id);
            return authorityRepository.save(authority);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Authority not found with id " + id);
        }
    }
}
