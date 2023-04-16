package tpIntegrador;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Equipo {

	String nombre;

 

	@Override
	public String toString() {
		return " -Equipo: " + nombre + "\n";
	}

}
