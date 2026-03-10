/**
 * Esta clase funciona como "plantilla" para no repetir código en los estados.
 * Guardamos lo que todas las celdas tienen en común: un símbolo y si están vivas o no.
 */
public abstract class EstadoBase implements EstadoCelda {
	
	protected char simbolo;
	protected boolean vivo;
	
	// El constructor guarda los datos básicos que le pase la clase hija
	public EstadoBase(char simbolo, boolean vivo) {
		this.simbolo = simbolo;
		this.vivo = vivo;
	}
	
	// Devuelve si la celda cuenta como viva para sus vecinos
	@Override
	public boolean estaViva() {
		return this.vivo;
	}
	
	// Devuelve el carácter para imprimir en pantalla (O X L E)
	@Override
	public char getSimbolo() {
		return this.simbolo;
	}
	
	/** * Este método lo dejo "abstract" porque cada estado tiene sus propias reglas.
	 * La lógica de si nace o muere se escribe en las clases hijas.
	 */
	@Override
	public abstract EstadoCelda calcularSiguiente(int vecinosVivos);
	
	/**
	 * El equals es importante para que la Celda sepa si el estado realmente cambió.
	 * Si el estado siguiente es de la misma clase que el actual, asumimos que es igual.
	 */
	@Override
	public boolean equals(Object obj) {
		// Si es exactamente el mismo objeto en memoria
		if (this == obj) return true;
		
		// Si el otro objeto no existe o no es del mismo tipo de estado
		if (obj == null || getClass() != obj.getClass()) return false;
		
		return true;
	}
}