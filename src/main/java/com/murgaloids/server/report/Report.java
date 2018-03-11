package com.murgaloids.server.report;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Report is a class representation of an item.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "int(11)")
    private Long id;

    @NonNull
    @Column(name = "reporter_id", columnDefinition = "int(11)")
    private Long reporterId;

    @NonNull
    @Column(name = "reportee_id", columnDefinition = "int(11)")
    private Long reporteeId;

    @NonNull
    @Column(name = "description", columnDefinition = "varchar(254)")
    private String description;
}