/**
 * Esta clase maneja el estado "Latente"
 * Es como una semilla: está en el tablero pero no cuenta como viva todavía.
 */
public class EstadoLatente extends EstadoBase {
	/**
	 * Al crearla, usamos la letra 'L'.
	 * IMPORTANTE: Le pasamos 'false' al padre porque, aunque esté en el tablero,
	 * no debe sumar como "vecino vivo" para las demás celdas.
	 */
	public EstadoLatente() {
		super('L', false); // Usamos 'L' para diferenciarla visualmente
	}
	
	/**
	 * Regla especial: si tiene exactamente 1 vecino vivo cerca,
	 * se "despierta" y pasa a ser una celda viva común (EstadoVivo).
	 */
	@Override
	public EstadoCelda calcularSiguiente(int vecinosVivos) {
		// Si se cumple la condición de activación
		if (vecinosVivos == 1) {
			return new EstadoVivo();
		}
		// Si tiene 0 o más de 1, se queda esperando (sigue latente)
		return this;
	}
}
