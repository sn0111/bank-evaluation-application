package com.tech.bank.dao;

import com.tech.bank.data.entity.*;
import com.tech.bank.data.repository.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PropertyEvaluationDAO {

    private final PropertyEvaluationRepository propertyEvaluationRepository;

    private final FacilityDetailsRepository facilityDetailsRepository;

    private final CommentsRepository commentsRepository;

    private final BorrowersDetailsRepository borrowersDetailsRepository;

    private final DocumentsRepository documentsRepository;

    public PropertyEvaluationDAO(PropertyEvaluationRepository propertyEvaluationRepository, FacilityDetailsRepository facilityDetailsRepository, CommentsRepository commentsRepository, BorrowersDetailsRepository borrowersDetailsRepository, DocumentsRepository documentsRepository) {
        this.propertyEvaluationRepository = propertyEvaluationRepository;
        this.facilityDetailsRepository = facilityDetailsRepository;
        this.commentsRepository = commentsRepository;
        this.borrowersDetailsRepository = borrowersDetailsRepository;
        this.documentsRepository = documentsRepository;
    }

    public PropertyEvaluationEntity savePropertyEvaluation(PropertyEvaluationEntity entity){
        return propertyEvaluationRepository.save(entity);
    }

    public FacilityDetailsEntity saveFacilityDetails(FacilityDetailsEntity entity){
        return facilityDetailsRepository.save(entity);
    }

    public List<CommentsEntity> saveComments(List<CommentsEntity> entities){
        return commentsRepository.saveAll(entities);
    }

    public List<BorrowersDetailsEntity> saveBorrowerDetails(List<BorrowersDetailsEntity> entities){
        return borrowersDetailsRepository.saveAll(entities);
    }

    public List<PropertyEvaluationEntity> getAllPropertyEvaluation(){
        return propertyEvaluationRepository.findAll();
    }

    public DocumentsEntity saveDocument(DocumentsEntity entity){
        return documentsRepository.save(entity);
    }

    public Optional<DocumentsEntity> getDocumentByUuid(String documentsUuid){
        return documentsRepository.findByDocumentsUuid(documentsUuid);
    }

    public void deleteDocument(DocumentsEntity entity){
        documentsRepository.delete(entity);
    }

}
