package com.mx.cargas.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.mx.cargas.dao.CargasDaoImpl;
import com.mx.cargas.util.Utilerias;

@Controller
public class ControladorCargas {

	@RequestMapping("Carga")
    public ModelAndView Carga(HttpServletRequest request) {		
		ModelAndView vista = new ModelAndView();
		String archivo = request.getParameter("file") == null ? ""  : request.getParameter("file");
		CargasDaoImpl cargasDao = new CargasDaoImpl();
				
		if(archivo.endsWith("xls")) {
			Utilerias.readExcelFile(archivo);
			try {
				cargasDao.insertBatchExcel(archivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		
		if(archivo.endsWith("txt")) {
			try {
				cargasDao.insertBatchText(archivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		
		vista.setViewName("Carga");
		return vista;
	}
	
	
}