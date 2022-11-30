package thread;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImplementacaoFilaThread extends Thread  {
	
	private static ConcurrentLinkedQueue<ObjetoFilaThread> pilha_fila =
			new ConcurrentLinkedQueue<ObjetoFilaThread>();
	
	public static void add(ObjetoFilaThread objetoFilaThread) {
		pilha_fila.add(objetoFilaThread);
	}
			@Override
			public void run() {
				
			System.out.println("Fila Rodando");
			
			
			while(true) {
				
				Iterator iteracao = pilha_fila.iterator();
				
				synchronized (iteracao) {/*Bloquear o acesso a essa lista por outros processos*/
				
					while(iteracao.hasNext()) { /*Enquanto conter dados na lista irá processar*/
						
						ObjetoFilaThread processar  = (ObjetoFilaThread) iteracao.next();/*Pega o objeto atual*/
						
						/*Processar 10 mil notas fiscais*/
						/*Gerar uma lista enorme de PDF*/
						/*Gerar um envio em massa de e-mail*/
						System.out.println(processar.getNome());
						System.out.println(processar.getEmail());
						System.out.println("-------------------");
						iteracao.remove();
						
						try {
							Thread.sleep(1000);/*Dar um tempo para descarga de memória*/
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}
				}
				try {
					Thread.sleep(1000);/*Processou toda lista da um tempo para descarga de memória*/
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
	}

}
