package com.murgaloids.server.report;

import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path="/reports")
public class ReportController {
    @Autowired
    private ReportRepository reportRepository;

    @PostMapping("/add")
    public void addReport(@NonNull @RequestBody Report report) {
        if (!reportRepository.existsById(report.getId()))
            reportRepository.save(report);
    }

    @GetMapping("/get")
    public @ResponseBody Report getReport(@NonNull @RequestParam Long id) {
        return reportRepository.existsById(id) ? reportRepository.findById(id) : null;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Report> getReports(@NonNull @RequestParam Long userId) {
        return reportRepository.findByReporteeId(userId);
    }
}