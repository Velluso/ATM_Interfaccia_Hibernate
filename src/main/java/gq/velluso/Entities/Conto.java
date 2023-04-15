package gq.velluso.Entities;

import javax.persistence.*;

@Entity
@Table(name = "conto")
public class Conto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "saldo")
    private double saldo;

    @Column(name = "pin")
    private int pin;


    public Conto(int id, String nome, String cognome,  double saldo, int pin) {

        this.nome = nome;
        this.cognome = cognome;
        this.id = id;
        this.saldo = saldo;
        this.pin = pin;
    }

    public Conto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }


// CREO LA LOGICA DELLE OPERAZIONI BANCARIE ALL'INTERNO DI QUESTA CLASSE PER POTER RICHIAMARE I METODI ALL'INTERNO DI ContoDAO E LAVORARE COL DATABASE
    public void prelievo(double importo) {
        if (saldo < importo) {
            System.out.println("Saldo insufficiente, impossibile effettuare il prelievo");

        }
        else {
            double cifraPrelevata = saldo -= importo;
            System.out.println("\nPrelievo di" + "" + cifraPrelevata + " " + "effettuato");
        }
    }


    public void deposito(double importo) {
        if (importo > 0) {
            saldo += importo;
            System.out.println("Deposito effettuato");
        } else {
            System.out.println("Inserire quantità di denaro valide, deposito annullato.");
        }

    }

    public void cambioPin(int vecchioPin, int nuovoPin) {


        if (vecchioPin != pin) {
            System.out.println("PIN errato");

        }

        // se il PIN è uguale al precedente l'operazione viene annullata.
        if (nuovoPin == vecchioPin) {
            System.out.println("il nuovo PIN non può essere uguale a quello precedente");
        } else {
            setPin(nuovoPin);
            System.out.println("PIN cambiato con successo");
        }
    }

    public void creaConto( String nome, String cognome, double saldoIniziale, int pin) {

        // se il nome e cognome non sono ne Null ne vuoti allora vengono salvati

        if(nome != null && !nome.isEmpty()) {
            this.nome = nome;
        } else {
            System.out.println("Il nome non può essere vuoto. Riprova.\n");
            return;
        }

        if(cognome != null && !cognome.isEmpty()) {
            this.cognome = cognome;
        } else {
            System.out.println("Il cognome non può essere vuoto. Riprova.\n");
            return;
        }


        if (saldoIniziale <= 0) {
            System.out.println("Saldo Insufficiente, deposita almeno 1 euro");
        } else {
            this.saldo = saldoIniziale;
        }

        this.pin = pin;
        System.out.println("Il conto è stato creato con successo. Nome: " + this.nome + ", Cognome: " + this.cognome + ", Saldo iniziale: " + this.saldo + ", PIN: " + this.pin +"\n");
    }

    public void eliminaConto(Conto conto) {

        // Dichiaro una variabile id che deve corrispondere ad un ID esistente nel database

        int id = conto.getId();
        if (id == conto.getId()) {
            System.out.println("Conto eliminato con successo");
        } else {
            System.out.println("conto non trovato");
        }
    }


}
