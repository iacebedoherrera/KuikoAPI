package com.kuiko.api.model;

public class CommunityDTO {

    private String communityCode;
    private String communityName;
    private int provinceCount;


    public CommunityDTO(String communityCode, String communityName, int provinceCount) {
        this.communityCode = communityCode;
        this.communityName = communityName;
        this.provinceCount = provinceCount;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public int getProvinceCount() {
        return provinceCount;
    }

    public void setProvinceCount(int provinceCount) {
        this.provinceCount = provinceCount;
    }

}
