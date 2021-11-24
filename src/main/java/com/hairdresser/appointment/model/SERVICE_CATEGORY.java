package com.hairdresser.appointment.model;

public enum SERVICE_CATEGORY {
    DAMEN_HAARSCHNITT("Damen - Haarschnitte & Stylings"),
    DAMEN_COLORATION("Damen - Farbe & Coloration"),
    HERREN_HAARSCHNITT("Herren - Haarschnitte & Stylings"),
    HOCHZEIT("Hochzeit");

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    SERVICE_CATEGORY(String title){
        this.title=title;
    }

}
