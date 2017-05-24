import java.util.Random;

public class JugadorPar extends JugadorBase{
	
	private boolean par, apostado;
	
	
	public JugadorPar(Ruleta r, int saldo){
		super(r,saldo);
		apostado = false;
	}

	
	/*
	@Override
	public void run(){
		while(cantApostar <= saldo){
			
			while(ruleta.getEstado() != Ruleta.Estado.APOSTANDO){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				apuesta();
			
			while(ruleta.getEstado() != Ruleta.Estado.PAGANDO){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			cobra();
		}
			System.out.printf("Soy %s y me he arruinado, ADIOS.%n", Thread.currentThread().getName());
	}
*/
	@Override
	public void apuesta() {

		if(saldo >= cantApostar && ruleta.cobraDinero(cantApostar)){
			par = new Random().nextBoolean();
			saldo -=cantApostar;
			System.out.printf("Soy %s y he apostado 10€ a %s%n", Thread.currentThread().getName(), (par)?"Par":"Impar");
		}
		
	}

	@Override
	public void cobra() {
		
		if(0!= ruleta.getNumSacado() && par == (ruleta.getNumSacado()%2 == 0)){
			saldo += ruleta.pagaDinero(cantApostar*2);
			System.out.printf("Soy %s y he ganado 20€ > %d%n", Thread.currentThread().getName(), saldo);
		}else 
			System.out.printf("Soy %s y he perdido 10€ > %d%n", Thread.currentThread().getName(), saldo);
	}
}	
