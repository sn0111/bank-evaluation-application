package com.tech.bank.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PROPERTIES_EVALUATION")
@Audited(withModifiedFlag = true)
@AuditTable(schema = "bank_audit", value = "PROPERTIES_EVALUATION_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class PropertyEvaluationEntity extends AuditEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_evaluation_id")
    private Long propertyEvaluationId;

    @Column(name = "property_evaluation_uuid", unique = true, length = 40)
    private String propertyEvaluationUuid;

    @Column(name = "initiator_name", length = 100)
    private String initiatorName;

    @Column(name = "initiator_business_unit", length = 100)
    private String initiatorBusinessUnit;

    @Column(name = "initiator_contact_number", length = 100)
    private String initiatorContactNumber;

    @Column(name = "reference", length = 20)
    private String reference;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_details_id", referencedColumnName = "facility_details_id", foreignKey = @ForeignKey(name = "fk_facility_details_facility_details_id"))
    private FacilityDetailsEntity facilityDetails;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "facility_type", length = 150)
    private EvaluationType evaluationType;

    @Column(name = "fos_ref_number", length = 150)
    private String fosRefNumber;

    @Column(name = "is_fos_ref")
    private boolean isFosRef;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "property_evaluation_id", referencedColumnName = "property_evaluation_id", insertable = true,updatable = true, foreignKey = @ForeignKey(name = "fk_properties_evaluation_property_evaluation_id"))
    private List<BorrowersDetailsEntity> borrowersDetails;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "property_evaluation_id", referencedColumnName = "property_evaluation_id", foreignKey = @ForeignKey(name = "fk_properties_evaluation_property_evaluation_id"))
    private List<CommentsEntity> comments;

    @OneToMany
    @JoinColumn(name = "property_evaluation_id", referencedColumnName = "property_evaluation_id", foreignKey = @ForeignKey(name = "fk_properties_evaluation_property_evaluation_id"))
    private List<DocumentsEntity> documents;

    @Override
    public void setUuid(String uuid) {
        setPropertyEvaluationUuid(uuid);
    }
}
