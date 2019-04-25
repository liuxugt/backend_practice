package com.xu.struct;

import java.util.List;

public class Report {
    List<Session> report_session;
    double workedHour;
    int totalReport;

    public Report(List<Session> report_session, double workedHour, int totalReport) {
        this.report_session = report_session;
        this.workedHour = workedHour;
        this.totalReport = totalReport;
    }

    public Report() {
    }

    public List<Session> getReport_session() {
        return report_session;
    }

    public double getWorkedHour() {
        return workedHour;
    }

    public int getTotalReport() {
        return totalReport;
    }

    public void setReport_session(List<Session> report_session) {
        this.report_session = report_session;
    }

    public void setWorkedHour(double workedHour) {
        this.workedHour = workedHour;
    }

    public void setTotalReport(int totalReport) {
        this.totalReport = totalReport;
    }
}
