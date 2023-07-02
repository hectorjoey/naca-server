package fhi360.it.assetverify.serviceImpl;

import fhi360.it.assetverify.model.Inventory;
import fhi360.it.assetverify.repository.InventoryRepository;
import fhi360.it.assetverify.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public Page<Inventory> getHealthCommodities(Pageable pageable) {
        Page<Inventory> result = inventoryRepository.findByOrderByIdAsc(pageable);
        for (Inventory value : result) {
            value.setShellLife(calculateSLife(value.getManufactureDate(), value.getExpiryDate()));
            value.setLosses(calLosses(value.getLosses()));
            value.setQuantityReceived(value.getQuantityReceived());
            value.setNegativeAdjustment(calNegativeAdjustment(value.getNegativeAdjustment()));
            value.setPositiveAdjustment(calPositiveAdjustment(value.getPositiveAdjustment()));
            value.setStockOnHand(calStockOnHand(value.getOpeningBalance(), value.getQuantityReceived(), value.getQuantityIssued(), value.getLosses(), value.getPositiveAdjustment(), value.getNegativeAdjustment()));
            value.setStockBalance(calStockBal(value.getStockBalance()));
            value.setQuantityIssued(calQuantityIssued(value.getQuantityIssued()));
        }
        return result;
    }

    private String calQuantityIssued(String quantityIssued) {
        if (quantityIssued == null) {
            return "0";
        } else {
            return quantityIssued;
        }
    }

    private String calStockOnHand(String openingBalance, String quantityReceived, String quantityIssued, String losses, String positiveAdjustment, String negativeAdjustment) {
        try {
            int calculatedStock = parseOrDefault(openingBalance) + parseOrDefault(quantityReceived)
                    - parseOrDefault(quantityIssued) - parseOrDefault(losses)
                    + parseOrDefault(positiveAdjustment) - parseOrDefault(negativeAdjustment);
            return String.valueOf(calculatedStock);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Invalid input found";
        }
    }

    private int parseOrDefault(String value) {
        return value != null && !value.isEmpty() ? Integer.parseInt(value) : 0;
    }

    private String calStockBal(String stockBalance) {
        if (stockBalance == null) {
            return "0";
        } else {
            return stockBalance;
        }
    }

    private String calPositiveAdjustment(String positiveAdjustment) {
        if (positiveAdjustment == null) {
            return "0";
        } else {
            return positiveAdjustment;
        }
    }

    private String calNegativeAdjustment(String negativeAdjustment) {
        if (negativeAdjustment == null) {
            return "0";
        } else {
            return negativeAdjustment;
        }
    }

    private String calLosses(String losses) {
        if (losses == null) {
            return "0";
        } else {
            return losses;
        }
    }

    private String calculateSLife(String manufactureDate, String expiryDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy");
        try {
            if (expiryDate.isEmpty()) {
                return "No Date found"; // or handle the empty expiry date in an appropriate way
            }

            LocalDate startDate = LocalDate.now(); // Today's date
            LocalDate endDate = LocalDate.parse(expiryDate, df);

            int months = Math.toIntExact(Period.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(1)).toTotalMonths());

            if (months <= 0) {
                return "0";
            } else {
                return String.valueOf(months);
            }
        } catch (DateTimeParseException e) {
            // Handle the parsing error, e.g., log an error message or throw a custom exception.
            e.printStackTrace();
            return "Invalid Date found";
        }
    }
}