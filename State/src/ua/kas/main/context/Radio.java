package ua.kas.main.context;

public class Radio implements Station {

	private Station station;

	public void setStation(Station station) {
		this.station = station;
	}

	public void nextStation() {
		if (station instanceof Station1) {
			setStation(new Station2());
		} else if (station instanceof Station2) {
			setStation(new Station1());
		}
	}

	@Override
	public void play() {
		station.play();
	}
}
