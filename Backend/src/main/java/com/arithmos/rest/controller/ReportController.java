package com.arithmos.rest.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arithmos.rest.model.Report;
import com.arithmos.rest.service.ReportService;

@RestController
@RequestMapping(path = "/report")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportController {

	@Autowired
	private ReportService reportservice;

	@PostMapping(path = "/pushreport", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> pushReport(@RequestBody List<Report> report) throws Exception {

		return new ResponseEntity<String>(reportservice.pushReport(report).toString(), HttpStatus.OK);
	}

	@PostMapping(path = "/viewReport")
    public ResponseEntity<List<Report>> getReport(@RequestBody Map<String, Date> dateMap){
	    try{

	    	List<Report> reports = reportservice.getReport(dateMap);

	        return new ResponseEntity<>(reports,HttpStatus.OK);
        }catch (Exception e){
	    	e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PostMapping(path = "/viewReportByTeam")
	public ResponseEntity<List<Report>> getReportByTeam(@RequestBody Map<String, String> map){
		try{
			String input1 =map.get("startDate");
			String input2 =map.get("endDate");
			DateFormat inputFormat = new SimpleDateFormat(
					"EEE MMM dd yyyy", Locale.ENGLISH);
			Date date1 = inputFormat.parse(input1);
			Date date2 = inputFormat.parse(input2);

			DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd",
					Locale.ENGLISH);
			outputFormat.setTimeZone(TimeZone.getTimeZone("IST"));

			String output1 = outputFormat.format(date1);
			String output2 = outputFormat.format(date2);
			Date dates1=new SimpleDateFormat("yyyy-MM-dd").parse(output1);
			Date dates2=new SimpleDateFormat("yyyy-MM-dd").parse(output2);


			List<Report> reports = reportservice.getReportByTeam(map.get("team"),dates1,dates2);
			return new ResponseEntity<>(reports,HttpStatus.OK);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
