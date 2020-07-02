package com.arithmos.rest.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.arithmos.rest.model.Report;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class ReportDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JsonObject pushReport(List<Report> book) {
        JsonObject success = new JsonObject();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        for (Report r : book) {
            jdbcTemplate.update(
                    "INSERT INTO `task`(`date`, `Team`, `Assignee`, `JiraID`, `Description`, `Comments`, `Oncall`, `Delivery_date`, `Status`, `Blockers`) VALUES (?,?,?,?,?,?,?,?,?,?)",
                    dtf.format(localDate), r.getTeam(), r.getAssignee(), r.getJiraID(), r.getTaskDesc(), r.getComment(), r.getOnCall(), r.getDeliveryDate().split("T")[0], r.getStatus(), r.getBlockers());
        }

        success.addProperty("submitted", true);
        return success;
    }

    public List<Report> getReport(Map<String, Date> dateMap) {
        String startDate = formatDate(dateMap.get("startDate"));
        String endDate = formatDate(dateMap.get("endDate"));
        String sql = "SELECT * FROM task WHERE date BETWEEN '" + startDate + "' AND '" + endDate + "'";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Report(
                                rs.getDate("date"),
                                rs.getString("Team"),
                                rs.getString("Assignee"),
                                rs.getString("JiraID"),
                                rs.getString("Description"),
                                rs.getString("Comments"),
                                rs.getString("Oncall"),
                                rs.getString("Delivery_date"),
                                rs.getString("status"),
                                rs.getString("Blockers")
                        )
        );
    }

    public List<Report> getReportByTeam(String team, Date startDate, Date endDate) {
        String startDates = formatDate(startDate);
        String endDates = formatDate(endDate);
        String sql = "SELECT * FROM TASK WHERE team = ? AND date BETWEEN '" + startDates + "' AND '" + endDates + "'";
        return jdbcTemplate.query(
                sql,
                new Object[]{team},
                (rs, rowNum) ->
                        new Report(
                                rs.getDate("date"),
                                rs.getString("Team"),
                                rs.getString("Assignee"),
                                rs.getString("JiraID"),
                                rs.getString("Description"),
                                rs.getString("Comments"),
                                rs.getString("Oncall"),
                                rs.getString("Delivery_date"),
                                rs.getString("status"),
                                rs.getString("Blockers")
                        )
        );
    }

    public String formatDate(Date retdate) {
        try {
            String dateStr = retdate.toString();
            DateFormat formatter = new SimpleDateFormat("E MMM ddd HH:mm:ss Z yyyy");
            Date date = (Date) formatter.parse(dateStr);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String formatedDate = cal.get(Calendar.YEAR) + "-0" + (cal.get(Calendar.MONTH) + 1) + "-0"
                    + cal.get(Calendar.DATE);

            return formatedDate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
