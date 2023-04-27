package fhi360.it.assetverify.service;

import fhi360.it.assetverify.model.Visitor;
import fhi360.it.assetverify.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitorService {
    private final VisitorRepository repository;

    public void saveVisitorInfo(final Visitor visitor) {
        this.repository.save(visitor);
    }
}
