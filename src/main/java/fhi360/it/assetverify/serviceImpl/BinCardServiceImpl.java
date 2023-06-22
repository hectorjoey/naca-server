package fhi360.it.assetverify.serviceImpl;


import fhi360.it.assetverify.model.BinCard;
import fhi360.it.assetverify.model.ConsolidatedStockReport;
import fhi360.it.assetverify.model.Inventory;
import fhi360.it.assetverify.model.StockStatusReport;
import fhi360.it.assetverify.repository.BinCardRepository;
import fhi360.it.assetverify.repository.InventoryRepository;
import fhi360.it.assetverify.repository.StockStatusReportRepository;
import fhi360.it.assetverify.service.BinCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class BinCardServiceImpl implements BinCardService {
    private final StockStatusReportRepository stockStatusReportRepository;
    private final InventoryRepository inventoryRepository;
    private final BinCardRepository binCardRepository;


    @Override
    public BinCard addBinCard(@RequestBody BinCard binCard) {
        int closingStock = Integer.parseInt(binCard.getStockBalance()) - Integer.parseInt(binCard.getQuantityIssued());

        int receivedQty = Integer.parseInt(binCard.getQuantityReceived());
        int stockOnHand = (Integer.parseInt(binCard.getStockBalance()) + Integer.parseInt(binCard.getQuantityIssued())) - Integer.parseInt(binCard.getQuantityIssued());

        Inventory inventory = inventoryRepository.findById(binCard.getInventoryId()).get();
        String startDateString = binCard.getManufactureDate();
        String endDateString = binCard.getExpiryDate();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy");

        LocalDate startDate = LocalDate.parse(startDateString, df);
        LocalDate endDate = LocalDate.parse(endDateString,  df);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        int mos = receivedQty/stockOnHand;
        binCard.setClosingStock(String.valueOf(closingStock));
        binCard.setStockBalance(String.valueOf(closingStock));
        binCard.setMos(String.valueOf(mos));
//        binCard.setLossesAndAdjustments(inventory.getLossesAndAdjustments());

        String quantityIssued = binCard.getQuantityIssued();

        int sumIssued = Integer.parseInt(quantityIssued);

//        computeSum(sumIssued);
        System.out.println(quantityIssued);
        if (inventory != null) {
            inventory.setClosingStock(String.valueOf(closingStock));
            inventory.setStockOnHand(String.valueOf(closingStock));
            inventory.setStockBalance(String.valueOf(closingStock));
            inventory.setQuantityIssued(quantityIssued);
            System.out.println("inventory balance:: " + closingStock);
            inventory.setMos(String.valueOf(mos));
            inventory.setShellLife(String.valueOf(daysBetween));
        }

        return binCardRepository.save(binCard);
    }

    @Override
    public List<BinCard> getAllBinCards() {
        return binCardRepository.findAll();
    }
}
