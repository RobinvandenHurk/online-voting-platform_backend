package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Party;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.HttpResponseData;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author:    Robin van den Hurk
 * Date:      23/03/2021
 * File name: GetSingleElectionResponseData
 */

public class GetSingleElectionResponseData extends HttpResponseData {

    private long id;
    private String name;
    private List<Party> parties;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date startDateTime;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date endDateTime;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date createdAt;
    private long createdBy;

    public GetSingleElectionResponseData(long id, String name, List<Party> parties, Date startDateTime, Date endDateTime, Date createdAt, long createdBy) {
        this.id = id;
        this.name = name;
        this.parties = parties;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Party> getParties() {
        return parties;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public long getCreatedBy() {
        return createdBy;
    }
}
