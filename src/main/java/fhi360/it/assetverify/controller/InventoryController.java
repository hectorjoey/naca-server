
package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.dto.InventoryDto;
import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.Inventory;
import fhi360.it.assetverify.model.StockStatusReport;
import fhi360.it.assetverify.repository.BinCardRepository;
import fhi360.it.assetverify.repository.InventoryRepository;
import fhi360.it.assetverify.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
//@CrossOrigin(origins = "https://naca-client.netlify.app/")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class InventoryController {
    private final InventoryRepository inventoryRepository;
    private final InventoryService inventoryService;


    @GetMapping("all-inventories")
    List<Inventory> getInventories() {
        return inventoryRepository.findAll();
    }


    @GetMapping("all-invent")
    List<Inventory> getInventory() {
        return inventoryService.getInventories();
    }

    //get all inventory
    @GetMapping("inventories")
    public Page<Inventory> getAllInventories(Pageable pageable) {
        return inventoryService.getHealthCommodities(pageable);
    }

    @PostMapping("inventory")
    ResponseEntity<Object> createInventory(@RequestBody Inventory inventory) {
        return new ResponseEntity<>(inventoryService.addInventory(inventory), HttpStatus.CREATED);
    }

    //get inventory by Id
    @GetMapping("invent/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for this id :: " + id));
        return ResponseEntity.ok().body(inventory);
    }

    // For searching inventory
    @GetMapping("inventory/{keyword}")
    public Page<Inventory> getInventories(Pageable pageable, @PathVariable("keyword") String keyword) {
        return inventoryRepository.findAll(pageable, keyword);
    }

    @PutMapping("inventories/{id}")
    public Inventory updateInventory(@PathVariable("id") Long id, @Valid @RequestBody InventoryDto inventoryDto) throws ResourceNotFoundException {
        System.out.println("Update inventory with ID = " + id + "...");
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for this id :: " + id));
        inventory.setOpeningBalance(inventoryDto.getOpeningBalance());
        inventory.setClosingStock(inventoryDto.getClosingStock());
        final Inventory updatedInventory = inventoryRepository.save(inventory);
        System.out.println("Updated Inventory " + updatedInventory);
        return inventoryRepository.save(updatedInventory);
    }


    @PatchMapping("inventorys/{id}")
    public Inventory updateInventorys(@PathVariable("id") Long id, @Valid @RequestBody InventoryDto inventoryDto) throws ResourceNotFoundException {
        System.out.println("Update inventory with ID = " + id + "...");
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for this id :: " + id));
        inventory.setQ1(inventoryDto.getQ1());
        inventory.setQ2(inventoryDto.getQ2());
        inventory.setQ3(inventoryDto.getQ3());
        int totalQ = Integer.parseInt(inventory.getQ1()) + Integer.parseInt(inventory.getQ2()) + Integer.parseInt(inventory.getQ3());
        System.out.println("totalQ ::: " + totalQ);
        int calculateAnc = totalQ / 3;

//        int calculateAnc;
        double roundedUpAnc = Math.ceil(calculateAnc);
        System.out.println("calculateAnc ::: " + calculateAnc);
        inventory.setAnc(String.valueOf(roundedUpAnc));
        System.out.println("rounde ;; ::: " + roundedUpAnc);

        int calculateStockOnHand = Integer.parseInt(inventory.getStockOnHand());
        int calculateMos = calculateStockOnHand / calculateAnc;
        System.out.println("stock onhand ::: " + calculateStockOnHand);
        inventory.setMos(String.valueOf(calculateMos));
        System.out.println("MOs::: " + inventory.getMos());
        System.out.println("Anc::: " + inventory.getAnc());

        final Inventory updatedInventory = inventoryRepository.save(inventory);
        System.out.println("Updated Inventory " + updatedInventory);
        return inventoryRepository.save(updatedInventory);
    }


    @PatchMapping("invent/{id}")
    public Inventory updateAnInventory(@PathVariable("id") Long id, @Valid @RequestBody InventoryDto inventoryDto) throws ResourceNotFoundException {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for this id :: " + id));
//        inventory.setStockBalance(inventoryDto.getStockBalance());
//        inventory.setClosingStock(inventoryDto.getClosingStock());
        inventory.setLosses(inventoryDto.getLosses());
        inventory.setPositiveAdjustment(inventoryDto.getPositiveAdjustment());
        inventory.setNegativeAdjustment(inventoryDto.getNegativeAdjustment());

        final Inventory updatedInventory = inventoryRepository.save(inventory);
        System.out.println("Updated Inventory" + updatedInventory);
//        assetEmailsService.sendEmailWithAttachment(asset1);
        return inventoryRepository.save(updatedInventory);

    }

    @DeleteMapping("inventory/{id}")
    public Map<String, Boolean> deleteInventory(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for this id :: " + id));
        inventoryRepository.delete(inventory);
//        deleteAssetService.deleteAssetEmail(asset);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
