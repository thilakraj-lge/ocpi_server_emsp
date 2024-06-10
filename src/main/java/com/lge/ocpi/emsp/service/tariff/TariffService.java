package com.lge.ocpi.emsp.service.tariff;

import com.lge.ocpi.emsp.model.dto.tariff.TariffDto;
import com.lge.ocpi.emsp.model.entity.tariff.Tariff;

public interface TariffService {
    TariffDto pushTariff(Tariff tarrif);

    TariffDto getTariff(String tariffId);

    void deleteTariff(String tariffId);
}
