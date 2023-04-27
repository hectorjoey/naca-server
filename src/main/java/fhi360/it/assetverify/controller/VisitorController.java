
package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.model.Visitor;
import fhi360.it.assetverify.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({ "/api/v1/" })
@RequiredArgsConstructor
public class VisitorController
{
    private final VisitorRepository visitorRepository;
    
    @GetMapping({ "visitors" })
    List<Visitor> getAllVisitors() {
        return (List<Visitor>)this.visitorRepository.findAll();
    }

}
