package com.tech.bank.dao;

import com.tech.bank.data.entity.CommentsEntity;
import com.tech.bank.data.entity.CurrencyEntity;
import com.tech.bank.data.entity.DocumentTypeEntity;
import com.tech.bank.data.entity.ReferenceNumberEntity;
import com.tech.bank.data.repository.CommentsRepository;
import com.tech.bank.data.repository.CurrencyRepository;
import com.tech.bank.data.repository.DocumentTypeRepository;
import com.tech.bank.data.repository.ReferenceNumberRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CommonsDAO {

    private final DocumentTypeRepository documentTypeRepository;
    private final CurrencyRepository currencyRepository;
    private final CommentsRepository commentsRepository;

    private final ReferenceNumberRepository referenceNumberRepository;

    public CommonsDAO(DocumentTypeRepository documentTypeRepository, CurrencyRepository currencyRepository, CommentsRepository commentsRepository, ReferenceNumberRepository referenceNumberRepository) {
        this.documentTypeRepository = documentTypeRepository;
        this.currencyRepository = currencyRepository;
        this.commentsRepository = commentsRepository;
        this.referenceNumberRepository = referenceNumberRepository;
    }

    public List<DocumentTypeEntity> getDocumentTypes(){
        return documentTypeRepository.findAll();
    }

    public Optional<DocumentTypeEntity> getDocumentByUuid(String documentUuid){
        return documentTypeRepository.findByDocumentUuid(documentUuid);
    }

    public List<CurrencyEntity> getCurrencies(){
        return currencyRepository.findAll();
    }

    public List<CommentsEntity> getComments(){
        return commentsRepository.findAll();
    }

    public Optional<CurrencyEntity> getCurrencyByUuid(String currencyUuid){
        return currencyRepository.findByCurrencyUuid(currencyUuid);
    }

    public Optional<ReferenceNumberEntity> getReferenceNumber(String referenceName){
        return referenceNumberRepository.findByReferenceName(referenceName);
    }

    public ReferenceNumberEntity saveReference(ReferenceNumberEntity entity){
        return referenceNumberRepository.save(entity);
    }
}
