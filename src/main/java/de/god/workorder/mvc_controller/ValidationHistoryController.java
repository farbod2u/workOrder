package de.god.workorder.mvc_controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.god.workorder.entity.WorkOrder;
import de.god.workorder.mvc_controller.model.WorkOrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.net.http.HttpClient;

/**
 * @author Saeed Safaeian
 * MVC controller for index.jsp
 */
@Controller
@RequestMapping("/validation-history")
@RequiredArgsConstructor
@Log4j2
public class ValidationHistoryController {

    private final ObjectMapper objectMapper;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("workOrderModel", new WorkOrderModel());
        return "index";
    }

    @PostMapping
    public String validate(@ModelAttribute("workOrderModel") WorkOrderModel workOrderModel, Model model) {
        try {
            WorkOrder workOrder = objectMapper.readValue(workOrderModel.getJson(), WorkOrder.class);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/api/work-order/validation", workOrder, String.class);
            workOrderModel.setResult(response.getBody());
        } catch (RestClientException e) {
            workOrderModel.setResult(" " + e.getMessage().substring(e.getMessage().indexOf("[") + 1, e.getMessage().indexOf("]")).replaceAll(",","\n"));
        } catch (Exception e) {
            log.log(Level.ERROR, e);
        }

        model.addAttribute("workOrderModel", workOrderModel);

        return "index";
    }
}
