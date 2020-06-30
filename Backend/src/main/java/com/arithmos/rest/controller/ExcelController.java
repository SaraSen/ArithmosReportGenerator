package com.arithmos.rest.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.arithmos.rest.service.ExcelOutputService;

@RestController
@RequestMapping(path = "/download")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExcelController {

	@Autowired
	private ExcelOutputService excelOutputService;

	@PostMapping(value = "tasks")
	public ResponseEntity<?> excelReport(@RequestBody Map<String,Date> dateRange) throws IOException {
		System.out.println(dateRange);
		ByteArrayInputStream in = excelOutputService.contactListToExcelFile();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=report.xlsx");
//	return new ResponseEntity<>(HttpStatus.OK);
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

}
