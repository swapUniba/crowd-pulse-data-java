package com.github.frapontillo.pulse.crowd.data.entity;

import java.util.List;

/**
 * Connection are data retrieved from user smartphone.
 * They can contain the contacts of the smartphone owner, and so on.
 *
 * @author Fabio De Pasquale
 */
public class Connection extends Entity {

    private String source;
    private String username;
    private String deviceId;
    private String contactName;
    private String contactId;
    private Integer contactedTimes;
    private Integer starred;
    private List<String> contactPhoneNumber;
    private String type;
    private boolean share;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Integer getContactedTimes() {
        return contactedTimes;
    }

    public void setContactedTimes(Integer contactedTimes) {
        this.contactedTimes = contactedTimes;
    }

    public Integer getStarred() {
        return starred;
    }

    public void setStarred(Integer starred) {
        this.starred = starred;
    }

    public List<String> getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(List<String> contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
}
