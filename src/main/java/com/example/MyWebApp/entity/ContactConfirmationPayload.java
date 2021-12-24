package com.example.MyWebApp.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ContactConfirmationPayload {

    @NotEmpty
    @Size(min = 1)
    private String contact;
    @NotEmpty
    @Size(min=1)
    private String code;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
