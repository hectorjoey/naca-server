package fhi360.it.assetverify.serviceImpl;

import fhi360.it.assetverify.model.Inventory;
import fhi360.it.assetverify.repository.InventoryRepository;
import fhi360.it.assetverify.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

}
