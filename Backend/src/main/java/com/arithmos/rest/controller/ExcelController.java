package com.arithmos.rest.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.arithmos.rest.service.ExcelOutputService;

@RestController
@RequestMapping(path = "/download")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExcelController {

	@Autowired
	private ExcelOutputService excelOutputService;

	@PostMapping(value = "tasks", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<?> excelReport(@RequestBody Map<String, Date> dateRange) throws IOException {

		ByteArrayInputStream in = excelOutputService.contactListToExcelFile(dateRange);
		File file = new File(System.getProperty("user.home") + "\\Arithmos\\Report.xlsx");
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("attachment", "0");
		headers.add("Content-Disposition", "attachment; filename=report.xlsx");

		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);

//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Disposition", "attachment; filename=report.xlsx");
//
//		return ResponseEntity.ok().headers(headers).body((new File(System.getProperty("user.home")+ "\\Arithmos\\Report.xlsx")));
	}

}
