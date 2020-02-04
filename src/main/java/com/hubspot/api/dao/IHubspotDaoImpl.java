package com.hubspot.api.dao;

import com.hubspot.api.model.Invitation;
import com.hubspot.api.model.Partner;
import com.hubspot.api.model.PartnerWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hubspot.api.utils.HubspotConstants.COUNTRIES;
import static com.hubspot.api.utils.HubspotConstants.FAILURE;

@Repository
public class IHubspotDaoImpl implements IHubspotDao {

    @Value("${hubspot.api.get.partners.url}")
    private String getPartnersUrl;

    @Value("${hubspot.api.post.invitation.url}")
    private String postInvitationUrl;

    @Override
    public List<Partner> getPartnersAvailability() {
        RestTemplate restTemplate = new RestTemplate();
        PartnerWrapper result = restTemplate.getForObject(getPartnersUrl, PartnerWrapper.class);
        return result.getPartners();
    }

    @Override
    public String sendInvitations(List<Invitation> invitationList) {
        String response;
        try {
            Map<String, List<Invitation>> list = new HashMap<>();
            list.put(COUNTRIES, invitationList);

            HttpEntity<Map<String, List<Invitation>>> request = new HttpEntity<>(list);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Invitation> result = restTemplate.postForEntity(postInvitationUrl, request, Invitation.class);
            response = result.getStatusCode().toString();

        } catch (HttpClientErrorException ex) {
            System.out.println("Exception status code: " + ex.getStatusCode());
            System.out.println("Exception response body: " + ex.getResponseBodyAsString());
            System.out.println("Exception during send invitations post request: " + ex.getMessage());
            response = ex.getResponseBodyAsString();
        }
        return response;
    }


}
