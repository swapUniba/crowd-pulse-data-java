package com.github.frapontillo.pulse.crowd.data.entity;

/**
 * PersonalData are data retrieved from user smartphone.
 * They can be data about GPS position, App information, network statistics, and so on.
 *
 * @author Cosimo Lovascio
 */
public class PersonalData extends Entity {

    private String username;
    private String deviceId;
    private Long timestamp;
    private String source;
    private Double latitude;
    private Double longitude;
    private Double speed;
    private Double accuracy;
    private String packageName;
    private String category;
    private Long foregroundTime;
    private String state;
    private Long rxBytes;
    private Long txBytes;
    private String networkType;
    private Integer inVehicle;
    private Integer onBicycle;
    private Integer onFoot;
    private Integer running;
    private Integer still;
    private Integer walking;
    private Integer tilting;
    private Integer unknown;
    private boolean share;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getForegroundTime() {
        return foregroundTime;
    }

    public void setForegroundTime(long foregroundTime) {
        this.foregroundTime = foregroundTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getRxBytes() {
        return rxBytes;
    }

    public void setRxBytes(long rxBytes) {
        this.rxBytes = rxBytes;
    }

    public long getTxBytes() {
        return txBytes;
    }

    public void setTxBytes(long txBytes) {
        this.txBytes = txBytes;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public void setInVehicle(Integer inVehicle) {
        this.inVehicle = inVehicle;
    }

    public Integer getInVehicle() {
        return inVehicle;
    }

    public Integer getOnBicycle() {
        return onBicycle;
    }

    public void setOnBicycle(Integer onBicycle) {
        this.onBicycle = onBicycle;
    }

    public Integer getOnFoot() {
        return onFoot;
    }

    public void setOnFoot(Integer onFoot) {
        this.onFoot = onFoot;
    }

    public Integer getRunning() {
        return running;
    }

    public void setRunning(Integer running) {
        this.running = running;
    }

    public Integer getStill() {
        return still;
    }

    public void setStill(Integer still) {
        this.still = still;
    }

    public Integer getWalking() {
        return walking;
    }

    public void setWalking(Integer walking) {
        this.walking = walking;
    }

    public Integer getUnknown() {
        return unknown;
    }

    public void setUnknown(Integer unknown) {
        this.unknown = unknown;
    }

    public Integer getTilting() {
        return tilting;
    }

    public void setTilting(Integer tilting) {
        this.tilting = tilting;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }
}
