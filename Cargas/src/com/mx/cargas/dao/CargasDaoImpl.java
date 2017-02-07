package com.mx.cargas.dao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mx.cargas.util.Utilerias;

@Repository
public class CargasDaoImpl implements CargasDao {
	
	@Autowired
    private DataSource dataSource;    
    private SimpleJdbcTemplate jdbcTemplate;

	//insert batch example
	public void insertBatchText(String archivo) throws IOException{
	    String sql = "INSERT INTO CARGAS " +
	        "(FECHA,NOMBRE,EDAD,SEXO) VALUES (?, ?, ?, ?)";
	    
	    List<Object[]> parameters = new ArrayList<Object[]>();
	    parameters= Utilerias.readTxtFile(archivo);
	    
	    jdbcTemplate = new SimpleJdbcTemplate(Utilerias.getDataSource());
	    jdbcTemplate.batchUpdate(sql, parameters);
	}
	
	//insert batch example
	public void insertBatchExcel(String archivo) throws IOException{
	    String sql = "INSERT INTO CARGAS " +
	        "(FECHA,NOMBRE,EDAD,SEXO) VALUES (?, ?, ?, ?)";
	    
	    List<Object[]> parameters = new ArrayList<Object[]>();
	    parameters= Utilerias.readExcelFile(archivo);
	    
	    jdbcTemplate = new SimpleJdbcTemplate(Utilerias.getDataSource());
	    jdbcTemplate.batchUpdate(sql, parameters);
	}		
}
