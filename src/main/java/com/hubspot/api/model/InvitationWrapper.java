package com.hubspot.api.model;

import java.util.List;

public class InvitationWrapper {

    private List<Invitation> invitations;

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }
}
