package com.murgaloids.server.report;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Report is a class representation of a report.
 */
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @Column(name = "reporter_id", columnDefinition = "int(11)")
    private Long reporterId;

    @Column(name = "reportee_id", columnDefinition = "int(11)")
    private Long reporteeId;

    @Column(name = "description", columnDefinition = "varchar(254)")
    private String description;

    protected Report() {}
    public Report(
        Long id,
        Long reporterId,
        Long reporteeId,
        String description
    ) {
        this.id = id;
        this.reporterId = reporterId;
        this.reporteeId = reporteeId;
        this.description= description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReporterId() {
        return this.reporterId;
    }

    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }

    public Long getReporteeId() {
        return this.reporteeId;
    }

    public void setReporteeId(Long reporteeId) {
        this.reporteeId = reporteeId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}