package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dto.DonationCreateDto;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.mapper.DonationMapper;
import pl.coderslab.charity.repository.DonationRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;
    private final DonationMapper donationMapper;

    public void save(Donation donation) {
        donationRepository.save(donation);
    }

    public void saveWithDto(DonationCreateDto donationCreateDto) {
        donationRepository.save(donationMapper.donationCreateDtoToDonation(donationCreateDto));
    }

    public Donation findById(long id) {
        return donationRepository.findById(id).orElse(null);
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    public List<Donation> findAllByUserId(long id) {
        return donationRepository.findAllByUserId(id);
    }

    public void update(Donation donation) {
        donationRepository.save(donation);
    }

    public void deleteById(long id) {
        donationRepository.deleteById(id);
    }

    public long countAllDonations() {
        return donationRepository.count();
    }

    public int countAllBags() {
        return donationRepository.getNumberOfBags().orElse(0);
    }

    public boolean existsDonationByInstitutionId (long id) {
        return donationRepository.existsDonationByInstitutionId(id);
    }

    public boolean existsDonationByCategoryId (long id) {
        return donationRepository.existsDonationByCategoriesId(id);
    }

    public boolean existsDonationByUserId (long id) {
        return donationRepository.existsDonationByUserId(id);
    }

}
