
package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.BinCard;
import fhi360.it.assetverify.repository.BinCardRepository;
import fhi360.it.assetverify.service.BinCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//@CrossOrigin(origins = "https://naca-client.netlify.app/")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class BinCardController {

    private final BinCardRepository binCardRepository;
    private final BinCardService binCardService;

    @GetMapping("all-bincards")
    List<BinCard> getBinCards() {
        return binCardService.getAllBinCards();
    }

    @GetMapping("bincards")
    public Page<BinCard> getAllBinCards(Pageable pageable) {
        return binCardRepository.findByOrderByIdAsc(pageable);
    }


    @PostMapping("bincard")
    ResponseEntity<BinCard> createBinCard(@RequestBody BinCard binCard){
        return new ResponseEntity<>(binCardService.addBinCard(binCard), HttpStatus.CREATED);
    }

    @GetMapping("bincard/{id}")
    public ResponseEntity<BinCard> getBinCardById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        BinCard binCard = binCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BinCard not found for this id :: " + id));
        return ResponseEntity.ok().body(binCard);
    }

    // For searching binCard
    @GetMapping("bincards/{keyword}")
    public Page<BinCard> getAllBinCard(Pageable pageable, @PathVariable("keyword") String keyword) {
        return binCardRepository.findAll(pageable, keyword);
    }


    @GetMapping("bincard/inventory/{inventoryId}")
    public List<BinCard> getBincardByBatchNo(@PathVariable Long inventoryId) {
        return binCardRepository.findByInventoryId(inventoryId);
    }
}
