package com.tech.bank.servie;

import com.tech.bank.dao.CommonsDAO;
import com.tech.bank.dao.PropertyEvaluationDAO;
import com.tech.bank.dao.UserDAO;
import com.tech.bank.data.dto.*;
import com.tech.bank.data.entity.*;
import com.tech.bank.util.BankEvaluationException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Objects;

@Service
public class PropertyEvaluationService {

    private final PropertyEvaluationDAO propertyEvaluationDAO;

    private final ModelMapper modelMapper;

    private final CommonsDAO commonsDAO;

    private final UserDAO userDAO;

    public PropertyEvaluationService(PropertyEvaluationDAO propertyEvaluationDAO, ModelMapper modelMapper, CommonsDAO commonsDAO, UserDAO userDAO) {
        this.propertyEvaluationDAO = propertyEvaluationDAO;
        this.modelMapper = modelMapper;
        this.commonsDAO = commonsDAO;
        this.userDAO = userDAO;
    }

    private String generatePv() throws BankEvaluationException {
        ReferenceNumberEntity reference = commonsDAO.getReferenceNumber("PV").orElseThrow(() -> new BankEvaluationException("Reference generator not found reference name"));
        StringBuilder sb = new StringBuilder();
        sb.append(reference.getReferenceName());
        LocalDate currentDate = LocalDate.now();
        int currentMonthValue = currentDate.getMonthValue();
        sb.append(Year.now());
        String month = String.format("%02d", currentMonthValue);
        sb.append(month);
        String formattedNumber = String.format("%03d", reference.getReferenceNumber()+1);
        sb.append(formattedNumber);

        Long i = reference.getReferenceNumber() + 1;
        reference.setReferenceNumber(i);
        // Update new reference number
        ReferenceNumberEntity reference1 = commonsDAO.saveReference(reference);
        System.out.println(reference1);
        return sb.toString();
    }

    @Transactional
    public PropertyEvaluationDTO savePropertyEvaluation(PropertyEvaluationDTO dto) throws BankEvaluationException {
        FacilityDetailsDTO facilityDetails = dto.getFacilityDetails();
        CurrencyEntity currency = commonsDAO.getCurrencyByUuid(facilityDetails.getCurrency().getCurrencyUuid())
                .orElseThrow(() -> new BankEvaluationException("Currency not found"));
        FacilityDetailsEntity facilityDetailsEntity = modelMapper.map(facilityDetails, FacilityDetailsEntity.class);
        facilityDetailsEntity.setCurrency(currency);

        FacilityDetailsEntity savedFacility = propertyEvaluationDAO.saveFacilityDetails(facilityDetailsEntity);

        PropertyEvaluationEntity entity = modelMapper.map(dto, PropertyEvaluationEntity.class);
        entity.setReference(generatePv());

        List<CommentsEntity> comments = entity.getComments();
        List<BorrowersDetailsEntity> borrowersDetails = entity.getBorrowersDetails();
        entity.setFacilityDetails(savedFacility);
        entity.setComments(null);
        entity.setBorrowersDetails(null);
        entity.setDocuments(null);
        PropertyEvaluationEntity propertyEvaluationEntity = propertyEvaluationDAO.savePropertyEvaluation(entity);

        for(DocumentsDTO documentsDTO:dto.getDocuments()){
            DocumentsEntity documentsEntity = propertyEvaluationDAO.getDocumentByUuid(documentsDTO.getDocumentsUuid())
                    .orElseThrow(() -> new BankEvaluationException("Document not found"));
            documentsEntity.setPropertyEvaluation(propertyEvaluationEntity);
            propertyEvaluationDAO.saveDocument(documentsEntity);
        }

        for (CommentsEntity e : comments) {
            e.setPropertyEvaluation(propertyEvaluationEntity);
        }
        List<CommentsEntity> commentsEntities = propertyEvaluationDAO.saveComments(comments);
        for (BorrowersDetailsEntity e : borrowersDetails) {
            e.setPropertyEvaluation(propertyEvaluationEntity);
        }
        List<BorrowersDetailsEntity> borrowersDetailsEntities = propertyEvaluationDAO.saveBorrowerDetails(borrowersDetails);
        PropertyEvaluationDTO propertyEvaluationDTO = modelMapper.map(propertyEvaluationEntity, PropertyEvaluationDTO.class);
        List<CommentsDTO> commentsDTOS = commentsEntities.stream().map(e -> modelMapper.map(e, CommentsDTO.class)).toList();
        propertyEvaluationDTO.setComments(commentsDTOS);
        List<BorrowersDetailsDTO> borrowersDetailsDTOS = borrowersDetailsEntities.stream().map(e -> modelMapper.map(e, BorrowersDetailsDTO.class)).toList();
        propertyEvaluationDTO.setBorrowersDetails(borrowersDetailsDTOS);

        return propertyEvaluationDTO;
    }

    public List<PropertyEvaluationDTO> getPropertyEvaluations(){
        return propertyEvaluationDAO.getAllPropertyEvaluation()
                .stream()
                .map(e-> {
                    e.setDocuments(List.of());
                    return modelMapper.map(e, PropertyEvaluationDTO.class);
                })
                .toList();
    }

    @Transactional
    public DocumentsDTO saveDocument(MultipartFile file, String documentTypeUuid) throws BankEvaluationException, IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        DocumentTypeEntity documentType = commonsDAO.getDocumentByUuid(documentTypeUuid).orElseThrow(() -> new BankEvaluationException("Document type not found"));
        DocumentsEntity entity = new DocumentsEntity();
        entity.setDocumentType(documentType);
        entity.setFile(file.getBytes());
        entity.setName(fileName);
        entity.setSize(String.valueOf(file.getSize()));
        DocumentsEntity documentsEntity = propertyEvaluationDAO.saveDocument(entity);
        DocumentsDTO dto = modelMapper.map(documentsEntity, DocumentsDTO.class);
        String url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(documentsEntity.getDocumentsUuid())
                .toUriString();
        dto.setUrl(url);
        return dto;
    }

    @Transactional
    public DocumentsDTO getDocument(String documentsUuid) throws BankEvaluationException {
        DocumentsEntity documents = propertyEvaluationDAO.getDocumentByUuid(documentsUuid)
                .orElseThrow(() -> new BankEvaluationException("Document not found"));
        return modelMapper.map(documents,DocumentsDTO.class);
    }

    @Transactional
    public String deleteDocument(String documentsUuid) throws BankEvaluationException {
        DocumentsEntity documents = propertyEvaluationDAO.getDocumentByUuid(documentsUuid)
                .orElseThrow(() -> new BankEvaluationException("Document not found"));
        propertyEvaluationDAO.deleteDocument(documents);
        return "Document deleted successfully";
    }

    @Transactional(readOnly = true)
    public List<CommentsDTO> getComments(){
        return commonsDAO.getComments()
                .stream().map(e->modelMapper.map(e,CommentsDTO.class))
                .toList();
    }
}
