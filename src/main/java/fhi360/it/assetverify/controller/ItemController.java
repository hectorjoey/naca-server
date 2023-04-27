package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.Item;
import fhi360.it.assetverify.repository.ItemRepository;
import fhi360.it.assetverify.service.ItemEmailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping({ "/api/v1/" })
@RequiredArgsConstructor
@Slf4j
public class ItemController
{
    private final ItemEmailsService itemEmailsService;
    private final ItemRepository itemRepository;
    
    @GetMapping({ "all-items" })
    List<Item> getItems() {
        return this.itemRepository.findAll();
    }
    
    @GetMapping({ "items" })
    public Page<Item> getAllItems(final Pageable pageable) {
        return this.itemRepository.findAll(pageable);
    }
    
    @GetMapping({ "items/{keyword}" })
    public Page<Item> getAllItem(final Pageable pageable, @PathVariable("keyword") final String keyword) {
        return this.itemRepository.findAll(pageable, keyword);
    }
    
    @GetMapping({ "item/{id}" })
    public ResponseEntity<Item> getItemById(@PathVariable("id") final Long id) throws ResourceNotFoundException {
        
        final Item item = this.itemRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Item not found for this id :: " + id));
        return ResponseEntity.ok().body(item);
    }
    
    @PostMapping({ "item" })
    ResponseEntity<Item> createItem(@RequestBody final Item item) throws MessagingException {
        return new ResponseEntity<>(this.itemRepository.save(item), HttpStatus.CREATED);
    }
    
    @PutMapping({ "item/{id}" })
    public Item updateItem(@PathVariable("id") final long id, @Valid @RequestBody final Item item) throws ResourceNotFoundException, IOException, MessagingException {
        log.debug("Update Item with ID = {}", + id);
        final Item item2 = this.itemRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Item not found for this id :: " + id));
        item2.setStatus(item.getStatus());
        final Item updatedItem = this.itemRepository.save(item2);
        log.debug("Updated Item {}", updatedItem);
        return this.itemRepository.save(updatedItem);
    }
    
    @DeleteMapping({ "item/{id}" })
    public Map<String, Boolean> deleteItem(@PathVariable("id") final Long id) throws ResourceNotFoundException {

        final Item item = this.itemRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Item not found for this id :: " + id));
        this.itemRepository.delete(item);
        final Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
