/**
 * Esta clase representa a cada posicion del tablero.
 * Lo importante acá es que la celda no decide si vive o muere,
 * eso se lo pide a su "estadoActual".
 */
public class Celda {
	
	// Guardamos el estado que tiene ahora y el que va a tener después
	private EstadoCelda estadoActual;
	private EstadoCelda estadoSiguiente;
	
	// Cuando creamos una celda, le decimos con qué estado arranca
	public Celda(EstadoCelda estadoInicial) {
		this.estadoActual = estadoInicial;
	}
	
	/**
	 * Primero calculamos qué va a pasar en el siguiente turno.
	 * Es clave hacerlo en dos pasos para que el cambio de una celda
	 * no afecte el cálculo de sus vecinas en el mismo turno.
	 */
	public void prepararSiguiente(int vecinosVivos) {
		this.estadoSiguiente = estadoActual.calcularSiguiente(vecinosVivos);
	}
	
	/**
	 * Una vez que todas las celdas calcularon su futuro,
	 * esta función hace el cambio efectivo.
	 */
	public boolean actualizar() {
		// Nos fijamos si el estado cambió para saber si hubo movimiento
		boolean cambio = !estadoActual.equals(estadoSiguiente);
		estadoActual = estadoSiguiente;
		return cambio;
	}
	
	// Le preguntamos al estado si se considera "vivo"
	public boolean estaViva() {
		return estadoActual.estaViva();
	}
	
	// Para dibujarla en la consola, le pedimos al estado su letra (X, L, E, O)
	public char getRepresentacion() {
		return estadoActual.getSimbolo();
	}
}