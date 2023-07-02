package fhi360.it.assetverify.serviceImpl;


import fhi360.it.assetverify.model.BinCard;
import fhi360.it.assetverify.model.Inventory;
import fhi360.it.assetverify.repository.BinCardRepository;
import fhi360.it.assetverify.repository.InventoryRepository;
import fhi360.it.assetverify.service.BinCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class BinCardServiceImpl implements BinCardService {
    private final InventoryRepository inventoryRepository;
    private final BinCardRepository binCardRepository;


    @Override
    public BinCard addBinCard(@RequestBody BinCard binCard) {
//        int closingStock = Integer.parseInt(binCard.getOpeningBalance()) + Integer.parseInt(binCard.getQuantityReceived()) - Integer.parseInt(binCard.getQuantityIssued());
//        int lossesAndAdjustments = Integer.parseInt(binCard.getLosses()) + Integer.parseInt(binCard.getPositiveAdjustment()) + Integer.parseInt(binCard.getNegativeAdjustment());
//        int stockOnHand = Integer.parseInt(binCard.getOpeningBalance()) + Integer.parseInt(binCard.getQuantityReceived()) - lossesAndAdjustments;
//
//        binCard.setClosingStock(String.valueOf(closingStock));
//        binCard.setStockBalance(String.valueOf(closingStock));
//        String quantityIssued = binCard.getQuantityIssued();
//        Inventory inventory = inventoryRepository.findById(binCard.getInventoryId()).orElse(null);
//
//
//        System.out.println(quantityIssued);
//        if (inventory != null) {
//            inventory.setClosingStock(String.valueOf(closingStock));
//            inventory.setStockOnHand(String.valueOf(closingStock));
//            inventory.setStockBalance(String.valueOf(stockOnHand));
//            inventory.setOpeningBalance(String.valueOf(closingStock));
//            inventory.setQuantityIssued(quantityIssued);
//        }
        return binCardRepository.save(binCard);
    }

    @Override
    public List<BinCard> getAllBinCards() {
        return binCardRepository.findAll();
    }
}
