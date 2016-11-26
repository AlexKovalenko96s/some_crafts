package ua.kas.main;

import java.util.ArrayList;

public class ConfBet {

	private String match;
	private String s;

	private int idUser;
	private int idEvent;

	private ArrayList<Integer> selected;

	public ConfBet() {
		System.out.println(match);
	}

	public void set() {
		s = "";
		for (int i = 0; i < selected.size(); i++) {
			if (selected.indexOf(i) == 1) {
				s = " winH ";
			}
			if (selected.indexOf(i) == 2) {
				s += " winA ";
			}
			if (selected.indexOf(i) == 3) {
				s = " draw ";
			}
		}
	}

	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public ArrayList<Integer> getSelected() {
		return selected;
	}

	public void setSelected(ArrayList<Integer> selected) {
		this.selected = selected;
	}

}
