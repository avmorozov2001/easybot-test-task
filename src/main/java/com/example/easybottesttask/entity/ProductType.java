package com.example.easybottesttask.entity;

public enum ProductType {
    PC("PC"),
    LAPTOP("LAPTOP"),
    MONITOR("MONITOR"),
    HARD_DISK("HARD_DISK");

    ProductType(String productTypeValue) {
    }

    public final static String PC_NAME = "PC";
    public final static String LAPTOP_NAME = "LAPTOP";
    public final static String HARD_DISK_NAME = "HARD_DISK";
    public final static String MONITOR_NAME = "MONITOR";

}
