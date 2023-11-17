package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		
		Map <String, Integer> votes = new LinkedHashMap<>();
	
		System.out.print("Enter file full path: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			
			String line = br.readLine();
			while (line != null) {
				
				String[] fields = line.split(",");
				String candidateName = fields[0];
				Integer count = Integer.parseInt(fields[1]);
				
				if (votes.containsKey(candidateName)) {
					int votesSoFar = votes.get(candidateName);
					votes.put(candidateName, count + votesSoFar);
				}
				else {
					votes.put(candidateName, count);
				}
				line = br.readLine();
			}
			for (String key : votes.keySet()) {
				System.out.println(key + ": " + votes.get(key));
			}
			
			
			int maiorValor = Integer.MIN_VALUE;
			String chaveMaiorValor = "";
			
			for (String key : votes.keySet()) {
				int valor = votes.get(key);
				if(valor > maiorValor) {
					maiorValor = valor;
					chaveMaiorValor = key;
				}
			}
			System.out.println("Vencedor: "+ chaveMaiorValor + " com " + maiorValor + " votos");
			
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();

	}

}
