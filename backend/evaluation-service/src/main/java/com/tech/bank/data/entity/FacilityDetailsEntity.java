package com.tech.bank.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FACILITY_DETAILS")
@Audited(withModifiedFlag = true)
@AuditTable(schema = "bank_audit", value = "FACILITY_DETAILS_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class FacilityDetailsEntity extends AuditEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_details_id")
    private Long facilityDetailId;

    @Column(name = "facility_details_uuid", unique = true, length = 40)
    private String facilityDetailUuid;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "facility_type", length = 150)
    private FacilityType facilityType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category", length = 150)
    private Category category;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "valuation_type", length = 150)
    private ValuationType valuationType;

    @Column
    private int term;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id", foreignKey = @ForeignKey(name = "fk_currencies_currency_id"))
    private CurrencyEntity currency;

    @Column
    private int amount;

    @Column(name = "is_housing_loan")
    private boolean isHousingLoan;
//
//    @OneToOne
//    @JoinColumn(name = "facility_details_id", referencedColumnName = "facility_details_id", foreignKey = @ForeignKey(name = "fk_facility_details_facility_details_id"))
//    private PropertyEvaluationEntity propertyEvaluation;

    @Override
    public void setUuid(String uuid) {
        setFacilityDetailUuid(uuid);
    }
}
