package scorePackUDP;

import java.io.Serializable;

public class ScorePlayer implements Serializable {
	//necesita ser serializable por que sino da un error de io
	private static final long serialVersionUID = 1L;
	int score,pilot;
	String name;
	public ScorePlayer(int score, String name, int pilot) {
		this.score=score;
		this.pilot=pilot;
		if (name.length()>5) this.name=(String) name.subSequence(0, 5);
		else this.name=name;
	}
	public String getName() {
		return name;
	}
	public int getPilot() {
		return pilot;
	}
	public int getScore() {
		return score;
	}
	public String getFormatPlayer() {
		String t= String.format("%05d$ %5s",score,name);
		return t;
	}
}
