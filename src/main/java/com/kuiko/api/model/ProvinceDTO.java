package com.kuiko.api.model;

public class ProvinceDTO {

    private String communityCode;
    private String communityName;
    private int provinceCode;
    private String provinceName;


    public ProvinceDTO(String codigoCa, String nombreCa, int codigoProvincia, String nombreProvincia) {
        this.communityCode = codigoCa;
        this.communityName = nombreCa;
        this.provinceCode = codigoProvincia;
        this.provinceName = nombreProvincia;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String codigoCa) {
        this.communityCode = codigoCa;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String nombreCa) {
        this.communityName = nombreCa;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int codigoProvincia) {
        this.provinceCode = codigoProvincia;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String nombreProvincia) {
        this.provinceName = nombreProvincia;
    }

}
