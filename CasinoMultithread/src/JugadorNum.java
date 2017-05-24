import java.util.Random;

public class JugadorNum extends JugadorBase{
	
	private int numApostado;
	
	public JugadorNum(Ruleta r, int saldo){
		super(r,saldo);
		numApostado = -2;
	}
	
	/*
	@Override
	public void run(){
		while(cantApostar <= saldo){
			
			apuesta();
			
			cobra();
		}
		System.out.printf("Soy %s y me he arruinado, ADIOS.%n", Thread.currentThread().getName());
	}
*/
	@Override
	public void apuesta() {
		
		if(saldo >= cantApostar && ruleta.cobraDinero(cantApostar)){
			numApostado = new Random().nextInt(36)+1;
			saldo -=cantApostar;
			System.out.printf("Soy %s y he apostado 10€ al %d %n", Thread.currentThread().getName(), numApostado);
		}
	}



	@Override
	public void cobra() {
		if(numApostado == ruleta.getNumSacado()){
			saldo += ruleta.pagaDinero(cantApostar*36);
			System.out.printf("Soy %s y he ganado 360€ > %d%n", Thread.currentThread().getName(), saldo);
		}else 
			System.out.printf("Soy %s y he perdido 10€ > %d%n", Thread.currentThread().getName(), saldo);
		numApostado = -2;
	}
}
