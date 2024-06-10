package com.lge.ocpi.emsp.service.cdr;

import com.lge.ocpi.emsp.model.dto.cdr.CdrDto;
import com.lge.ocpi.emsp.model.entity.cdr.Cdr;

public interface CdrService {

    public CdrDto postCdr(Cdr cdr);

    public CdrDto getCdr(String cdrId);

}
