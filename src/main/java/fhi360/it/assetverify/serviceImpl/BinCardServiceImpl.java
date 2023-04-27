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
        int closingStock = Integer.parseInt(binCard.getClosingStock()) - Integer.parseInt(binCard.getQuantityIssued());
        Inventory inventory = inventoryRepository.findById(binCard.getInventoryId()).get();
        if (inventory != null) {
            inventory.setClosingStock(String.valueOf(closingStock));
            inventory.setStockOnHand(String.valueOf(closingStock));
            inventory.setOpeningBalance(String.valueOf(closingStock));
            System.out.println("inventory balance:: " + closingStock);
        }
        binCard.setClosingStock(String.valueOf(closingStock));
        return binCardRepository.save(binCard);
    }

    @Override
    public List<BinCard> getAllBinCards() {
        return binCardRepository.findAll();
    }
}