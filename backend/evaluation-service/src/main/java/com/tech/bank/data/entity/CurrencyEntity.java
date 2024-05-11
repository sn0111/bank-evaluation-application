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
@Table(name = "CURRENCIES")
@Audited(withModifiedFlag = true)
@AuditTable(schema = "bank_audit", value = "CURRENCIES_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class CurrencyEntity extends AuditEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private Long currencyId;

    @Column(name = "currency_uuid", unique = true, length = 40)
    private String currencyUuid;

    @Column(name = "currency_name", length = 5)
    private String currencyName;

    @Column(length = 150)
    private String description;

    @Override
    public void setUuid(String uuid) {
        setCurrencyUuid(uuid);
    }
}
