/**
 * Esta clase representa una celda que está muerta o vacía.
 * Sigue la regla clásica de "nacimiento".
 */
public class EstadoMuerto extends EstadoBase {
	
	/**
	 * Al padre (EstadoBase) le pasamos un puntito '.' para que el tablero
	 * no se vea tan cargado en la consola, y 'false' porque no está viva.
	 */
	public EstadoMuerto() {
		super('.', false);
	}
	
	/**
	 * Una celda muerta solo "revive" si tiene exactamente 3 vecinos vivos.
	 * Es la única forma en que este estado cambia a algo distinto.
	 */
	@Override
	public EstadoCelda calcularSiguiente(int vecinosVivos) {
		// Si hay 3 vecinos vivos, nace una nueva célula
		if (vecinosVivos == 3) {
			return new EstadoVivo();
		}
		// Si no tiene 3, sigue muerta (se devuelve a sí misma)
		return this;
	}
}