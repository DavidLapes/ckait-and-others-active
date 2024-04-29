package com.davidlapes.janca.external.ckait.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CKAITEngineerDetail {

    private String personalPhone;
    private String personalEmail;
    private LocalDate dateOfBirth;

    private String companyWww;
    private String companyIco;
    private String companyAddress;
    private String companyName;
    private String companyEmail;
    private String companyPhone;
}
