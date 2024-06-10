package com.lge.ocpi.emsp.service.tariff;

import com.lge.ocpi.emsp.mapstruct.CommonMapper;
import com.lge.ocpi.emsp.model.dto.tariff.TariffDto;
import com.lge.ocpi.emsp.model.entity.session.Session;
import com.lge.ocpi.emsp.model.entity.tariff.Tariff;
import com.lge.ocpi.emsp.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TariffServiceImpl implements TariffService{
    @Autowired
    private TariffRepository tarrifRepository;

    @Override
    public TariffDto pushTariff(Tariff tarrif) {
        Tariff tariff = tarrifRepository.save(tarrif);
        return CommonMapper.INSTANCE.tariffFromEntity(tariff);
    }

    @Override
    public TariffDto getTariff(String tariffId) {
        Optional<Tariff> tariff = tarrifRepository.findById(tariffId);
        if(tariff.isPresent()){
            return CommonMapper.INSTANCE.tariffFromEntity(tariff.get());
        } else {
            System.out.printf("No tariff found with id %d%n", tariffId);
            return null;
        }
    }

    @Override
    public void deleteTariff(String tariffId) {
        Optional<Tariff> tariff = tarrifRepository.findById(tariffId);
        if(tariff.isPresent()){
            tarrifRepository.deleteById(tariffId);
        } else {
            System.out.printf("No tariff found with id %d%n", tariffId);
        }
    }
}
