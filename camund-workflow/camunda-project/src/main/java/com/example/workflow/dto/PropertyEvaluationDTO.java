package com.example.workflow.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyEvaluationDTO {

    private String propertyEvaluationUuid;
    @NotNull
    private String initiatorName;
    @NotNull
    private String initiatorBusinessUnit;
    @NotNull
    private String initiatorContactNumber;
    @Valid
    private FacilityDetailsDTO facilityDetails;
    @NotNull
    private EvaluationType evaluationType;
    @NotNull
    private String fosRefNumber;
    private boolean isFosRef;
    @Valid
    private List<BorrowersDetailsDTO> borrowersDetails;
    @Valid
    private List<CommentsDTO> comments;
//    @Valid
    private List<DocumentsDTO> documents;
}
