package tpIntegrador;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Pronostico {

	Partido partido;
	Equipo equipo;
	ResultadoEnum resultado;

 
	public int puntos() {
		// Si el resultado del partido es = al del pronostico q se compara sumo 1 punto
		ResultadoEnum resultadoReal = this.partido.resultado(this.equipo);
		if (this.resultado.equals(resultadoReal)) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public String toString() {
		return "\n *Pronóstico del Partido: " + partido + "\n *Datos Seleccionados del Equipo: \n" + equipo
				+ " -Resultado: " + resultado + "\n";
	}

}
