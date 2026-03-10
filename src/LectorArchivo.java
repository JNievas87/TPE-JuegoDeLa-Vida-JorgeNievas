import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Esta clase se encarga de leer los archivos de texto y transformarlos en un Tablero.
 * Es como un traductor: lee caracteres (O, E, L, .) y crea objetos de tipo Celda.
 */
public class LectorArchivo {
	public static Tablero cargar(String ruta) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		String linea;
		int filas = 0;
		int columnas = 0;
		
		/**
		 * Hacemos una primera pasada por el archivo solo para contar
		 * cuántas filas y columnas tiene. Esto lo necesito para poder
		 * crear el objeto Tablero con el tamaño justo.
		 */
		while ((linea = br.readLine()) != null) {
			filas++;
			columnas = linea.length();
		}
		br.close();
		// Ya sabiendo el tamaño, creamos el tablero vacío
		Tablero tablero = new Tablero(filas, columnas);
		
		/**
		 * Segunda pasada: Ahora sí leemos carácter por carácter y
		 * vamos llenando el tablero con las celdas correspondientes.
		 */
		br = new BufferedReader(new FileReader(ruta));
		int f = 0;
		while ((linea = br.readLine()) != null) {
			for (int c = 0; c < columnas; c++) {
				char simbolo = linea.charAt(c);
				// Según la letra que leamos, creamos un estado distinto
				switch (simbolo) {
					case 'O':
						// Si leo una 'O', le pongo la lógica de probabilidad de enfermar
						tablero.setCelda(f, c, new Celda(new ProbabEnfermar()));
						break;
					
					case 'E':
						tablero.setCelda(f, c, new Celda(new EstadoEnferma()));
						break;
					
					case 'L':
						tablero.setCelda(f, c, new Celda(new EstadoLatente()));
						break;
					
					default:
						// Por defecto, cualquier otra cosa (como un punto) es una celda muerta
						tablero.setCelda(f, c, new Celda(new EstadoMuerto()));
						break;
				}
			}
			f++;
		}
		br.close();
		return tablero;
	}
}