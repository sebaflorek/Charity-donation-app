package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;

    public void save(Donation donation) {
        donationRepository.save(donation);
    }

    public Donation findById(long id) {
        return donationRepository.findById(id).orElse(null);
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    public void update(Donation donation) {
        donationRepository.save(donation);
    }

    public void deleteById(long id) {
        donationRepository.deleteById(id);
    }

}
