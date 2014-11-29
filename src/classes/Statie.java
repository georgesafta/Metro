package classes;
public class Statie {
	private String nume;
	private String numeVecinStanga;
	private int distantaVecinStanga;
	private String numeVecinDreapta;
	private int distantaVecinDreapta;
	private Adresa adresa;

	public Statie(String nume, String numeVecinStanga, int distantaVecinStanga,
			String numeVecinDreapta, int distantaVecinDreapta, Adresa adresa) {
		super();
		this.nume = nume;
		this.numeVecinStanga = numeVecinStanga;
		this.distantaVecinStanga = distantaVecinStanga;
		this.numeVecinDreapta = numeVecinDreapta;
		this.distantaVecinDreapta = distantaVecinDreapta;
		this.adresa = adresa;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getNumeVecinStanga() {
		return numeVecinStanga;
	}

	public int getDistantaVecinStanga() {
		return distantaVecinStanga;
	}

	public String getNumeVecinDreapta() {
		return numeVecinDreapta;
	}

	public int getDistantaVecinDreapta() {
		return distantaVecinDreapta;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setNumeVecinStanga(String numeVecinStanga) {
		this.numeVecinStanga = numeVecinStanga;
	}

	public void setDistantaVecinStanga(int distantaVecinStanga) {
		this.distantaVecinStanga = distantaVecinStanga;
	}

	public void setNumeVecinDreapta(String numeVecinDreapta) {
		this.numeVecinDreapta = numeVecinDreapta;
	}

	public void setDistantaVecinDreapta(int distantaVecinDreapta) {
		this.distantaVecinDreapta = distantaVecinDreapta;
	}

	@Override
	public String toString() {
		return "Statie [nume=" + nume + ", numeVecinStanga=" + numeVecinStanga
				+ ", distantaVecinStanga=" + distantaVecinStanga
				+ ", numeVecinDreapta=" + numeVecinDreapta
				+ ", distantaVecinDreapta=" + distantaVecinDreapta
				+ ", adresa=" + adresa + "]";
	}

	
	
}
