package com.murgaloids.server.report;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called reportRepository.
 * 
 * The goal of Spring Data repository abstraction is to significantly reduce the
 * amount of boilerplate code required to implement data access layers for various
 * persistence stores.
 */
public interface ReportRepository extends CrudRepository<Report, Long> {
    boolean existsById(Long Id);
    boolean existsByReporteeId(Long Id);
    Report findById(Long id);
    List<Report> findByReporteeId(Long id);
}