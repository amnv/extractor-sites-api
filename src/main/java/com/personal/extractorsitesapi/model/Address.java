package com.personal.extractorsitesapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class Address {

    @Size(max = 50)
    private String street;

    @Size(max = 10)
    private String number;

    @Size(max = 50)
    private String extras;

    @Size(max = 20)
    private String neighbor;

    @Size(max = 10)
    private String cep;

    @Size(max = 20)
    private String city;

    @Size(max = 10)
    private String state;
}
