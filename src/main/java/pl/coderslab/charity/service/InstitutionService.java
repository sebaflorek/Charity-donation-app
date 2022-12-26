package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public void save(Institution institution) {
        institutionRepository.save(institution);
    }

    public Institution findById(long id) {
        return institutionRepository.findById(id).orElse(null);
    }

    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    public void update(Institution institution) {
        institutionRepository.save(institution);
    }

    public void deleteById(long id) {
        institutionRepository.deleteById(id);
    }

}
