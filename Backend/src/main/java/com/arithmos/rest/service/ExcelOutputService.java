package com.arithmos.rest.service;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.Map;

public interface ExcelOutputService {
	public ByteArrayInputStream contactListToExcelFile(Map<String, Date> dateRange);

}
