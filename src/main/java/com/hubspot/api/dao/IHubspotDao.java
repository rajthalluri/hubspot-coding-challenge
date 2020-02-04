package com.hubspot.api.dao;

import com.hubspot.api.model.Invitation;
import com.hubspot.api.model.Partner;

import java.util.List;

public interface IHubspotDao {

    List<Partner> getPartnersAvailability();

    String sendInvitations(List<Invitation> invitationList);
}
