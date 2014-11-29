package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static Harta h = creareHarta();

	public static void main(String[] args) throws Exception {

		// h.afisareMatrice();

		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));

		String welcomeText = "*********************************\n"
				+ "**                             **\n"
				+ "**    Metro Application v1.0   **\n"
				+ "**                             **\n"
				+ "*********************************\n";
		String optiuni = "\nSelectati o optiune:\n"
				+ "\t 1 - Afisare plan metrou\n" + "\t 2 - Cautare drum\n"
				+ "\t 3 - Autentifiere\n" + "\t 4 - Iesire\n"
				+ "In orice moment puteti anula procedura in curs cu tasta \"a\".";
		System.out.println(welcomeText + optiuni);

		String command = b.readLine();
		while (!command.equals("4")) {
			try {
				int cmd = Integer.parseInt(command);

				switch (cmd) {
				case 1:
					h.afisareLinii();
					break;
				case 2:
					cautareDrum();
					break;
				case 3:
					logare();
					break;
				case 4:
					break;
				default:
					throw new Exception();
				}
			} catch (Exception e) {
//				e.printStackTrace();
				System.err
						.println("Optiunea aleasa nu este valabila. Reincercati.");
			} finally {
				System.out.println(optiuni);
				command = b.readLine();
			}
		}

	}

	private static void logare() throws IOException {
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		String username, password;
		System.out.print("Username: ");
		username = b.readLine();
		if (username.equals("a"))
			return;
		System.out.print("Password: ");
		password = b.readLine();

		while (!username.equals("admin") || !password.equals("pass")) {
			System.out.println("Autentificare esuata. Reincercati.");
			System.out.print("Username: ");
			username = b.readLine();
			if (username.equals("a"))
				return;
			System.out.print("Password: ");
			password = b.readLine();
		}
		String optiuni = "Selectati o optiune\n" + "\t 1 - Adaugati o statie\n"
				+ "\t 2 - Adaugati o linie";
		System.out.println(optiuni);
		String command = b.readLine();
		while (!command.equals("a")) {
			try {
				int cmd = Integer.parseInt(command);

				switch (cmd) {
				case 1:
					System.out.print("Linie: ");
					String linie = b.readLine();
					if (linie.equals("a")) return;
					Linie l = h.cautareLinie(linie);
					while (l == null) {
						System.out.println("Linia nu exista. Reincercati.");
						linie = b.readLine();
						l = h.cautareLinie(linie);
					}
					System.out.print("Nume: ");
					String nume = b.readLine();

					System.out.print("Vecin stanga: ");
					String vs = b.readLine();

					System.out.print("Distanta stanga: ");
					boolean ok = true;
					int ds = 0, dd = 0;
					do {
						try {
							ds = Integer.parseInt(b.readLine());
							ok = true;
						} catch (Exception e) {
							System.out.println("Valoarea introdusa este incorecta.");
							ok = false;
						}
					} while (ok == false);
					
					System.out.print("Vecin dreapta: ");
					String vd = b.readLine();

					System.out.print("Distanta dreapta: ");
					do {
						try {
							dd = Integer.parseInt(b.readLine());
							ok = true;

						} catch (Exception e) {
							System.out.println("Valoarea introdusa este incorecta.");
							ok = false;
						}
					} while (ok == false);
					Statie s = new Statie(nume, vs, ds, vd, dd, null);
					l.addStatie(s);
					break;
				case 2:
					System.out.print("Nume: ");
					nume = b.readLine();
					l = new Linie(new ArrayList<Statie>(), nume);
					h.adaugareLinie(l);
					break;
				default:
					throw new Exception();
				}
			} catch (Exception e) {
				// e.printStackTrace();
				if (command.equals("a"))
					return;
				System.out
						.println("Optiunea aleasa nu este valabila. Reincercati.");
			} finally {
				System.out.println(optiuni);
				command = b.readLine();
			}
		}
	}

	private static void cautareDrum() throws Exception {
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Intoduceti statia de plecare: ");

		String readline = b.readLine();
		Statie start = h.cautareStatie(readline);
		while (start == null && !readline.equals("a")) {
			System.out.println("Statia introdusa este incorecta. Reincercati");
			readline = b.readLine();
			start = h.cautareStatie(readline);
		}
		if (readline.equals("a"))
			return;
		System.out.print("Intoduceti destinatia: ");
		readline = b.readLine();
		Statie stop = h.cautareStatie(readline);
		while ((stop == null || start.getNume()
				.equalsIgnoreCase(stop.getNume())) && !readline.equals("a")) {
			System.out.println("Statia introdusa este incorecta. Reincercati");
			stop = h.cautareStatie(b.readLine());
		}
		if (readline.equals("a"))
			return;
		h.gasireDrum(start.getNume(), stop.getNume());

	}

	private static Harta creareHarta() {
		// linie 1
		ArrayList<Statie> statiiLinie1 = new ArrayList<Statie>();
		Adresa adr = new Adresa("Strada", "55");

		Statie s = new Statie("Pipera", null, 0, "Aurel Vlaicu", 22, adr);
		statiiLinie1.add(s);

		s = new Statie("Aurel Vlaicu", "Pipera", 22, "Aviatorilor", 47, adr);
		statiiLinie1.add(s);

		s = new Statie("Aviatorilor", "Aurel Vlaicu", 47, "Piata Victoriei",
				34, adr);
		statiiLinie1.add(s);

		s = new Statie("Piata Victoriei", "Aviatorilor", 34, "Piata Romana", 7,
				adr);
		statiiLinie1.add(s);

		s = new Statie("Piata Romana", "Piata Victoriei", 7, "Universitate",
				17, adr);
		statiiLinie1.add(s);

		s = new Statie("Universitate", "Piata Romana", 17, "Piata Unirii", 15,
				adr);
		statiiLinie1.add(s);

		s = new Statie("Piata Unirii", "Universitate", 15, "Tineretului", 20,
				adr);
		statiiLinie1.add(s);

		s = new Statie("Tineretului", "Piata Unirii", 20, null, 0, adr);
		statiiLinie1.add(s);

		// linie 2
		ArrayList<Statie> statiiLinie2 = new ArrayList<Statie>();
		s = new Statie("Politehnica", null, 0, "Eroilor", 9, adr);
		statiiLinie2.add(s);

		s = new Statie("Eroilor", "Politehnica", 9, "Izvor", 11, adr);
		statiiLinie2.add(s);

		s = new Statie("Izvor", "Eroilor", 11, "Piata Unirii", 30, adr);
		statiiLinie2.add(s);

		s = new Statie("Piata Unirii", "Izvor", 30, "Timpuri Noi", 21, adr);
		statiiLinie2.add(s);

		s = new Statie("Timpuri Noi", "Piata Unirii", 21, "Dristor", 5, adr);
		statiiLinie2.add(s);

		s = new Statie("Dristor", "Timpuri Noi", 5, "Nicolae Grigorescu", 25,
				adr);
		statiiLinie2.add(s);

		s = new Statie("Nicolae Grigorescu", "Dristor", 25, "1 dec 1918", 25,
				adr);
		statiiLinie2.add(s);

		s = new Statie("1 dec 1918", "Nicolae Grigorescu", 25, null, 0, adr);
		statiiLinie2.add(s);

		// linie 3
		ArrayList<Statie> statiiLinie3 = new ArrayList<Statie>();
		s = new Statie("Dristor", null, 0, "Obor", 10, adr);
		statiiLinie3.add(s);

		s = new Statie("Obor", "Dristor", 10, "Piata Victoriei", 11, adr);
		statiiLinie3.add(s);

		s = new Statie("Piata Victoriei", "Obor", 11, "Grozavesti", 143, adr);
		statiiLinie3.add(s);

		s = new Statie("Grozavesti", "Piata Victoriei", 143, "Eroilor", 20, adr);
		statiiLinie3.add(s);

		s = new Statie("Eroilor", "Grozavesti", 20, "Izvor", 23, adr);
		statiiLinie3.add(s);

		s = new Statie("Izvor", "Eroilor", 23, "Piata Unirii", 30, adr);
		statiiLinie3.add(s);

		s = new Statie("Piata Unirii", "Izvor", 30, "Timpuri Noi", 25, adr);
		statiiLinie3.add(s);

		s = new Statie("Timpuri Noi", "Piata Unirii", 25, "Dristor", 10, adr);
		statiiLinie3.add(s);

		s = new Statie("Dristor", "Timpuri Noi", 10, "Nicolae Grigorescu", 20,
				adr);
		statiiLinie3.add(s);

		s = new Statie("Nicolae Grigorescu", "Dristor", 20, "Titan", 20, adr);
		statiiLinie3.add(s);

		s = new Statie("Titan", "Nicolae Grigorescu", 20, null, 0, adr);
		statiiLinie3.add(s);

		// creare linii
		Linie linie1 = new Linie(statiiLinie1, "M3");
		Linie linie2 = new Linie(statiiLinie2, "M1");
		Linie linie3 = new Linie(statiiLinie3, "M2");

		// creare harta
		ArrayList<Linie> linii = new ArrayList<Linie>();
		linii.add(linie1);
		linii.add(linie2);
		linii.add(linie3);
		return new Harta(linii);
	}
}
