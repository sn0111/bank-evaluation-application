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
@Table(name = "REFERENCE_NUMBER")
@Audited(withModifiedFlag = true)
@AuditTable(schema = "bank_audit", value = "REFERENCE_NUMBER_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class ReferenceNumberEntity extends AuditEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reference_id")
    private Long referenceId;

    @Column(name = "reference_name", length = 10, unique = true,nullable = false)
    private String referenceName;

    @Column(name = "reference_number")
    private Long referenceNumber;

    @Override
    public void setUuid(String uuid) {

    }
}
