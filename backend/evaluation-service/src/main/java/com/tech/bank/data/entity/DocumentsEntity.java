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
@Table(name = "DOCUMENTS")
@Audited(withModifiedFlag = true)
@AuditTable(schema = "bank_audit", value = "DOCUMENTS_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class DocumentsEntity extends AuditEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "documents_id")
    private Long documentsId;

    @Column(name = "documents_uuid", unique = true, length = 40)
    private String documentsUuid;

    @Column(length = 50)
    private String name;

    @Column(length = 10)
    private String size;

    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "document_id", foreignKey = @ForeignKey(name = "fk_document_types_document_id"))
    private DocumentTypeEntity documentType;

    @Lob
    @Column
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "property_evaluation_id", referencedColumnName = "property_evaluation_id", foreignKey = @ForeignKey(name = "fk_properties_evaluation_property_evaluation_id"))
    private PropertyEvaluationEntity propertyEvaluation;

    @Override
    public void setUuid(String uuid) {
        setDocumentsUuid(uuid);
    }
}
