package org.curs24.securityApp.DTO;

import lombok.Data;

@Data
public class AddressRequest {

    private String strada;
    private String numar;
    private String localitate;
}