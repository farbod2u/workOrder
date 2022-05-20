package de.god.workorder.service;

import de.god.workorder.entity.Part;
import de.god.workorder.entity.ValidationHistory;
import de.god.workorder.entity.WorkOrder;
import de.god.workorder.exception.WorkOrderValidationException;
import de.god.workorder.repository.CurrencyRepository;
import de.god.workorder.repository.DepartmentRepository;
import de.god.workorder.repository.ValidationHistoryRepository;
import de.god.workorder.repository.WorkOrderTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Saeed Safaeian.
 */
@Service
@RequiredArgsConstructor
public class ValidationService {

    private final ValidationHistoryRepository validationHistoryRepository;
    private final CurrencyRepository currencyRepository;
    private final WorkOrderTypeRepository workOrderTypeRepository;
    private final DepartmentRepository departmentRepository;

    /**
     * save each validation to DB
     *
     * @param history
     * @return history
     */
    public ValidationHistory saveValidation(ValidationHistory history) {
        validationHistoryRepository.save(history);
        return history;
    }

    /**
     * @param workOrder
     * @return String
     */
    public String Validate(WorkOrder workOrder) {
        List<String> errors = new ArrayList<>();

        if (!workOrderTypeRepository.existsById(workOrder.getType()))
            errors.add("Work order type is invalid.");

        /**
         * validation rules for all work order types here
         */
        validateForAll(workOrder, errors);

        if (workOrder.getType() == null)
            errors.add("Type must has value.");
        else {
            /**
             * validation rules for specific work order type in switch/case
             */
            switch (workOrder.getType()) {
                /*case "ANALYSIS":
                    break;*/

                case "REPAIR":
                    validateForRepair(workOrder, errors);
                    break;

                case "REPLACEMENT":
                    validateForReplacement(workOrder, errors);

                    break;
            }
        }

        /**
         * if any error occurred in rule validation then throw exception
         */
        if (errors.size() > 0) {
            saveValidation(new ValidationHistory(null, null, workOrder.getType(),
                    workOrder.getDepartment(), ValidationHistory.StatusType.INVALID));
            throw new WorkOrderValidationException(errors.toString());
        }

        saveValidation(new ValidationHistory(null, null, workOrder.getType(),
                workOrder.getDepartment(), ValidationHistory.StatusType.VALID));

        return "VALID";
    }

    private void validateForReplacement(WorkOrder workOrder, List<String> errors) {
        validateFactoryName(workOrder, errors);
        validateFactoryOrderNumber(workOrder, errors);
        validateInventoryNumbers(workOrder, errors);
    }

    private void validateInventoryNumbers(WorkOrder workOrder, List<String> errors) {
        if (workOrder.getParts() != null) {
            for (Part part : workOrder.getParts())
                if (part.getInventory_number() == null) {
                    errors.add("All parts Inventory_Numbers must has value.");
                    break;
                }
        }
    }

    private void validateFactoryOrderNumber(WorkOrder workOrder, List<String> errors) {
        if (workOrder.getFactory_order_number() != null) {
            if (!workOrder.getFactory_order_number().matches("[[A-Z]||[a-z]]{2}[0-9]{8}"))
                errors.add("Factory_Order_Number must start with 2 letter and end with 8 number.");
        }
    }

    private void validateFactoryName(WorkOrder workOrder, List<String> errors) {
        if (workOrder.getFactory_name() == null || workOrder.getFactory_name().trim().length() < 1)
            errors.add("Factory_Name must has value.");
    }

    private void validateForRepair(WorkOrder workOrder, List<String> errors) {
        validateAnalysisDate(workOrder, errors);
        validateResponsiblePerson(workOrder, errors);
        validateTestDate(workOrder, errors);
        validatePartsCount(workOrder, errors);
    }

    private void validatePartsCount(WorkOrder workOrder, List<String> errors) {
        if (workOrder.getParts() != null)
            if (workOrder.getParts().stream().mapToInt(part -> part.getCount() != null ? part.getCount() : 0).sum() <= 0)
                errors.add("Total count of Parts must be greater than 0.");
    }

    private void validateTestDate(WorkOrder workOrder, List<String> errors) {
        if (workOrder.getTest_date() == null)
            errors.add("Test_Date must has value.");
        else {
            if (workOrder.getAnalysis_date() != null && workOrder.getTest_date().compareTo(workOrder.getAnalysis_date()) <= 0)
                errors.add("Test_Date must be after Analysis_Date.");
            if (workOrder.getEnd_date() != null && workOrder.getTest_date().compareTo(workOrder.getEnd_date()) >= 0)
                errors.add("Test_Date must be before End_Date.");
        }
    }

    private void validateResponsiblePerson(WorkOrder workOrder, List<String> errors) {
        if (workOrder.getResponsible_person() == null || workOrder.getResponsible_person().trim().length() < 1)
            errors.add("Responsible_Person must has value.");
    }

    private void validateAnalysisDate(WorkOrder workOrder, List<String> errors) {
    /*if (workOrder.getAnalysis_date() == null)
        errors.add("Analysis_Date must has value.");
    else {*/
        if (workOrder.getAnalysis_date() != null) {
            if (workOrder.getStart_date() != null && workOrder.getAnalysis_date().compareTo(workOrder.getStart_date()) <= 0)
                errors.add("Analysis_Date must be after Start_Date.");
            if (workOrder.getEnd_date() != null && workOrder.getAnalysis_date().compareTo(workOrder.getEnd_date()) >= 0)
                errors.add("Analysis_Date must be before End_Date.");
        }
    }

    private void validateForAll(WorkOrder workOrder, List<String> errors) {
        validateDepartment(workOrder, errors);
        validateStartDate(workOrder, errors);
        validateEndDate(workOrder, errors);
        validateCurrency(workOrder, errors);
        validateCost(workOrder, errors);
    }

    private void validateCost(WorkOrder workOrder, List<String> errors) {
        if (workOrder.getCost() == null)
            errors.add("Cost must has value.");
        else if (workOrder.getCost() <= 0D)
            errors.add("Cost must greater that 0.");
    }

    private void validateCurrency(WorkOrder workOrder, List<String> errors) {
        if (!currencyRepository.existsById(workOrder.getCurrency()))
            errors.add("Currency is not valid ISO 4217 curency code.");
    }

    private void validateEndDate(WorkOrder workOrder, List<String> errors) {
        if (workOrder.getEnd_date() == null)
            errors.add("End_Date must has value.");
        else if (workOrder.getStart_date() != null && workOrder.getEnd_date().compareTo(workOrder.getStart_date()) <= 0)
            errors.add("End_Date must be after Start_Date");
    }

    private void validateStartDate(WorkOrder workOrder, List<String> errors) {
        if (workOrder.getStart_date() == null)
            errors.add("Start_Date must has value.");
        else if (workOrder.getStart_date().compareTo(LocalDate.now()) >= 0)
            errors.add("Start_Date must before current date.");
    }

    private void validateDepartment(WorkOrder workOrder, List<String> errors) {
        if (workOrder.getDepartment() == null || workOrder.getDepartment().trim().length() < 1)
            errors.add("Department must has value.");
        else if (!departmentRepository.existsById(workOrder.getDepartment()))
            errors.add("Department is invalied.");
    }
}
