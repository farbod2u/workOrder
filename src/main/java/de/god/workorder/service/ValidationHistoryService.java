package de.god.workorder.service;

import de.god.workorder.entity.ValidationHistory;
import de.god.workorder.repository.ValidationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Saeed Safaeian
 */
@Service
@RequiredArgsConstructor
public class ValidationHistoryService {

    private final ValidationHistoryRepository validationHistoryRepository;

    public ValidationHistory save(ValidationHistory history) {
        return validationHistoryRepository.save(history);
    }

    public List<ValidationHistory> getAll(){
        return validationHistoryRepository.findAll();
    }
}
