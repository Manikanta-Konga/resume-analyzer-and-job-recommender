package com.resume.Backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdzunaJobDto {

    private String title;
    private CompanyDto company;
    private LocationDto location;
    private String description;
    private String redirect_url;
    private Double salary_min;
    private Double salary_max;

    public AdzunaJobDto() {

    }

    public AdzunaJobDto(String title, CompanyDto company, LocationDto location, String description,
                        String redirect_url, Double salary_min, Double salary_max) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.redirect_url = redirect_url;
        this.salary_min = salary_min;
        this.salary_max = salary_max;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public Double getSalary_min() {
        return salary_min;
    }

    public void setSalary_min(Double salary_min) {
        this.salary_min = salary_min;
    }

    public Double getSalary_max() {
        return salary_max;
    }

    public void setSalary_max(Double salary_max) {
        this.salary_max = salary_max;
    }
}
