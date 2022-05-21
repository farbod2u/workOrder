package de.god.workorder.service;

import de.god.workorder.entity.ValidationHistory;
import de.god.workorder.repository.ValidationHistoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ValidationHistoryServiceTest {

    @InjectMocks
    private ValidationHistoryService underTest;

    @Mock
    private ValidationHistoryRepository validationHistoryRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        //given
        ValidationHistory validationHistory = new ValidationHistory(null, null,
                "ANALYSIS", "DEP#1", ValidationHistory.StatusType.VALID);
        given(validationHistoryRepository.save(validationHistory)).willReturn(validationHistory);

        //when
        ValidationHistory result = underTest.save(validationHistory);

        //then
        verify(validationHistoryRepository).save(validationHistory);
        assertThat(result).isEqualTo(validationHistory);

    }

    @Test
    void getAll() {
        //given
        List<ValidationHistory> validationHistories = List.of(
                new ValidationHistory(1L, null, "ANALYSYS", "DEP#1", ValidationHistory.StatusType.VALID ),
                new ValidationHistory(2L, null, "REPAIR", "DEP#1", ValidationHistory.StatusType.INVALID ),
                new ValidationHistory(3L, null, "ANALYSYS", "DEP#2", ValidationHistory.StatusType.VALID )
        );
        given(validationHistoryRepository.findAll()).willReturn(validationHistories);

        //when
        List<ValidationHistory> result = underTest.getAll();

        //then
        verify(validationHistoryRepository).findAll();
        assertThat(result).isEqualTo(validationHistories);

    }
}