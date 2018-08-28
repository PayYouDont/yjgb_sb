
package com.gospell.chitong.rdcenter.broadcast.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: ExcelUtil 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年8月28日 下午3:01:32 
*  
*/
public class ExcelUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
	
	public static HSSFWorkbook createExcel(List<?> list,Map<String,String> map) {
		List<Field> fields = ReflecUtil.getFields(list);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");
		HSSFRow headerRow = sheet.createRow(0);
		int j = 0;
		for (Field field:fields) {
				String key = field.getName();
				String value = map.get(key);
				if(value==null) {
					continue;
				}
				HSSFCell cell = headerRow.createCell(j);
				j++;
				cell.setCellValue(value);
		}
		for (int i=0;i<list.size();i++) {
			HSSFRow row = sheet.createRow(i+1);
			j = 0;
			for (Field field:fields) {
				String name = field.getName();
				if(map.get(name)==null) {
					continue;
				}
				HSSFCell cell = row.createCell(j);
				j++;
				try {
					Object obj = field.get(list.get(i));
					String value = "";
					if(obj instanceof Date) {
						Date date = (Date)obj;
						value = DateUtils.formatDateTime(date);
					}else if(obj!=null){
						value = obj.toString();
					}
					cell.setCellValue(value);
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				} 
			}
		}
		return workbook;
	}
}
