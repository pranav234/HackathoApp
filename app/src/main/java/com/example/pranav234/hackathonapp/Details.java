package com.example.pranav234.hackathonapp;

public class Details {
    private String tender_category;
    private String company_name;
    private String venue;
    private String dead_line;
    private String base_price;
    private String detail;
    private String people_going;

    public Details(String tender_category, String company_name, String venue, String dead_line, String base_price, String detail,String people_going) {
        this.tender_category = tender_category;
        this.company_name = company_name;
        this.venue = venue;
        this.dead_line = dead_line;
        this.base_price = base_price;
        this.detail = detail;
        this.people_going=people_going;
    }

    public String getTender_category() {
        return tender_category;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getVenue() {
        return venue;
    }

    public String getDead_line() {
        return dead_line;
    }

    public String getBase_price() {
        return base_price;
    }

    public String getDetail() {
        return detail;
    }

    public String getPeople_going() {
        return people_going;
    }
}
