package classes;
import java.util.ArrayList;

public class Harta {
	private ArrayList<Linie> linii;
	private int[][] harta;
	private ArrayList<String> numeStatii;

	public Harta(ArrayList<Linie> linii) {
		this.linii = linii;
		numeStatii = new ArrayList<String>();
	}

	public ArrayList<Linie> getLinii() {
		return linii;
	}

	private void init() {
		numeStatii.clear();
		for (int i = 0; i < linii.get(0).getNumarStatii(); i++) {
			numeStatii.add(linii.get(0).getStatii().get(i).getNume());
		}
		for (int i = 1; i < linii.size(); i++) {
			for (int j = 0; j < linii.get(i).getNumarStatii(); j++) {
				if (numeStatii.contains(linii.get(i).getStatii().get(j)
						.getNume()) == false)
					numeStatii.add(linii.get(i).getStatii().get(j).getNume());
			}
		}
		harta = new int[numeStatii.size()][numeStatii.size()];
		for (int i = 0; i < numeStatii.size(); i++) {
			for (int j = 0; j < numeStatii.size(); j++) {
				harta[i][j] = 30000;
			}
		}
		for (int i = 0; i < numeStatii.size(); i++) {
			String numeStatie = numeStatii.get(i);
			for (int k = 0; k < linii.size(); k++) {
				Statie statie = linii.get(k).cautareStatie(numeStatie);
				if (statie != null) {
					String numeVecinDreapta = statie.getNumeVecinDreapta();
					if (numeVecinDreapta != null) {
						int j = numeStatii.indexOf(numeVecinDreapta);
						harta[i][j] = statie.getDistantaVecinDreapta();
					}
					String numeVecinStanga = statie.getNumeVecinStanga();
					if (numeVecinStanga != null) {
						int j = numeStatii.indexOf(numeVecinStanga);
						harta[i][j] = statie.getDistantaVecinStanga();
					}
				}
			}
		}
	}

	public void setLinii(ArrayList<Linie> linii) {
		this.linii = linii;
	}

	public void afisareLinii() {
//		init();
		for (Linie l : linii) {
			ArrayList<Statie> st = l.getStatii();
			System.out.print("Linie " + l.getNume() + "  :  ");
			for (Statie s : st) {
				System.out.print(s.getNume() + "   ");
			}
			System.out.println();

		}
	}

	private void scriereDrum(int i, int j, int[][] a) {
		int k = 0, gasit = 0;
		while ((k < numeStatii.size()) && gasit == 0) {
			if (i != k && j != k && a[i][j] == a[i][k] + a[k][j]) {
				scriereDrum(i, k, a);
				scriereDrum(k, j, a);
				gasit = 1;
			}
			k++;
		}
		if (gasit == 0)
			System.out.print(" - " + numeStatii.get(j));
	}

	private int calculeazaDrum(int nodInitial, int nodFinal, int[][] a) {
		if (a[nodInitial][nodFinal] < 30000) {
			System.out.println("Drumul de la " + numeStatii.get(nodInitial)
					+ " la " + numeStatii.get(nodFinal) + " are lungimea "
					+ a[nodInitial][nodFinal]);
			
			//scriere drum
			System.out.print(numeStatii.get(nodInitial));
			scriereDrum(nodInitial, nodFinal, a);
			System.out.println();
			return a[nodInitial][nodFinal];
		} else
			System.out.println("Nu exista drum de la " 
					+ numeStatii.get(nodInitial) + " la "
					+ numeStatii.get(nodFinal));
			return -1;
	}

	private void lungimeDrumuri(int[][] a) {
		int i, j, k;
		for (k = 0; k < numeStatii.size(); k++)
			for (i = 0; i < numeStatii.size(); i++)
				for (j = 0; j < numeStatii.size(); j++)
					if (a[i][j] > a[i][k] + a[k][j])
						a[i][j] = a[i][k] + a[k][j];
	}

	public int gasireDrum(String nodInitial, String nodFinal) {
		init();
		int[][] copieHarta = harta;
		int i = numeStatii.indexOf(nodInitial);
		int f = numeStatii.indexOf(nodFinal);
		lungimeDrumuri(copieHarta);
		int drum = calculeazaDrum(i, f, copieHarta);
		return drum;
	}
	
	public void adaugareLinie(Linie l) {
		if (linii == null)
			linii = new ArrayList<Linie>();
		linii.add(l);
	}

	public Statie cautareStatie(String nume) {
		for (Linie linie : linii) {
			Statie s = linie.cautareStatie(nume);
			if (s != null)
				return s;
		}
		return null;
	}
	public Linie cautareLinie(String nume) {
		for (Linie linie : linii) {
			if (linie.getNume().equalsIgnoreCase(nume))
				return linie;
		}
		return null;
	}
}

