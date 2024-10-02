package com.ohgiraffers.model.dto;

public class MenuDTO {

    private int menuCode;
    private  String menmuName;
    private int menuPricce;
    private int categoryCode;
    private String orderableStatus;

    public MenuDTO(){}

    public MenuDTO(int menuCode, String menmuName, int menuPricce, int categoryCode, String orderableStatus) {
        this.menuCode = menuCode;
        this.menmuName = menmuName;
        this.menuPricce = menuPricce;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenmuName() {
        return menmuName;
    }

    public void setMenmuName(String menmuName) {
        this.menmuName = menmuName;
    }

    public int getMenuPricce() {
        return menuPricce;
    }

    public void setMenuPricce(int menuPricce) {
        this.menuPricce = menuPricce;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuCode=" + menuCode +
                ", menmuName='" + menmuName + '\'' +
                ", menuPricce=" + menuPricce +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
