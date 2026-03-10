import java.util.Scanner;
import java.io.File;

/**
 * Esta es la clase que da inicio a todo.
 * Se encarga de la interacción con el usuario y de preparar el tablero
 * antes de pasárselo al Simulador.
 */
public class Principal {
	
	// Constante para que la animación no vaya ni muy lento ni muy rápido
	private static final int VELOCIDAD_SIMULACION = 500;
	
	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		Tablero miTablero = null;
		boolean huboError = false; // Bandera para manejar el flujo sin usar 'return'
		
		System.out.println("--- Juego de la Vida (TPE) ---");
		System.out.println("1 - Cargar desde carpeta /ejemplos");
		System.out.println("2 - Generar aleatorio (10x10)");
		
		int opcion = 0;
		try {
			System.out.print("Elija una opción: ");
			opcion = entrada.nextInt();
			entrada.nextLine(); // Limpiamos el buffer
		} catch (Exception e) {
			System.out.println("Error: Ingrese un número válido.");
			huboError = true;
		}
		
		// --- BLOQUE DE CARGA DE DATOS ---
		if (!huboError) {
			if (opcion == 1) {
				// Caso 1: Cargar archivo
				System.out.println("\nArchivos disponibles en /ejemplos:");
				listarEjemplos();
				
				System.out.print("\nIngrese el nombre del archivo (ej: texto1.txt): ");
				String nombre = entrada.nextLine();
				String rutaFinal = "ejemplos/" + nombre;
				
				try {
					File archivo = new File(rutaFinal);
					if (!archivo.exists()) {
						throw new Exception("El archivo '" + nombre + "' no existe.");
					}
					miTablero = LectorArchivo.cargar(rutaFinal);
					System.out.println("¡Carga exitosa!");
				} catch (Exception e) {
					System.out.println("Error al cargar: " + e.getMessage());
					huboError = true;
				}
				
			} else if (opcion == 2) {
				// Caso 2: Generación aleatoria
				System.out.print("¿Cuántas células vivas querés poner?: ");
				try {
					int cantidadVivas = entrada.nextInt();
					miTablero = generarConCantidad(10, 10, cantidadVivas);
				} catch (Exception e) {
					System.out.println("Error en el número de células.");
					huboError = true;
				}
				
			} else {
				System.out.println("La opción elegida no existe.");
				huboError = true;
			}
		}
		
		// --- BLOQUE DE SIMULACIÓN ---
		// Solo entramos acá si el tablero se creó bien y no hubo problemas antes
		if (!huboError && miTablero != null) {
			System.out.print("¿Cuántas generaciones simular? (0 para infinito): ");
			try {
				int totalGeneraciones = entrada.nextInt();
				
				System.out.println("\nIniciando simulación... Presione Ctrl+C para detener.");
				Simulador simulador = new Simulador();
				
				// Le pasamos el tablero, las vueltas y nuestra velocidad fija
				simulador.ejecutar(miTablero, totalGeneraciones, VELOCIDAD_SIMULACION);
				
			} catch (Exception e) {
				System.out.println("Error al ingresar las generaciones.");
			}
		}
		
		System.out.println("\n--- Fin del programa ---");
		entrada.close();
	}
	
	/**
	 * Revisa la carpeta 'ejemplos' y muestra los archivos .txt que encuentra.
	 */
	public static void listarEjemplos() {
		File carpeta = new File("ejemplos");
		if (carpeta.exists() && carpeta.isDirectory()) {
			String[] archivos = carpeta.list();
			if (archivos != null && archivos.length > 0) {
				for (String nombre : archivos) {
					if (nombre.toLowerCase().endsWith(".txt")) {
						System.out.println(" -> " + nombre);
					}
				}
			} else {
				System.out.println("[Carpeta vacía]");
			}
		} else {
			System.out.println("[No se encontró la carpeta /ejemplos]");
		}
	}
	
	/**
	 * Crea un tablero desde cero y ubica células vivas al azar.
	 */
	public static Tablero generarConCantidad(int filas, int columnas, int cantidadVivas) {
		Tablero tablero = new Tablero(filas, columnas);
		
		// Primero llenamos con celdas muertas para evitar punteros nulos
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				tablero.setCelda(i, j, new Celda(new EstadoMuerto()));
			}
		}
		
		// Validamos que no nos pidan más de lo que entra
		if (cantidadVivas > (filas * columnas)) cantidadVivas = filas * columnas;
		
		int colocadas = 0;
		while (colocadas < cantidadVivas) {
			int f = (int) (Math.random() * filas);
			int c = (int) (Math.random() * columnas);
			
			if (!tablero.celdaEstaViva(f, c)) {
				// Usamos ProbabEnfermar para que siga la lógica del TPE
				tablero.setCelda(f, c, new Celda(new ProbabEnfermar()));
				colocadas++;
			}
		}
		return tablero;
	}
}