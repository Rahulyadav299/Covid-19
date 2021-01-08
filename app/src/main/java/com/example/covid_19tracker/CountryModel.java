package com.example.covid_19tracker;

public class CountryModel {
    String cases,todaycases,deaths,todaydeaths,recovered,todayrecovered,active,critical,flag,country;

    public CountryModel(String flagurl, String countryname){

    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodaydeaths() {
        return todaydeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todaydeaths = todaydeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodayrecovered() {
        return todayrecovered;
    }

    public void setTodayrecovered(String todayrecovered) {
        this.todayrecovered = todayrecovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CountryModel(String cases, String todaycases, String deaths, String todaydeaths, String recovered, String todayrecovered, String active, String critical, String flag, String country) {
        this.cases = cases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydeaths = todaydeaths;
        this.recovered = recovered;
        this.todayrecovered = todayrecovered;
        this.active = active;
        this.critical = critical;
        this.flag = flag;
        this.country = country;
    }

    public void get(int position) {
    }
}
