package com.mx.cargas.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Utilerias {

    public static List<Object[]> readExcelFile(String archivo){
        InputStream excelStream = null;
    	List<Object[]> parametros = new ArrayList<Object[]>();    
    	Object[] obj;

        try {
            excelStream = new FileInputStream(new File(archivo));
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;            
            int rows = hssfSheet.getLastRowNum();
            int cols = 0;
            String cellValue;
            for (int r = 0; r < rows; r++) {
            	row = hssfSheet.getRow(r);
                if (row == null){
                    break;
                }else{
                    System.out.print("Row: " + r + " -> ");
                    obj= new Object[row.getLastCellNum()];
                    for (int c = 0; c < (cols = row.getLastCellNum()); c++) {
                        cellValue = row.getCell(c) == null ? "":
                                (row.getCell(c).getCellType() == Cell.CELL_TYPE_STRING)? row.getCell(c).getStringCellValue():
                                (row.getCell(c).getCellType() == Cell.CELL_TYPE_NUMERIC)? "" + row.getCell(c).getNumericCellValue():
                                (row.getCell(c).getCellType() == Cell.CELL_TYPE_BOOLEAN)? "" + row.getCell(c).getBooleanCellValue():
                                (row.getCell(c).getCellType() == Cell.CELL_TYPE_BLANK)? "BLANK":
                                (row.getCell(c).getCellType() == Cell.CELL_TYPE_FORMULA)? "FORMULA":
                                (row.getCell(c).getCellType() == Cell.CELL_TYPE_ERROR)? "ERROR":"";
                        System.out.print("[Column " + c + ": " + cellValue + "] ");
                        obj[c] = cellValue;
                    }
                    parametros.add(obj);
                    System.out.println();
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
        } catch (IOException ex) {
            System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
        } finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                System.out.println("Error in file processing after close it (Error al procesar el fichero después de cerrarlo): " + ex);
            }
        }    
        return parametros;
   }
	
    public static List<Object[]> readTxtFile(String archivo) throws IOException {
    	List<Object[]> parametros = new ArrayList<Object[]>();    	
	    String cadena;
	    FileReader f = new FileReader(archivo);
	    BufferedReader b = new BufferedReader(f);
	    Object[] obj=null;
	    while((cadena = b.readLine())!=null) {
	    	String[] datos = cadena.split("&");
	    	obj= new Object[datos.length];
	    	for (int i = 0; i  < datos.length; i++) {
	    		 obj[i] = datos[i];
	    	}
	    	parametros.add(obj);
	    }	
	    b.close();
	    return parametros;
    }
    
    public static DataSource getDataSource() {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
			"spring-servlet.xml");
		DataSource ds = (DataSource)ctx.getBean("dataSource");    
		return ds;
    }
}    
	
