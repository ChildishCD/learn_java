package com.java.intro.annotation;

@Test1("")
public class Build {
    private String address;
    private int area;

    public Build() {
    }

    public Build(String address, int area) {
        this.address = address;
        this.area = area;
    }

    @Test1("")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}
