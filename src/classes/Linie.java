package classes;
import java.util.ArrayList;

public class Linie {
	private ArrayList<Statie> statii;
	private String nume;

	public Linie(ArrayList<Statie> statii, String nume) {
		super();
		this.statii = statii;
		this.nume = nume;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public ArrayList<Statie> getStatii() {
		return statii;
	}

	public void addStatie(Statie statie) {
		if (statii == null) statii = new ArrayList<Statie>();
		String numeVecinDreapta = statie.getNumeVecinDreapta();
		String numeVecinStanga = statie.getNumeVecinStanga();
		if (numeVecinDreapta != null) {
			int dist = statie.getDistantaVecinDreapta();
			for (Statie s : statii) {
				if (s.getNume().equalsIgnoreCase(numeVecinDreapta)) {
					s.setNumeVecinStanga(statie.getNume());
					s.setDistantaVecinStanga(dist);
				}
			}
		}
		if (numeVecinStanga != null) {
			int dist = statie.getDistantaVecinStanga();
			for (Statie s : statii) {
				if (s.getNume().equalsIgnoreCase(numeVecinStanga)) {
					s.setNumeVecinDreapta(statie.getNume());
					s.setDistantaVecinDreapta(dist);
				}
			}
		}
		statii.add(statie);
	}

	public int getNumarStatii() {
		return statii.size();
	}

	public Statie cautareStatie(String nume) {
		for (Statie statie : statii) {
			if (statie.getNume().equalsIgnoreCase(nume))
				return statie;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Linie [statii=" + statii + ", nume=" + nume + "]";
	}
	
}
