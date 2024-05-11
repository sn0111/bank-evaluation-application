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
@Table(name = "CUSTOMERS")
@Audited(withModifiedFlag = true)
@AuditTable(schema = "bank_audit", value = "CUSTOMERS_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class CustomersEntity extends AuditEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_uuid", unique = true, length = 40)
    private String customerUuid;


    @Column(name = "customer_number", length = 10, unique = true)
    private String customerNumber;

    @Column(name = "short_name", length = 100)
    private String shortName;

    @Column(name = "is_individual", length = 1)
    private String isIndividual;

    @Column(length = 10)
    private String nationality;

    @Column(name = "nationality_number", length = 5)
    private String nationalityNumber;

    @Column(name = "nationality_description", length = 150)
    private String nationalityDescription;

    @Column(name = "street_address", length = 150)
    private String streetAddress;


    @Column(name = "address_line2", length = 150)
    private String addressLine2;


    @Column(name = "address_line3", length = 150)
    private String address_line3;

    @Column(name = "town_country", length = 30)
    private String townCountry;

    @Column(name = "post_code", length = 8)
    private String postCode;

    @Column(length = 30)
    private String country;

    @Column(name = "country_code", length = 5)
    private String countryCode;

    @Column(name = "country_code_number", length = 5)
    private String countryCodeNumber;

    @Column(name = "dispatch_code", length = 10)
    private String dispatchCode;

    @Column(name = "communication_channel", length = 20)
    private String communicationChannel;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "office_phone_number", length = 20)
    private String officePhoneNumber;

    @Column(name = "fax_number", length = 20)
    private String faxNumber;

    @Column(name = "mobile_operator_iso", length = 5)
    private String mobileOperatorIso;

    @Column(name = "mobile_operator_code", length = 20)
    private String mobileOperatorCode;

    @Column(name = "sms_number", length = 15)
    private String smsNumber;

    @Column(length = 100)
    private String email;

    @Override
    public void setUuid(String uuid) {
        setCustomerUuid(uuid);
    }
}
