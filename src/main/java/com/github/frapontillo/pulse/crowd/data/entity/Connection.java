package com.github.frapontillo.pulse.crowd.data.entity;

import java.util.List;

/**
 * Connection are data retrieved from user smartphone.
 * They can contain the contacts of the smartphone owner, and so on.
 *
 * @author Fabio De Pasquale
 */
public class Connection extends Entity {

    private String displayName;
    private String deviceId;
    private String contactName;
    private Integer contactedTimes;
    private Integer starred;
    private List<String> contactPhoneNumber;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

}
