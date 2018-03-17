package scorePackUDP;

import java.util.ArrayList;

public class MisDatos {
	
	static ArrayList<ScorePlayer> scoreStrAll = new ArrayList<>();
	static ArrayList<ScorePlayer> scoreStrTop = new ArrayList<>();
	private static MisDatos miconfigurador;
	 
	public  static MisDatos addGetMisDatos(ScorePlayer scorePlayer) {
		System.out.println("Iniciando Mis Datos...");
		if (miconfigurador==null) {
			miconfigurador = new MisDatos();
		}
		scoreStrAll.add(scorePlayer);
		System.out.println("Total: "+scoreStrAll.size());
		return miconfigurador;
	 }
	 
	 private MisDatos(){}
	 
	 public static ArrayList<ScorePlayer> getScoreStrAll() {
		 System.out.println("Enviando: "+scoreStrAll.size());
		return scoreStrAll;
	}

	public static MisDatos getMisDatos() {
		System.out.println("Iniciando Mis Datos...");
		if (miconfigurador==null) {
			System.out.println("No esta iniciado :(");
		}
		return miconfigurador;
	}
}