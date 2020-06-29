package com.arithmos.rest.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.compress.utils.IOUtils;

import com.arithmos.rest.service.ExcelOutputService;

@RestController
@RequestMapping(path = "/download")
@CrossOrigin(origins = "http://localhost:4200")
public class ExcelController {
	
	@Autowired
	private ExcelOutputService excelOutputService;
	
	@GetMapping("/tasks.xlsx")
    public void downloadCsv(HttpServletResponse response) throws IOException {
	    System.out.println("hello");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=tasks.xlsx");
        ByteArrayInputStream stream = excelOutputService.contactListToExcelFile();
        IOUtils.copy(stream, response.getOutputStream());
    }

}
