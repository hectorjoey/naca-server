package fhi360.it.assetverify.service;

import fhi360.it.assetverify.model.BinCard;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Service
public interface BinCardService {

    BinCard addBinCard(BinCard binCard);

    List<BinCard> getAllBinCards();

}