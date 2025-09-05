package com.brcodigo.app.contract.sankey;

import com.brcodigo.app.impl.sankey.SankeyDataService;
import com.brcodigo.app.impl.sankey.model.SankeyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sankey-data")
public class SankeyDataController {

    @Autowired
    private SankeyDataService sankeyDataService;

    // Get all sankey data
    @GetMapping
    public List<SankeyData> getAllSankeyData() {
        return sankeyDataService.getAllSankeyData();
    }

    // Get sankey data by ID
    @GetMapping("/{id}")
    public ResponseEntity<SankeyData> getSankeyDataById(@PathVariable String id) {
        return sankeyDataService.getSankeyDataById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new sankey data
    @PostMapping
    public SankeyData createSankeyData(@RequestBody SankeyData sankeyData) {
        return sankeyDataService.createSankeyData(sankeyData);
    }

    // Update existing sankey data
    @PutMapping("/{id}")
    public ResponseEntity<SankeyData> updateSankeyData(@PathVariable String id, @RequestBody SankeyData sankeyData) {
        return ResponseEntity.ok(sankeyDataService.updateSankeyData(sankeyData));
    }

    // Delete sankey data
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSankeyData(@PathVariable String id) {
        sankeyDataService.deleteSankeyData(id);
        return ResponseEntity.ok().build();
    }
}