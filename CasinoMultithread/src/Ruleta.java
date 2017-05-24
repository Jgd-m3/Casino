import java.util.Random;

public class Ruleta {

	int numSacado, saldo, cont;
	boolean bancarrota;
	public static enum Estado {APOSTANDO, TIRANDO, PAGANDO, BANCARROTA};
	private String estado;
	private Estado actual;
	private Thread[] players;
	
	public Ruleta(int saldo){
		this.saldo = saldo;
		numSacado = -1;
		bancarrota = false;
		actual = Estado.BANCARROTA;
		//jugar();
		cont = 1;
	}
	
	
	public void jugar(){

		while(!bancarrota && cont > 0){
			cont = 0;
			//aceptamos apuestas
			actual = Estado.APOSTANDO;
			try {
				System.out.println("------------------------------------");
				System.out.println("ACEPTANDO APUESTAS: hagan juego por favor...........................");
				System.out.println("------------------------------------");
				Thread.sleep(1500);
				//System.out.println("No va mas......");
			} catch (InterruptedException e) { e.printStackTrace(); }
	
			//giramos ruleta
			actual = Estado.TIRANDO;
			numSacado = new Random().nextInt(37);
			System.out.printf("--------------------%nHa salido el %d%n---------------------%n",numSacado);
			try { Thread.sleep(1000);
			} catch (InterruptedException e) { e.printStackTrace(); }
			
			//pagamos
			actual = Estado.PAGANDO;
			//System.out.println("Repartiendo...");
			try { Thread.sleep(1500);
			} catch (InterruptedException e) { e.printStackTrace(); }
			
			
			if(bancarrota) actual = Estado.BANCARROTA;
			for (int i = 0; i < players.length; i++) {
				if(players[i].isAlive()) cont++;
			}

		}
		if(cont > 0) System.out.println("La ruleta se ha arruinado, no va mas señores... ADIOS.");
		else System.out.println("No hay mas jugadores, cerramos CHAO!");
	}
	
	
	public synchronized boolean cobraDinero( int money){
		saldo +=money;	
		return true;
	}
	
	public synchronized int pagaDinero(int money){
			saldo -= money;
			return (bancarrota = saldo <=0)? saldo+money: money;
	}
	
	public boolean bancarrota(){ return bancarrota; }
	
	public int getNumSacado() { return numSacado; }

	public boolean aceptaApuestas(){ return actual==Estado.APOSTANDO; }
	public boolean pagaApuestas(){ return actual == Estado.PAGANDO;}
	
	public void setPlayers(Thread[] p){ players = p; }
	
}
