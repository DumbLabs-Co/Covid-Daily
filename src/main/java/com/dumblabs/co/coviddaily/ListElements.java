package com.dumblabs.co.coviddaily;



public class ListElements {

    private String list_name_view, list_total_view, active,recovered,death,deltaTotal,deltaActive,deltaRecovered,deltaDeath, statecode;

    public ListElements(String list_name_view, String list_total_view, String active, String recovered, String death, String deltaTotal, String deltaActive, String deltaRecovered, String deltaDeath, String statecode) {
        this.list_name_view = list_name_view;
        this.list_total_view = list_total_view;
        this.active = active;
        this.recovered = recovered;
        this.death = death;
        this.deltaTotal = deltaTotal;
        this.deltaActive = deltaActive;
        this.deltaRecovered = deltaRecovered;
        this.deltaDeath = deltaDeath;
        this.statecode= statecode;
    }


    public String getList_name_view() {
        return list_name_view;
    }

    public String getList_total_view() {
        return list_total_view;
    }

    public String getActive() {
        return active;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getDeath() {
        return death;
    }

    public String getDeltaTotal() {
        return deltaTotal;
    }

    public String getDeltaActive() {
        return deltaActive;
    }

    public String getDeltaRecovered() {
        return deltaRecovered;
    }

    public String getDeltaDeath() {
        return deltaDeath;
    }

    public String getStatecode() {
        return statecode;
    }
}
