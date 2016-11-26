package ua.kas.main;

import java.util.ArrayList;
import java.util.LinkedList;

public class Money {

	private int userId;
	private int eventId;
	private int betId;
	private int money;

	ArrayList<Integer> bet = new ArrayList<Integer>();

	LinkedList<ArrayList<Integer>> event = new LinkedList<ArrayList<Integer>>();

	public void addBet() {
		bet.add(eventId);
		bet.add(betId);
		bet.add(userId);
		bet.add(money);

		event.add(bet);
		bet.clear();

	}

	public void checkBet() {
		// connect with server and check bet
		for (int i = 0; i < event.size(); i++) {
			for (int j = 0; j < bet.size(); j++) {
				if (j == 0) {
					// check eventId
				} else if (j == 1) {
					// check betId
				} else if (j == 3) {
					// check userId
				} else {
					// check money
				}
			}
		}
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getBetId() {
		return betId;
	}

	public void setBetId(int betId) {
		this.betId = betId;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
