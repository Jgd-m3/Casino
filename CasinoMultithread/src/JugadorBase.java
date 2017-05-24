
public abstract class JugadorBase implements Runnable{

	Ruleta ruleta;
	int saldo, cantApostar;

	
	public JugadorBase(Ruleta r, int i){
		ruleta = r;
		saldo = i;
		cantApostar = 10;

	}
	

	public void run(){
		while(cantApostar <= saldo){
			while(!ruleta.aceptaApuestas()){
				try { Thread.sleep(100);
				} catch (InterruptedException e) { e.printStackTrace(); }
			}

				apuesta();

			
			while(!ruleta.pagaApuestas()){
				try { Thread.sleep(100);
				} catch (InterruptedException e) { e.printStackTrace(); }
			}
				cobra();

		}
		System.out.printf("Soy %s y me he arruinado, ADIOS.%n", Thread.currentThread().getName());
		
	}
	public abstract void apuesta();
	public abstract void cobra();
	
}
