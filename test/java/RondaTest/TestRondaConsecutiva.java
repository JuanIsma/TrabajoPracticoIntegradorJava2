package RondaTest;
 
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tpIntegrador.Equipo;
import tpIntegrador.Partido;
import tpIntegrador.Pronostico;
import tpIntegrador.ResultadoEnum;

 
 

public class TestRondaConsecutiva {
	
	 
	
	
@Test	
public void testPtosRondasConsecutivas() {
	
 
	Path ruta;
	List<String> lineasArchivo = null;
	String lineaVector[] = new String[4];
	String camposV[] = new String[4];
   // Leer resultados
	Collection<Partido> partidos = new ArrayList<Partido>();
	String equipoLocal = "", equipoVisitante = "";
	int ronda = 0, golesLocal = 0, golesVisitante = 0;
	ResultadoEnum resultado = null;
	String  nombre="" ;

	try {
		ruta = Paths.get("C:\\Users\\Win X\\eclipse-workspace\\TpIntegradorJavaEntrega2\\src\\main\\java\\tpIntegrador\\resultados.csv");
		lineasArchivo = Files.readAllLines(ruta, StandardCharsets.UTF_8);
	} catch (IOException e) {
		System.out.println("No se pudo leer la linea de resultados...");
		System.out.println(e.getMessage());
		System.exit(1);
	}
	boolean primera = true;
	for (String linea : lineasArchivo) {
		if (primera) {
			primera = false;
		} else {
			lineaVector = linea.split(";"); // guardo los valores en un vector[]
			// System.out.println(linea); // IMPRIME DE PRUEBA
			ronda = Integer.parseInt(lineaVector[0]);

			equipoLocal = lineaVector[1];
			equipoVisitante = lineaVector[4];
			Equipo equipo1 = new Equipo(equipoLocal);
			Equipo equipo2 = new Equipo(equipoVisitante);
			Partido partido = new Partido( ronda , equipo1, equipo2);
			golesLocal = Integer.parseInt(lineaVector[2]);
			golesVisitante = Integer.parseInt(lineaVector[3]);
			partido.setGolesEquipo1(golesLocal);
			partido.setGolesEquipo2(golesVisitante);
			partidos.add(partido);
 		 
   	       }
	         }

	/***************************************************************************
	 * ARCHIVO PRONOSTICO *
	 ***************************************************************************/

	int  puntos=0 ; // total puntos pesona
	List<String> lineasPronostico = null;
	try {
		ruta = Paths.get("C:\\Users\\Win X\\eclipse-workspace\\TpIntegradorJavaEntrega2\\src\\main\\java\\tpIntegrador\\pronostico.csv");
		lineasPronostico = Files.readAllLines(ruta, StandardCharsets.UTF_8);
	} catch (IOException e) {
		System.out.println("No se pudo leer la linea de pronosticos...");
		System.out.println(e.getMessage());
		System.exit(1);
	}

	primera = true;
	for (String lineaPronost : lineasPronostico) {
		if (primera) {
			primera = false;
		} else {

			camposV = lineaPronost.split(";"); // Guarda los valore en un Vector[]
    		int participante =Integer.parseInt( camposV[0]);
        	Equipo equipo1 = new Equipo(camposV[2]);
			Equipo equipo2 = new Equipo(camposV[6]);
			Partido partido = null;
			
            for (Partido partidoCol : partidos) {
		 	// Cual es el partido  igual al partido de pronostico que estamos comparando
			 if(partidoCol.getEquipo1().getNombre().equals(equipo1.getNombre())
						&& partidoCol.getEquipo2().getNombre().equals(equipo2.getNombre())) {
					    partido = partidoCol;
				      }
			        }
		 	Equipo equipo = null;

			if ("X".equals(camposV[3])) {
				equipo = equipo1;
				resultado = ResultadoEnum.GANA;
			}
			if ("X".equals(camposV[4])) {
				equipo = equipo1;
				resultado = ResultadoEnum.EMPATA;
			}
			if ("X".equals(camposV[5])) {
				equipo = equipo1;
				resultado = ResultadoEnum.PIERDE;
		    	}
			Pronostico pronostico = new Pronostico(partido, equipo, resultado);
			// sumar los puntos correspondientes a cada participante
		 
			if( participante == 2 && ( partido.getRonda()==1|| partido.getRonda()==2 )) {
				
			  puntos+= pronostico.puntos();
			  nombre = camposV[1];
			 
            	}
              }   
	        }
	
	 
	           Assertions.assertEquals(7, puntos);
	           System.out.println(nombre+":"+" obtuvo "+puntos +" Aciertos");
}

}
//  Implementar un test (al menos uno, pero se recomienda hacer más) que calcule el
//   puntaje de una persona en 2 (dos) rondas consecutivas.




 