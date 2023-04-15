# Interfaccia ATM con Hibernate

Questa è una simulazione di ATM (Bancomat) scritta in Java 18. Il programma è stato sviluppato per essere eseguito sulla console di sistema. Il progetto utilizza anche Hibernate per la gestione della persistenza dei dati in un database MySQL.

## Requisiti di sistema
Per eseguire questo programma, è necessario avere installato Java 18 sul proprio sistema. Inoltre, il progetto utilizza Hibernate, quindi è necessario avere una connessione a un database compatibile con Hibernate (MySQL nel nostro caso).


## Utilizzo

Modificare il file "hibernate.cfg.xml" all'interno della cartella resources come segue:


1) Aprire il file hibernate.cfg.xml con un editor di testo.
2) Modificare i parametri di connessione del database (ad esempio, nome del database, nome utente, password) secondo le impostazioni del tuo ambiente.
3) Salvare le modifiche al file.
4) Copiare le query del file queries.sql all'interno del database.

Per compilare il codice sorgente, esegui il seguente comando nella directory principale del progetto:

``` 
javac Main.java
```
Una volta compilato con successo, è possibile eseguire il programma utilizzando il comando java seguito dal nome della classe contenente il metodo main():

``` 
java Main.java
```
In alternativa, è possibile utilizzare un IDE Java, come IntelliJ IDEA o Eclipse, per compilare ed eseguire il programma.



Il programma emulerà una sessione di ATM e guiderà l'utente attraverso le operazioni comuni come:

- Creazione conto corrente
- Accesso al conto corrente secondo un ID ed un PIN
- Prelievo di denaro 
- Deposito di denaro 
- Visualizzazione del saldo 
- Modifica del PIN.
- Cancellazione conto corrente

## Contribuire
Se vuoi contribuire a questo progetto, sentiti libero di clonare questo repository e apportare modifiche al codice sorgente.

## Licenza
Questo progetto è concesso in licenza ai sensi della Licenza MIT. Consulta il file LICENSE per ulteriori informazioni.
