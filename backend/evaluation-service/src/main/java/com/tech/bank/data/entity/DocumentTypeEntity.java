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
@Table(name = "DOCUMENT_TYPES")
@Audited(withModifiedFlag = true)
@AuditTable(schema = "bank_audit", value = "DOCUMENT_TYPES_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class DocumentTypeEntity extends AuditEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "document_uuid", unique = true, length = 40)
    private String documentUuid;

    @Column(name = "document_name", length = 50)
    private String documentName;

    @Column(length = 150)
    private String description;

    @Override
    public void setUuid(String uuid) {
        setDocumentUuid(uuid);
    }
}
