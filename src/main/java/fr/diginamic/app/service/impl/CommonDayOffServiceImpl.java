package fr.diginamic.app.service.impl;

import fr.diginamic.app.model.CommonDayOff;
import fr.diginamic.app.repository.CommonDayOffRepository;
import fr.diginamic.app.service.CommonDayOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommonDayOffServiceImpl implements CommonDayOffService {
    @Autowired
    private CommonDayOffRepository commonDayOffRepository;

    @Override
    public CommonDayOff save(CommonDayOff commonDayOff) {
        return commonDayOffRepository.save(commonDayOff);
    }

    @Override
    public List<CommonDayOff> findAll() {
        return commonDayOffRepository.findAll();
    }

    @Override
    public Optional<CommonDayOff> findById(Long id) {
        return commonDayOffRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        commonDayOffRepository.deleteById(id);
    }


}
