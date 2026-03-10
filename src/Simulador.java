/**
 * Clase que controla el ciclo de vida de la simulación.
 * Conecta el paso del tiempo con la actualización del tablero.
 */
public class Simulador {
	
	public void ejecutar(Tablero tablero, int totalGeneraciones, int velocidad) {
		
		int generacion = 0;
		
		// El bucle corre mientras no lleguemos al límite (o infinito si es 0)
		while (totalGeneraciones == 0 || generacion < totalGeneraciones) {
			
			System.out.println("\n--- Generación: " + generacion + " ---");
			
			// 1. Mostramos el tablero
			tablero.imprimir();
			
			/**
			 * 2. Avanzamos a la siguiente generación.
			 * con el método 'avanzarGeneracion' ya hacemos todo el trabajo:
			 * contar vecinos, preparar el siguiente estado y actualizar.
			 */
			boolean huboCambios = tablero.avanzarGeneracion();
			
			// 3. Si no hubo cambios, el sistema se detiene solo
			if (!huboCambios) {
				System.out.println("\nNo hubo cambios. El sistema se estabilizó.");
				break;
			}
			
			generacion++;
			
			// 4. Pausa para que la animación sea visible
			try {
				Thread.sleep(velocidad);
			} catch (InterruptedException e) {
				System.out.println("Error en la pausa de la simulación.");
			}
		}
		
		System.out.println("\nSimulación finalizada.");
	}
}