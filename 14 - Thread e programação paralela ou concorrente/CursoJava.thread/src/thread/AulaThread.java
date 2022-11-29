package thread;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.JOptionPane;

public class AulaThread {
	
	public static void main(String[] args) throws InterruptedException {
		
		/*Thread processando em paralelo do envio do email*/
		Thread threadEmail = new Thread(thread1);
		threadEmail.start();
				
		/*-----------------------DIVISÂO DAS THREAD ------------------------------*/
		
		/*Thread processando em paralelo do envio de nota fiscal*/
		Thread threadNFCE = new Thread(thread2);
		threadNFCE.start();

		
		/*Codigo do sistema do usuario continua o fluxo do trabalho*/
		System.out.println("Chegou ao fim do codigo de teste de thread");
		
		/*Fluxo do sistema , cadastro de venda, emissão de nota fiscal, algo do tipo*/
		JOptionPane.showMessageDialog(null, "Systema continua executando para o usuario");
	}
	
	private static Runnable thread2 = new Runnable() {
		
		@Override
		public void run() {
			
			/*Codigo da rótina que quero executar em paralelo*/
			for(int pos = 0; pos < 10; pos ++) {	
				
				/*Quero executar esse envio com tempo de parada, ou com tempo determinado*/
				System.out.println("Executando alguma rotina, por exemplo envio de nota fiscal");

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}//Da um tempo de 1 segundo
			}
		}
	};	
	
	private static Runnable thread1 = new Runnable() {
		
		@Override
		public void run() {	
			
			/*Codigo da rótina que quero executar em paralelo*/
			for(int pos = 0; pos < 10; pos ++) {	
				
				/*Quero executar esse envio com tempo de parada, ou com tempo determinado*/
				System.out.println("Executando alguma rotina, por exemplo envio de email");

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}//Da um tempo de 1 segundo
			}
			/*Fim do código em paralelo*/						
			
		}
	};
}
