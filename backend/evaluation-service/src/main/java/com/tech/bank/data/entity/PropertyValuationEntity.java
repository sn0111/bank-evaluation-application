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
@Table(name = "PROPERTY_VALUATIONS")
@Audited(withModifiedFlag = true)
@AuditTable(schema = "bank_audit", value = "PROPERTY_VALUATIONS_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class PropertyValuationEntity extends AuditEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "valuation_id")
    private Long facilityDetailId;

    @Column(name = "valuation_uuid", unique = true, length = 40)
    private String valuationUuid;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "facility_type", length = 150)
    private EvaluationType evaluationType;

    @Column(name = "fos_ref_number", length = 150)
    private String fosRefNumber;

    @Column(name = "is_fos_ref")
    private boolean isFosRef;

    @Override
    public void setUuid(String uuid) {
        setValuationUuid(uuid);
    }
}
