/**
 * Esta clase representa a una célula viva "común".
 * Sigue las reglas clásicas de supervivencia y muerte por soledad o sobrepoblación.
 */
public class EstadoVivo extends EstadoBase {
	
	/**
	 * Al crearla, le avisamos al "padre" (EstadoBase) que su símbolo es una 'O'
	 * y que obviamente cuenta como una célula viva (true).
	 */
	public EstadoVivo() {
		super('O', true);
	}
	
	/**
	 * Acá aplicamos las reglas de supervivencia:
	 * 1. Si tiene menos de 2 vecinos, muere (soledad).
	 * 2. Si tiene más de 3 vecinos, muere (sobrepoblación).
	 * 3. Si tiene 2 o 3, sobrevive.
	 */
	@Override
	public EstadoCelda calcularSiguiente(int vecinosVivos) {
		// Si no tiene la cantidad justa de vecinos, se muere
		if (vecinosVivos < 2 || vecinosVivos > 3) {
			return new EstadoMuerto();
		}
		// Si tiene 2 o 3 vecinos, se mantiene tal cual está
		return this;
	}
}