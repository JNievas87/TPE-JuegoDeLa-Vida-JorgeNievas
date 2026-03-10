# TPE: Juego de la Vida - Variante con Estados de Enfermedad y Latencia

### Estudiante: JORGE LUIS NIEVAS DNI 32978785
### Fecha de entrega: 10 de Marzo de 2026
### Materia: Programación II - TUDAI - UNICEN

## Descripción del Proyecto
Esta es una implementación en **Java** del autómata celular "Juego de la Vida", extendida con reglas de lógica compleja. El sistema simula la evolución de una población de celdas en una grilla bidimensional, donde cada individuo interactúa con sus 8 vecinos inmediatos (Vecindad de Moore).

## Lógica de Estados Implementada
El proyecto utiliza una estructura basada en interfaces para gestionar los cambios de estado de manera eficiente:

* **Vivo ('O'):** Sigue las reglas clásicas de supervivencia (2 o 3 vecinos) pero con un 25% de probabilidad de enfermar en cada turno.
* **Enfermo ('E'):** Un estado transitorio de debilidad. La celda muere inevitablemente en la siguiente generación.
* **Latente ('L'):** Celdas que no cuentan como "vivas" para sus vecinas, pero pueden "despertar" y pasar al estado Vivo si tienen exactamente 1 vecino vivo.
* **Muerto ('.'):** Celda vacía que puede revivir si tiene exactamente 3 vecinos vivos.

## Detalles de Implementación
* **Encapsulamiento:** El `Tablero` gestiona una matriz de objetos `Celda`, delegando la lógica de cambio de estado a las clases correspondientes.
* **Doble Fase de Actualización:** Para evitar errores de cálculo por cambios en cascada, el simulador primero calcula el siguiente estado de todas las celdas y luego aplica los cambios simultáneamente.
* **Entrada de Datos:** Soporta carga de patrones iniciales mediante archivos de texto (`.txt`) y generación aleatoria.

## Cómo ejecutar el simulador
1.  Clonar el repositorio o descargar el código.
2.  Asegurarse de que los archivos de prueba estén en la carpeta `/ejemplos`.
3.  Ejecutar la clase `Principal.java`.
4.  Seguir las instrucciones en consola para elegir el método de carga y la velocidad de simulación.
