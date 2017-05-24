/**
 * 
 * Se desea simular los posibles beneficios de diversas estrategias de juego en un casino. La ruleta francesa es un juego
	en el que hay una ruleta con 37 n�meros (del 0 al 36). Cada 3000 milisegundos el croupier saca un n�mero al azar
	y los diversos hilos apuestan para ver si ganan. Todos los hilos empiezan con 1.000 euros y la banca (que controla la
	ruleta) con 50.000. Cuando los jugadores pierden dinero, la banca incrementa su saldo.
	
	Se puede jugar a un n�mero concreto. Habr� 4 hilos que eligen n�meros al azar del 1 al 36 (no el 0) y restar�n
	10 euros de su saldo para apostar a ese ese n�mero. Si sale su n�mero su saldo se incrementa en 360 euros (36
	veces lo apostado).
	
	Se puede jugar a par/impar. Habr� 4 hilos que eligen al azar si apuestan a que saldr� un n�mero par o un n�mero
	impar. Siempre restan 10 euros para apostar y si ganan incrementan su saldo en 20 euros.
	
	Se puede jugar a la �martingala�. Habr� 4 hilos que eligen n�meros al azar. Elegir�n un n�mero y empezar�n
	restando 10 euros de su saldo para apostar a ese n�mero. Si ganan incrementan su saldo en 360 euros. Si pierden
	jugar�n el doble de su apuesta anterior (es decir, 20, luego 40, luego 80, y as� sucesivamente)
	
	La banca acepta todas las apuestas pero nunca paga m�s dinero del que tiene.
	Si sale el 0, todo el mundo pierde y la banca se queda con todo el dinero.
 * 
 * 
 * 
 * @author M3z
 *
 */
public class Simulacion {

	public static void main(String[] args) {


		Ruleta ruleta = new Ruleta(5000);
		
		Thread[] players = new Thread[9];

		for (int i = 0; i < 3; i++) {
			players[i] = new Thread(new JugadorNum(ruleta, 100));
			players[i].setName("JugadorNUM " + i);
		}
		for (int i = 3; i < 6; i++) {
			players[i] = new Thread(new JugadorPar(ruleta, 50));
			players[i].setName("JugadorPAR " + i);
		}
		for (int i = 6; i < 9; i++) {
			players[i] = new Thread(new JugadorGala(ruleta,500));
			players[i].setName("JugadorGALA " + i);
		}
		
		for(Thread aux : players){
			aux.start();
		}		
		ruleta.setPlayers(players);
		System.out.printf("Arranca la ruleta:%n------------------------------------%n");
		ruleta.jugar();

	}

}
