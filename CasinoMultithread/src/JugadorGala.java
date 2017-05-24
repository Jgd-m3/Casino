import java.util.Random;

public class JugadorGala extends JugadorBase{
	
	int numApostado;
	
	public JugadorGala(Ruleta r, int saldo){
		super(r,saldo);
		numApostado = -2;
	}

	@Override
	public void apuesta() {
		if(saldo >= cantApostar && ruleta.cobraDinero(cantApostar)){
			numApostado = new Random().nextInt(36)+1;
			saldo -=cantApostar;
			System.out.printf("Soy %s y he apostado %d€ al %d %n", Thread.currentThread().getName(), cantApostar, numApostado);
		}
	}

	@Override
	public void cobra() {

		if(numApostado == ruleta.getNumSacado()){
			saldo += ruleta.pagaDinero(cantApostar *36);
			System.out.printf("Soy %s y he ganado %d€%n", Thread.currentThread().getName(), cantApostar*36);
			cantApostar = 10;
		}else{
			cantApostar *=2;
			System.out.printf("Soy %s y he perdido %d€ > %d%n", Thread.currentThread().getName(),cantApostar, saldo);
		}
		numApostado = -2;
		
	}
	
	
	
}
