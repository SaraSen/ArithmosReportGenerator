package com.arithmos.rest.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.arithmos.rest.service.ExcelOutputService;

@RestController
@RequestMapping(path = "/download")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExcelController {

	@Autowired
	private ExcelOutputService excelOutputService;

	@GetMapping(value = "tasks")
	public ResponseEntity<InputStreamResource> excelReport() throws IOException {
		ByteArrayInputStream in = excelOutputService.contactListToExcelFile();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=report.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

}
