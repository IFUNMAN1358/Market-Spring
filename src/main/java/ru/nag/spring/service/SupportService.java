package ru.nag.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nag.spring.domain.Support;
import ru.nag.spring.exception.SupportException.SupportNotFoundException;
import ru.nag.spring.repository.SupportRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportService {

    private final SupportRepository supportRepository;

    public void saveSupport(Support support) {
        supportRepository.save(support);
    }

    public List<Support> getAll() {
        return supportRepository.findAll();
    }

    public Support getSupportById(Integer id) throws SupportNotFoundException {
        return supportRepository.findSupportById(id).orElseThrow(
                () -> new SupportNotFoundException("Support not found")
        );
    }

    public void deleteSupport(Support support) {
        supportRepository.delete(support);
    }

}
