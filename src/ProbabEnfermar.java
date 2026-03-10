/**
 * Esta clase es una variante de la célula viva.
 * La usamos para representar que, antes de ver si sobrevive por sus vecinos,
 * tiene una probabilidad de enfermarse y cambiar de estado.
 */
public class ProbabEnfermar extends EstadoVivo {
	
	// Definimos el 25% de probabilidad (0.25) como pide la consigna
	private final double probabDeEnfermar = 0.25;
	
	/**
	 * Primero sorteamos si se enferma.
	 * Si no se enferma, entonces se comporta como una célula viva normal.
	 */
	@Override
	public EstadoCelda calcularSiguiente(int vecinosVivos) {
		
		// Math.random() tira un número entre 0.0 y 1.0
		// Si sale menos de 0.25, significa que entró en ese 25% de chance
		if (Math.random() < probabDeEnfermar) {
			// Si tiene mala suerte, pasa a estar enferma directamente
			return new EstadoEnferma();
		}
		
		/** * Si no se enfermó, llamamos al super, y hace lo mismo que haría una celda viva común.
		 * Así nos ahorramos volver a escribir las reglas de vecinos < 2 o > 3.
		 */
		return super.calcularSiguiente(vecinosVivos);
	}
}