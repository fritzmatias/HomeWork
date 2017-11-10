package fritzmatias.core.model;

public enum AngularDirection {
	Hour(-1), AntiHour(1);
	

	int modificator;
	private AngularDirection(int modificator) {
		this.modificator = modificator;
	}
	
	public int mathModificator() {
		return modificator;
	}
}
