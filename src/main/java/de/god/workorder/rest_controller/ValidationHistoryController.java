package de.god.workorder.rest_controller;

import de.god.workorder.entity.ValidationHistory;
import de.god.workorder.service.ValidationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Saeed Safaeian
 */
@RestController
@RequestMapping("/api/validation-history")
@RequiredArgsConstructor
public class ValidationHistoryController {

    private final ValidationHistoryService validationHistoryService;

    @GetMapping("/getall")
    public ResponseEntity<List<ValidationHistory>> getAll(){
        return new ResponseEntity<>(validationHistoryService.getAll(), HttpStatus.OK);
    }

}
