package com.dumblabs.co.coviddaily;



public class ListElementsDistrict {

    private String list_name_district, list_total, activeD ,recoveredD ,deathD ,deltaTotalD ,deltaActiveD ,deltaRecoveredD ,deltaDeathD ;

    public ListElementsDistrict(String list_name_district, String list_total, String activeD, String recoveredD, String deathD, String deltaTotalD, String deltaActiveD, String deltaRecoveredD, String deltaDeathD) {
        this.list_name_district = list_name_district;
        this.list_total = list_total;
        this.activeD = activeD;
        this.recoveredD = recoveredD;
        this.deathD = deathD;
        this.deltaTotalD = deltaTotalD;
        this.deltaActiveD = deltaActiveD;
        this.deltaRecoveredD = deltaRecoveredD;
        this.deltaDeathD = deltaDeathD;
    }

    public String getList_name_district() {
        return list_name_district;
    }

    public String getList_total() {
        return list_total;
    }

    public String getActiveD() {
        return activeD;
    }

    public String getRecoveredD() {
        return recoveredD;
    }

    public String getDeathD() {
        return deathD;
    }

    public String getDeltaTotalD() {
        return deltaTotalD;
    }

    public String getDeltaActiveD() {
        return deltaActiveD;
    }

    public String getDeltaRecoveredD() {
        return deltaRecoveredD;
    }

    public String getDeltaDeathD() {
        return deltaDeathD;
    }
}
