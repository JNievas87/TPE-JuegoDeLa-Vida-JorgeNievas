/**
 * Esta clase representa la regla especial de la "Celda Enferma".
 * Según la consigna, estas celdas duran poco: en el próximo turno mueren sí o sí.
 */
public class EstadoEnferma extends EstadoBase {
	
	/**
	 * Al crearla, le pasamos al "padre" (EstadoBase) que su letra es la 'E'
	 * y que, técnicamente, cuenta como una celda viva para sus vecinos.
	 */
	public EstadoEnferma() {
		super('E', true);
	}
	
	/**
	 * Acá aplicamos la regla simplificada: no importa cuántos vecinos tenga,
	 * la celda enferma siempre pasa a estar muerta en la generación que sigue.
	 */
	@Override
	public EstadoCelda calcularSiguiente(int vecinosVivos) {
		// Se "muere" automáticamente sin mirar a los costados
		return new EstadoMuerto();
	}
}