package com.brcodigo.app.impl.sankey;

import com.brcodigo.app.impl.sankey.model.SankeyData;
import com.brcodigo.app.impl.sankey.repository.SankeyDataRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SankeyDataService {

    @Autowired
    private SankeyDataRepository sankeyDataRepository;

    public List<SankeyData> getAllSankeyData() {
        return sankeyDataRepository.findAll();
    }

    public Optional<SankeyData> getSankeyDataById(String id) {
        return sankeyDataRepository.findById(id);
    }

    public SankeyData createSankeyData(SankeyData sankeyData) {
        sankeyData.setValue(1);
        var newSankey = sankeyDataRepository.save(sankeyData);
        updateAll();
        return newSankey;
    }

    public SankeyData updateSankeyData(SankeyData sankeyData) {
        var optSankey = sankeyDataRepository.findById (sankeyData.getId());
        if(optSankey.isPresent()){
            var foundSankey = optSankey.get();
            foundSankey.setValue(1);
            foundSankey.setSource(sankeyData.getSource());
            foundSankey.setTarget(sankeyData.getTarget());
            var updateSankey = sankeyDataRepository.save(foundSankey);
            updateAll();
            return updateSankey;
        } else {
            return null;
        }
    }

    public void deleteSankeyData(String id) {
        sankeyDataRepository.deleteById(id);
        updateAll();
    }

    public void updateAll() {
        List<SankeyData> all = sankeyDataRepository.findAll();
        if (!ObjectUtils.isEmpty(all)) {
            all.forEach(sankeyData -> {
                List<SankeyData> list = sankeyDataRepository.findBySource(sankeyData.getTarget());
                if (!ObjectUtils.isEmpty(list) && !list.isEmpty()) {
                    sankeyData.setValue(list.size());
                    sankeyDataRepository.save(sankeyData);
                }
            });
        }
    }
}