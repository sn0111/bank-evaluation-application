package com.tech.bank.servie;

import com.tech.bank.dao.CommonsDAO;
import com.tech.bank.data.dto.CurrencyDTO;
import com.tech.bank.data.dto.DocumentTypeDTO;
import com.tech.bank.data.dto.ReferenceNumberDTO;
import com.tech.bank.data.entity.ReferenceNumberEntity;
import com.tech.bank.util.BankEvaluationException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonsService {

    private final CommonsDAO commonsDAO;
    private final ModelMapper modelMapper;

    public CommonsService(CommonsDAO commonsDAO, ModelMapper modelMapper) {
        this.commonsDAO = commonsDAO;
        this.modelMapper = modelMapper;
    }

    public List<CurrencyDTO> getCurrencies(){
        return commonsDAO.getCurrencies()
                .stream()
                .map(e->modelMapper.map(e,CurrencyDTO.class))
                .toList();
    }

    public List<DocumentTypeDTO> getDocumentTypes(){
        return commonsDAO.getDocumentTypes()
                .stream()
                .map(e->modelMapper.map(e,DocumentTypeDTO.class))
                .toList();
    }

    public ReferenceNumberDTO getReference() throws BankEvaluationException {
        ReferenceNumberEntity pv = commonsDAO.getReferenceNumber("PV").orElseThrow(() -> new BankEvaluationException("Reference generator not found reference name"));
        return modelMapper.map(pv, ReferenceNumberDTO.class);
    }
}
