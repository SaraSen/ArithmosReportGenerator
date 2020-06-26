package com.arithmos.rest.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arithmos.rest.dao.ExcelDAO;
import com.arithmos.rest.model.Report;

@Service
public class ExcelOutputServiceImpl implements ExcelOutputService {

	@Autowired
	ExcelDAO excelDAO;

	Workbook workbook = new XSSFWorkbook();
	Sheet sheet = null;

	private Sheet createSheets(String name) {
		return workbook.createSheet(name);
	}

	public void populateSheets() {

		

		ArrayList<String> sheetnames = new ArrayList<String>();
		sheetnames.add("DEVQA");
		sheetnames.add("Infrastructure");
		sheetnames.add("SVOC");

		for (int i = 0; i < sheetnames.size(); i++) {
			sheet = createSheets(sheetnames.get(i));

		}

		for (int i = 0; i < 3; i++) {
			workbook.setActiveSheet(i);
			System.out.println(workbook.getActiveSheetIndex());
			
			Row row = workbook.getSheetAt(i).createRow(0);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			// Creating header
			Cell cell = row.createCell(0);
			cell.setCellValue("ID");
			cell.setCellStyle(headerCellStyle);

			cell = row.createCell(1);
			cell.setCellValue("Date");
			cell.setCellStyle(headerCellStyle);

			cell = row.createCell(2);
			cell.setCellValue("Assignee");
			cell.setCellStyle(headerCellStyle);
			
			cell = row.createCell(3);
			cell.setCellValue("Team");
			cell.setCellStyle(headerCellStyle);

			cell = row.createCell(4);
			cell.setCellValue("Jira ID");
			cell.setCellStyle(headerCellStyle);

			cell = row.createCell(5);
			cell.setCellValue("Task Description");
			cell.setCellStyle(headerCellStyle);

			cell = row.createCell(6);
			cell.setCellValue("Comments");
			cell.setCellStyle(headerCellStyle);

			cell = row.createCell(7);
			cell.setCellValue("On Call");
			cell.setCellStyle(headerCellStyle);

			cell = row.createCell(8);
			cell.setCellValue("Delivery Date");
			cell.setCellStyle(headerCellStyle);

			cell = row.createCell(9);
			cell.setCellValue("Status");
			cell.setCellStyle(headerCellStyle);

			cell = row.createCell(10);
			cell.setCellValue("Blockers");
			cell.setCellStyle(headerCellStyle);
			
			cell = row.createCell(11);
			cell.setCellValue("Blockers");
			cell.setCellStyle(headerCellStyle);
		}
	}

	@Override
	public ByteArrayInputStream contactListToExcelFile() {

		List<Report> reports = excelDAO.createTestData();
		populateSheets();

		try {

			for (int i = 0; i < reports.size(); i++) {

					if(reports.get(i).getTeam().equalsIgnoreCase("devqa")) {
						Row dataRow = workbook.getSheetAt(0).createRow(i + 1);
						dataRow.createCell(0).setCellValue(i);
						dataRow.createCell(1).setCellValue(reports.get(i).getDate());
						dataRow.createCell(2).setCellValue(reports.get(i).getAssignee());
						dataRow.createCell(3).setCellValue(reports.get(i).getTeam());
						dataRow.createCell(4).setCellValue(reports.get(i).getJiraID());
						dataRow.createCell(5).setCellValue(reports.get(i).getTaskDesc());
						dataRow.createCell(6).setCellValue(reports.get(i).getComment());
						dataRow.createCell(7).setCellValue(reports.get(i).getOnCall());
						dataRow.createCell(8).setCellValue(reports.get(i).getDeliveryDate());
						dataRow.createCell(9).setCellValue(reports.get(i).getStatus());
						dataRow.createCell(10).setCellValue(reports.get(i).getBlockers());

						sheet.autoSizeColumn(0);
						sheet.autoSizeColumn(1);
						sheet.autoSizeColumn(2);
						sheet.autoSizeColumn(3);
						sheet.autoSizeColumn(4);
						sheet.autoSizeColumn(5);
						sheet.autoSizeColumn(6);
						sheet.autoSizeColumn(7);
						sheet.autoSizeColumn(8);
						sheet.autoSizeColumn(9);
						sheet.autoSizeColumn(10);
					}

			}

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
			return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
