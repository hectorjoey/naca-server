package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.dto.AssetDto;
import fhi360.it.assetverify.exception.AlreadyExistsException;
import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.Asset;
import fhi360.it.assetverify.repository.AssetRepository;
import fhi360.it.assetverify.service.AssetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping({"/api/v1/"})
@RequiredArgsConstructor
@Slf4j
public class AssetController {
    //    private final DeleteAssetService deleteAssetService;
//    private final AssetEmailsService assetEmailsService;
    private final AssetRepository assetRepository;
    private final AssetService assetService;

    @GetMapping({"assets"})
    public Page<Asset> getAllAssets(final Pageable pageable) {
        return this.assetRepository.findByOrderByStatesAsc(pageable);
    }

    @GetMapping({"assets/{keyword}"})
    public Page<Asset> getAllAsset(final Pageable pageable, @PathVariable("keyword") final String keyword) {
        return this.assetRepository.findAll(pageable, keyword);
    }

    @GetMapping({"all-assets"})
    List<Asset> getAssets() {
        return this.assetRepository.findAll();
    }

    @GetMapping({"all-assets/{id}"})
    public Optional<Asset> getAssetsById(@PathVariable("id") final Long id) {
        return this.assetRepository.findById(id);
    }

    @GetMapping({"asset/{id}"})
    public ResponseEntity<Asset> getAssetById(@PathVariable("id") final Long id) throws ResourceNotFoundException {
        final Asset asset = this.assetRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Asset not found for this id :: " + id));
        return ResponseEntity.ok().body(asset);
    }

    @PostMapping({"asset/serial/{serialNumber}"})
    public Asset getByAssetSerialNumber(@PathVariable("serialNumber") final String serialNumber) throws ResourceNotFoundException {
        final Asset asset = this.assetRepository.findBySerialNumber(serialNumber);
        if (asset == null) {
            throw new ResourceNotFoundException("Asset not found for this asset Tag " + serialNumber);
        }
        return this.assetRepository.findBySerialNumber(serialNumber);
    }

    @GetMapping({"asset/tag/{assetId}"})
    public Asset getByAssetTag(@PathVariable("assetId") final String assetId) throws ResourceNotFoundException {
        final Asset asset = this.assetRepository.findByAssetId(assetId);
        if (asset == null) {
            throw new ResourceNotFoundException("Asset not found for this asset Tag " + assetId);
        }
        return this.assetRepository.findByAssetId(assetId);
    }

    @PostMapping({"asset"})
    public ResponseEntity<?> createAsset(@Valid @RequestBody final Asset asset) throws AlreadyExistsException, MessagingException {
        final Asset assetsID = this.assetRepository.findByAssetId(asset.getAssetId());
        final Asset assetsSerial = this.assetRepository.findBySerialNumber(asset.getSerialNumber());
        if (assetsID != null) {
            throw new AlreadyExistsException(String.format("Asset with assetsID %s already exist", asset.getAssetId()));
        }
        if (assetsSerial != null) {
            throw new AlreadyExistsException(String.format("Asset with Serial Number %s already exist", asset.getSerialNumber()));
        }
        return new ResponseEntity<>(this.assetService.save(asset), HttpStatus.CREATED);
    }

    @PatchMapping({"asset/{id}"})
    public Asset updateAsset(@PathVariable("id") final long id, @Valid @RequestBody final AssetDto asset) throws ResourceNotFoundException, MessagingException {
        log.debug("Update Asset with Id = {}", id);
        final Asset asset2 = this.assetRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Asset not found for this id :: " + id));
        asset2.setEmailAddress(asset.getEmailAddress());
        asset2.setCustodian(asset.getCustodian());
        asset2.setStatus(asset.getStatus());
        asset2.setPhone(asset.getPhone());
        asset2.setLocation(asset.getLocation());
        asset2.setStates(asset.getStates());
        asset2.setCondition(asset.getCondition());
        final Asset updatedAsset = this.assetRepository.save(asset2);
        log.debug("Updated Asset {}", updatedAsset);
        return this.assetRepository.save(updatedAsset);
    }

    @DeleteMapping({"asset/{id}"})
    public Map<String, Boolean> deleteAsset(@PathVariable("id") final Long id) throws ResourceNotFoundException, MessagingException {
        final Asset asset = this.assetRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Asset not found for this id :: " + id));
        this.assetRepository.delete(asset);
        final Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
