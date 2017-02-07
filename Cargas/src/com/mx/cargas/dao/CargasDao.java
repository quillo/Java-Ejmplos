package com.mx.cargas.dao;
import java.io.IOException;

public interface CargasDao {
	public void insertBatchText(String archivo) throws IOException;
	public void insertBatchExcel(String archivo) throws IOException;
	
	
}
