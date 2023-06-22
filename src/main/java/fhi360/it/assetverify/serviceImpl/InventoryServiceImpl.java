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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class InventoryServiceImpl  implements InventoryService {
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
            value.setMos(calMonthOfStock(value.getQuantityReceived(), value.getStockOnHand()));
        }
        return result;
    }


    private String calMonthOfStock(String quantityReceived, String stockOnHand) {
        try {
            int received = quantityReceived.isEmpty() ? 0 : Integer.parseInt(quantityReceived);
            int onHand = stockOnHand.isEmpty() ? 0 : Integer.parseInt(stockOnHand);

            if (onHand == 0) {
                return "N/A"; // or any other appropriate value or indication
            } else {
                String mos = String.valueOf(received / onHand);
                return mos;
            }
        } catch (NumberFormatException e) {
            // Handle the parsing error, e.g., log an error message or throw a custom exception.
            e.printStackTrace();
            return "Invalid Quantity";
        }
    }

    private String calculateSLife(String manufactureDate, String expiryDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy");
        try {
            if (manufactureDate.isEmpty() || expiryDate.isEmpty()) {
                return "No Date found"; // or handle the empty date strings in an appropriate way
            }

            LocalDate startDate = LocalDate.parse(manufactureDate, df);
            LocalDate endDate = LocalDate.parse(expiryDate, df);
            return String.valueOf(ChronoUnit.DAYS.between(startDate, endDate));
        } catch (DateTimeParseException e) {
            // Handle the parsing error, e.g., log an error message or throw a custom exception.
            e.printStackTrace();
            return "Invalid Date found";
        }
    }
}
