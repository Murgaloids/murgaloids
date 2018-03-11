package com.murgaloids.server.report;

import java.util.List;

import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.murgaloids.server.student.StudentRepository;

@RestController
@RequestMapping(path="/reports")
public class ReportController {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add")
    public void addReport(@NonNull @RequestBody Report report) {
        if (!reportRepository.existsById(report.getId()))
            reportRepository.save(report);
    }

    @GetMapping("/get")
    public @ResponseBody Iterable<Report> getReports(@NonNull @RequestParam Long userId) {
        if (studentRepository.existsById(userId)) {
            return reportRepository.findByReporteeId(userId);
        }

        return null;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Report> getAllReports() {
        return reportRepository.findAll();
    }
}