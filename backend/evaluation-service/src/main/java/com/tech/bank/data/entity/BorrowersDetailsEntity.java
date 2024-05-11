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
@Table(name = "BORROWERS_DETAILS")
@Audited(withModifiedFlag = true)
@AuditTable(schema = "bank_audit", value = "BORROWERS_DETAILS_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class BorrowersDetailsEntity extends AuditEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowers_id")
    private Long borrowersId;

    @Column(name = "borrowers_uuid", unique = true, length = 40)
    private String borrowersUuid;

    @Column(name = "customer_number", length = 10)
    private String customerNumber;

    @Column(name = "customer_name", length = 100)
    private String customerName;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    @Column(length = 100)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "property_evaluation_id", referencedColumnName = "property_evaluation_id", foreignKey = @ForeignKey(name = "fk_properties_evaluation_property_evaluation_id"))
    private PropertyEvaluationEntity propertyEvaluation;

    @Override
    public void setUuid(String uuid) {
        setBorrowersUuid(uuid);
    }
}
