package gq.velluso;

import gq.velluso.DAO.ContoDAO;
import gq.velluso.Entities.Conto;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        // Creo un'interfaccia Session Factory per gestire la connessione con il database e creare nuove Session

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Conto.class)
                .buildSessionFactory();

        // Creo un nuovo oggetto Conto contenente la logica delle operazioni bancarie
        // Creo anche un ContoDAO che serve per gestire i dati delle operazioni di Conto comunicando col database
        Conto conto = new Conto();
        ContoDAO contoDAO = new ContoDAO(sessionFactory);


        // dichiaro le variabili per i cicli do-while e gli switch case
        int scelta;
        int id;
        int idAccesso;
        int pin;
        int sceltaAvvio;
        Scanner opzioneIniziale = new Scanner(System.in);



        // Ciclo do while per Effettuare il Login ad un Conto esistente oppure crearne uno nuovo

        do {
            System.out.println("Interfaccia ATM con Hibernate sviluppata da Marco Velluso");
            System.out.println("Scegli l'azione che desideri effettuare");
            System.out.println("1 - Crea Nuovo Conto");
            System.out.println("2 - Effettua l'accesso ad un conto esistente");
            System.out.println("0 - Esci");

            sceltaAvvio = opzioneIniziale.nextInt();

            switch (sceltaAvvio) {

                case 1:

                    System.out.println("Per Creare il tuo conto inserisci i tuoi dati");
                    System.out.println("Nome:\n");
                    String nome = opzioneIniziale.next();

                    System.out.println("Cognome:\n");
                    String cognome = opzioneIniziale.next();

                    System.out.println("Saldo Iniziale:\n");
                    double saldo = opzioneIniziale.nextDouble();


                    System.out.println("Imposta un PIN:\n");
                    pin = opzioneIniziale.nextInt();

                    // Richiamo la variabile conto contenente il metodo creaConto dell'Oggetto Conto con i parametri da gestire
                    conto.creaConto(nome, cognome, saldo, pin);

                    // Richiamo poi la variabile contoDAO contenente sia la logica per la transazione sia la logica dei metodi di Conto
                    contoDAO.salvaConto(conto);
                    System.out.println("conto creato con ID:" + conto.getId());
                    break;


                case 2:

                    do {
                        System.out.println("Inserisci l'ID del tuo Conto:");
                         idAccesso = opzioneIniziale.nextInt();

                         // Metodo che controlla se l'ID inserito dall'utente esiste nel Database
                        conto = contoDAO.selezionaDaId(idAccesso);

                        if (conto == null) {
                            System.out.println("Conto non trovato. Riprova.");
                        }
                        break;

                    }
                    // Il ciclo si ripete finchè non viene trovato un ID all'interno del database
                    while (conto == null);


                    // se l'ID esiste viene chiesto all'utente di autenticarsi all'account con il PIN corrispettivo
                    do {
                        System.out.println("Inserisci il PIN:");
                        pin = opzioneIniziale.nextInt();

                        if (pin != conto.getPin()) {
                            System.out.println("PIN Errato, riprova");
                        }

                    }
                    // Il ciclo si ripete finchè il PIN inserito corrisponde a quello salvato all'interno del database
                    while (pin != conto.getPin());


                    System.out.println("Login effettuato con Successo. Benvenuto" + " " + conto.getNome() + " " + conto.getCognome());



                    do {
                        System.out.println("Seleziona l'operazione da effettuare");
                        System.out.println("1 - Prelievo");
                        System.out.println("2 - Deposito");
                        System.out.println("3 - Controllo saldo");
                        System.out.println("4 - Cambio PIN");
                        System.out.println("5 - Elimina Conto");

                        System.out.println("0 - Torna al Menu Principale");

                        // inizializzo le variabili per lo switch-case
                        scelta = opzioneIniziale.nextInt();
                        id = conto.getId();


                        // 2° blocco di switch-case che dipende dal primo blocco di switch-case, viene mostrato all'utente solo dopo il Login

                        switch (scelta) {

                            case 1:
                                System.out.println("Inserisci l'importo da prelevare");
                                double importo = opzioneIniziale.nextDouble();
                                System.out.println("Inserisci il PIN del conto per effettuare l'operazione");
                                pin = opzioneIniziale.nextInt();
                                if (pin == conto.getPin()) {
                                    contoDAO.preleva(id, importo);
                                    break;
                                }

                            case 2:

                                System.out.println("Inserisci l'importo da depositare");
                                importo = opzioneIniziale.nextDouble();
                                System.out.println("Inserisci il PIN del conto per effettuare l'operazione");
                                pin = opzioneIniziale.nextInt();
                                if (pin == conto.getPin()) {
                                    contoDAO.deposita(id, importo);
                                    break;
                                }

                            case 3:

                                System.out.println("Inserisci il PIN del conto per effettuare l'operazione");
                                pin = opzioneIniziale.nextInt();
                                if (pin == conto.getPin()) {
                                    System.out.println("Il saldo è: " + contoDAO.getSaldo(id));
                                    break;
                                }

                            case 4:

                                System.out.println("Inserisci il PIN del conto per effettuare l'operazione");
                                pin = opzioneIniziale.nextInt();

                                // richiede il vecchio PIN e il nuovo PIN all'utente
                                System.out.println("Inserisci il vecchio PIN");
                                int vecchioPin = opzioneIniziale.nextInt();

                                // chiamo il ContoDAO per selezionare il conto e verificare se il PIN e l'ID corrispondono
                                conto = contoDAO.selezionaDaId(id);

                                System.out.println("Inserisci il nuovo PIN");
                                int nuovoPin = opzioneIniziale.nextInt();

                                // chiamo il metodo del conto per cambiare il PIN e salva le modifiche nel database attraverso ContoDAO
                                conto.cambioPin(vecchioPin, nuovoPin);
                                contoDAO.modificaPin(conto);
                                System.out.println("PIN cambiato con successo");

                                break;

                            case 5:

                                System.out.println("Inserisci l'ID del conto da eliminare:");
                                id = opzioneIniziale.nextInt();
                                conto.eliminaConto(conto);
                                contoDAO.eliminaConto(id);

                            case 0:

                                break;

                            default:
                                System.out.println("Scelta non valida");
                                break;
                        }
                    } while (scelta != 0);


                case 0:
                    System.out.println("Grazie per aver usato il nostro servizio. Arrivederci!");
                    break;

                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }

        } while (sceltaAvvio != 0);


        sessionFactory.close();
    }
}

