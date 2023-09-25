import java.util.Scanner;

public class Student {
    private String imie;
    private String nazwisko;
    private double[] oceny;
    private char plec;
    private String kierunek;

    public Student(String imie, String nazwisko, char plec, String kierunek) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
        this.kierunek = kierunek;
        this.oceny = new double[0];
        WyswietlInformacje();
    }

    public void WyswietlInformacje() {
        System.out.println("Imię: " + imie);
        System.out.println("Nazwisko: " + nazwisko);
        System.out.println("Płeć: " + plec);
        System.out.println("Kierunek: " + kierunek);

        if (oceny.length > 0) {
            System.out.print("Oceny: ");
            for (double ocena : oceny) {
                System.out.print(ocena + " ");
            }
            System.out.println();
        }
    }
    public void ZmienKierunek(String nowyKierunek) {
        this.kierunek = nowyKierunek;
        WyswietlInformacje();
    }

    public void DodajOcene(double nowaOcena) {
        this.oceny = dodajOcene(this.oceny, nowaOcena);
        WyswietlInformacje();
    }

    public double ObliczSredniaOcen() {
        if (oceny.length == 0) {
            return 0.0;
        }

        double suma = 0.0;
        for (double ocena : oceny) {
            suma += ocena;
        }

        return suma / oceny.length;
    }

    private double[] dodajOcene(double[] tablica, double nowaOcena) {
        double[] nowaTablica = new double[tablica.length + 1];
        for (int i = 0; i < tablica.length; i++) {
            nowaTablica[i] = tablica[i];
        }
        nowaTablica[tablica.length] = nowaOcena;
        return nowaTablica;
    }

    public void ZmienImie(String noweImie) {
        this.imie = noweImie;
    }

    public void ZmienNazwisko(String noweNazwisko) {
        this.nazwisko = noweNazwisko;
    }

    public void UstawPlec(String nowaPlec) {
        if (nowaPlec.equalsIgnoreCase("M") || nowaPlec.equalsIgnoreCase("K")) {
            this.plec = nowaPlec.toUpperCase().charAt(0);
        } else {
            System.out.println("Niepoprawna wartość płci. Użyj 'M' lub 'K'.");
        }
    }

    public boolean SprawdzZaliczenie() {
        double sredniaOcen = ObliczSredniaOcen();
        return sredniaOcen >= 1.75;
    }
    public static void WyswietlSredniaOcenaKierunku(String kierunek, Student[] students) {
        double sumaOcen = 0.0;
        int liczbaStudentowNaKierunku = 0;

        for (Student student : students) {
            if (student.getKierunek().equalsIgnoreCase(kierunek)) {
                sumaOcen += student.ObliczSredniaOcen();
                liczbaStudentowNaKierunku++;
            }
        }

        if (liczbaStudentowNaKierunku > 0) {
            double sredniaOcenNaKierunku = sumaOcen / liczbaStudentowNaKierunku;
            System.out.println("Średnia ocena na kierunku " + kierunek + ": " + sredniaOcenNaKierunku);
        } else {
            System.out.println("Brak studentów na kierunku " + kierunek);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student("Rafał", "Pociecha", 'M', "Informatyk");

        int opcja;

        do {
            System.out.println("Menu:");
            System.out.println("1. Wyświetl informacje o uczniu");
            System.out.println("2. Zmień kierunek ucznia");
            System.out.println("3. Dodaj ocenę ucznia");
            System.out.println("4. Oblicz średnią ocen");
            System.out.println("5. Sprawdź zaliczenie");
            System.out.println("6. Zmień Imię");
            System.out.println("7. Zmień Nazwisko");
            System.out.println("8. Ustaw Płeć ('M' lub 'K')");
            System.out.println("9. Zakończ");

            System.out.print("Wybierz opcję: ");
            opcja = scanner.nextInt();

            switch (opcja) {
                case 1:
                    student.WyswietlInformacje();
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Podaj nowy kierunek: ");
                    String nowyKierunek = scanner.nextLine();
                    student.ZmienKierunek(nowyKierunek);
                    break;
                case 3:
                    System.out.print("Podaj ocenę do dodania: ");
                    double nowaOcena = scanner.nextDouble();
                    student.DodajOcene(nowaOcena);
                    break;
                case 4:
                    double srednia = student.ObliczSredniaOcen();
                    System.out.println("Średnia ocen: " +srednia);
                    break;
                case 5:
                    boolean zaliczenie = student.SprawdzZaliczenie();
                    if (zaliczenie) {
                        System.out.println("Uczeń zaliczył przedmiot");
                    } else {
                        System.out.println("Uczeń nie zaliczył przedmiotu.");
                    }
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.print("Podaj nowe imię: ");
                    String noweImie = scanner.nextLine();
                    student.ZmienImie(noweImie);
                    break;
                case 7:
                    scanner.nextLine();
                    System.out.print("Podaj nowe nazwisko: ");
                    String noweNazwisko = scanner.nextLine();
                    student.ZmienNazwisko(noweNazwisko);
                    break;
                case 8:
                    scanner.nextLine();
                    System.out.print("Podaj nową płeć ('M' lub 'K'): ");
                    String nowaPlec = scanner.nextLine();
                    student.UstawPlec(nowaPlec);
                    break;
                case 9:
                    System.out.println("Zakończono program.");
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja.");
            }

        } while (opcja != 9);

        scanner.close();
    }
}
