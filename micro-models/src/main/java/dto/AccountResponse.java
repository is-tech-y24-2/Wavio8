package dto;

import dto.OwnerResponse;
import models.Role;

import java.io.Serializable;

public class AccountResponse implements Serializable {
    private Role role;
    private OwnerResponse ownerResponse;

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    private String colour;

    public AccountResponse() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public OwnerResponse getOwnerResponse() {
        return ownerResponse;
    }

    public void setOwnerResponse(OwnerResponse ownerResponse) {
        this.ownerResponse = ownerResponse;
    }
}
