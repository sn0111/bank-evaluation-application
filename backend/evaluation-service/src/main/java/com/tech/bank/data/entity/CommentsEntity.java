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
@Table(name = "COMMENTS")
@Audited(withModifiedFlag = true)
@AuditTable(schema = "bank_audit", value = "COMMENTS_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class CommentsEntity extends AuditEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private Long commentsId;

    @Column(name = "comments_uuid", unique = true, length = 40)
    private String commentsUuid;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "property_evaluation_id", referencedColumnName = "property_evaluation_id", foreignKey = @ForeignKey(name = "fk_properties_evaluation_property_evaluation_id"))
    private PropertyEvaluationEntity propertyEvaluation;

    @Override
    public void setUuid(String uuid) {
        setCommentsUuid(uuid);
    }
}
