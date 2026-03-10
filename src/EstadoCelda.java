/**
 * Esta interfaz es como un "contrato".
 * Cualquier estado nuevo que queramos inventar (como una celda zombi o mutante)
 * tiene que cumplir con estas tres funciones sí o sí.
 */
public interface EstadoCelda {
	
	/**
	 * Esta es la regla de oro: cada estado decide qué le pasa
	 * dependiendo de cuántos vecinos vivos tiene alrededor.
	 */
	EstadoCelda calcularSiguiente(int vecinosVivos);
	
	/** * Sirve para que el tablero pueda contar cuántas celdas
	 * hay vivas alrededor de una posición.
	 */
	boolean estaViva();
	
	/**
	 * Define qué letra vamos a ver en la consola al dibujar el tablero.
	 */
	char getSimbolo();
}
