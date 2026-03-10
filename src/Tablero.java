/**
 * La clase Tablero representa la grilla del juego.
 * Es la dueña de la matriz de Celdas y la encargada de coordinar
 * las reglas de vecindad para que el sistema evolucione.
 */
public class Tablero {
	
	private Celda[][] grilla;
	private int filas;
	private int columnas;
	
	/**
	 * Constructor: Inicializa la estructura del tablero.
	 * El tamaño se define al momento de la creación (por el Lector o el azar).
	 */
	public Tablero(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.grilla = new Celda[filas][columnas];
	}
	
	// Método para colocar una celda en una coordenada específica (encapsulamiento)
	public void setCelda(int f, int c, Celda celda) {
		grilla[f][c] = celda;
	}
	
	// Consulta si una celda específica está viva (útil para el generador aleatorio)
	public boolean celdaEstaViva(int f, int c) {
		return grilla[f][c].estaViva();
	}
	
	/**
	 * Realiza la evolución del sistema en dos pasos para evitar errores de cálculo
	 * por actualizaciones en cascada.
	 */
	public boolean avanzarGeneracion() {
		
		boolean huboCambios = false;
		/**
		 * PRIMER PASO
		 * Recorremos todas las celdas y les pedimos que decidan su futuro
		 * basándose en el estado actual de sus vecinos.
		 */
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				int vecinosVivos = contarVecinosVivos(i, j);
				grilla[i][j].prepararSiguiente(vecinosVivos);
			}
		}
		/**
		 * SEGUNDO PASO:
		 * Una vez que todas decidieron, hacemos el cambio efectivo de estado.
		 * Detectamos si al menos una cambió para saber si el sistema sigue activo.
		 */
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				boolean cambio = grilla[i][j].actualizar();
				if (cambio) {
					huboCambios = true;
				}
			}
		}
		return huboCambios;
	}
	/**
	 * Lógica de vecindad
	 * Revisa las 8 posiciones alrededor de (f, c).
	 * Incluye validación de bordes para evitar desbordes de índice.
	 */
	private int contarVecinosVivos(int f, int c) {
		int contador = 0;
		// Bucle anidado que recorre el cuadrado de 3x3 alrededor de la celda
		for (int i = f - 1; i <= f + 1; i++) {
			for (int j = c - 1; j <= c + 1; j++) {
				// Validamos límites del tablero y que no sea la celda central
				if (i >= 0 && i < filas &&
						j >= 0 && j < columnas &&
						!(i == f && j == c)) {
					
					if (grilla[i][j].estaViva()) {
						contador++;
					}
				}
			}
		}
		return contador;
	}
	/**
	 * Salida por consola:
	 * Recorre la matriz e imprime el símbolo de cada celda (. O E L).
	 */
	public void imprimir() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print(grilla[i][j].getRepresentacion() + " ");
			}
			System.out.println(); // Salto de línea por fila
		}
	}
}