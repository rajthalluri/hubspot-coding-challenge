package com.hubspot.api.service;

import com.hubspot.api.dao.IHubspotDao;
import com.hubspot.api.model.Invitation;
import com.hubspot.api.model.Partner;
import com.hubspot.api.utils.HubspotHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HubspotApiServiceImpl implements IHubspotApiService {

    @Autowired
    private IHubspotDao hubspotDao;

    @Override
    public List<Partner> getPartnersAvailability() {
        List<Partner> partnersList = hubspotDao.getPartnersAvailability();
        return partnersList;
    }

    @Override
    public List<Invitation> getInvitationsList(List<Partner> partnersList) {
        return HubspotHelper.checkAvailableDatesAndGetInvitations(partnersList);
    }

    @Override
    public String sendInvitations(List<Invitation> invitations) {
        return hubspotDao.sendInvitations(invitations);
    }
}
