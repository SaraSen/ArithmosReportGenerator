package com.arithmos.rest.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
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

	Workbook workbook;

	Sheet sheet = null;
	CellStyle normaldataCellStyle;
	CellStyle asigneedataCellStyle;
	int p, q, r = 0;

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

			Row row = workbook.getSheetAt(i).createRow(0);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerCellStyle.setBorderBottom(BorderStyle.THICK);
			headerCellStyle.setBorderLeft(BorderStyle.MEDIUM);
			headerCellStyle.setBorderRight(BorderStyle.MEDIUM);
			headerCellStyle.setBorderTop(BorderStyle.THICK);

			normaldataCellStyle = workbook.createCellStyle();
			normaldataCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			normaldataCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			normaldataCellStyle.setBorderBottom(BorderStyle.MEDIUM);

			asigneedataCellStyle = workbook.createCellStyle();
			asigneedataCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
			asigneedataCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			asigneedataCellStyle.setBorderBottom(BorderStyle.MEDIUM);
			// Creating header
			Cell cell = row.createCell(0);
			cell.setCellValue("Task ID");
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

		}
	}

	@Override
	public ByteArrayInputStream contactListToExcelFile() {

		List<Report> reports = excelDAO.createTestData();
		workbook = new XSSFWorkbook();
		p = 0;
		q = 0;
		r = 0;
		populateSheets();

		try {

			for (int i = 0; i < reports.size(); i++) {

				if (reports.get(i).getTeam().equalsIgnoreCase("devqa")) {
//					if (reports.get(i).getAssignee().equals(reports.get(1 + i-1).getAssignee())) {
//						System.out.println(reports.get(i).getAssignee() + "==="
//								+ reports.get((reports.size() - (reports.size()-1)) + 1).getAssignee());
//					}
					Row dataRow = workbook.getSheetAt(0).createRow(r + 1);
					addData(reports, dataRow, i, r);
					formatSheet();
					r++;

				

				} else if (reports.get(i).getTeam().equalsIgnoreCase("infrastructure")) {
					Row dataRow = workbook.getSheetAt(1).createRow(p + 1);
					addData(reports, dataRow, i, p);
					formatSheet();
					p++;
				} else {
					Row dataRow = workbook.getSheetAt(2).createRow(q + 1);
					addData(reports, dataRow, i, q);
					formatSheet();
					q++;
				}

			}

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
			return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private void formatSheet() {

		for (int i = 0; i < 11; i++) {
			sheet.autoSizeColumn(i);
		}

	}

	private void addData(List<Report> reports, Row dataRow, int i, int id) {

		Cell taskID = dataRow.createCell(0);
		taskID.setCellStyle(normaldataCellStyle);
		taskID.setCellValue(id);

		Cell date = dataRow.createCell(1);
		date.setCellStyle(normaldataCellStyle);
		date.setCellValue(reports.get(i).getDate());

		Cell asignee = dataRow.createCell(2);
		asignee.setCellStyle(asigneedataCellStyle);
		asignee.setCellValue(reports.get(i).getAssignee());

		Cell team = dataRow.createCell(3);
		team.setCellStyle(normaldataCellStyle);
		team.setCellValue(reports.get(i).getTeam());

		Cell jiraID = dataRow.createCell(4);
		jiraID.setCellStyle(normaldataCellStyle);
		jiraID.setCellValue(reports.get(i).getJiraID());

		Cell taskDesc = dataRow.createCell(5);
		taskDesc.setCellStyle(normaldataCellStyle);
		taskDesc.setCellValue(reports.get(i).getTaskDesc());

		Cell comment = dataRow.createCell(6);
		comment.setCellStyle(normaldataCellStyle);
		comment.setCellValue(reports.get(i).getComment());

		Cell onCall = dataRow.createCell(7);
		onCall.setCellStyle(normaldataCellStyle);
		onCall.setCellValue(reports.get(i).getOnCall());

		Cell dilveryData = dataRow.createCell(8);
		dilveryData.setCellStyle(normaldataCellStyle);
		dilveryData.setCellValue(reports.get(i).getDeliveryDate());

		Cell status = dataRow.createCell(9);
		status.setCellStyle(normaldataCellStyle);
		status.setCellValue(reports.get(i).getStatus());

		Cell blockers = dataRow.createCell(10);
		blockers.setCellStyle(normaldataCellStyle);
		blockers.setCellValue(reports.get(i).getBlockers());

	}

}
