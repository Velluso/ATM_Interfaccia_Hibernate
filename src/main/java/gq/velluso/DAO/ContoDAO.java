package gq.velluso.DAO;

import gq.velluso.Entities.Conto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ContoDAO {

    // Creo una Session Factory anche qui e la passo al costruttore della Classe per poter lavorare attraverso le Session per le transazioni col database
    private final SessionFactory sessionFactory;

    public ContoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // METODI DAO PER SALVARE I DATI DELLA CLASSE CONTO NEL DATABASE SEGUENDO SIA LA LOGICA DI CONTO CHE DEL FRAMEWORK PER OPERAZIONI CRUD
    public void preleva(int id, double importo) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Conto conto = session.get(Conto.class, id);
            conto.prelievo(importo);
            session.update(conto);
            session.getTransaction().commit();
        }
        catch (Exception eccezione) {
            eccezione.printStackTrace();
        }
    }

    public void deposita(int id, double importo) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Conto conto = session.get(Conto.class, id);
            conto.deposito(importo);
            session.update(conto);
            session.getTransaction().commit();
        }
        catch (Exception eccezione) {
            eccezione.printStackTrace();
        }
    }

    public double getSaldo(int id) {

        try (Session session = sessionFactory.openSession()) {
            Conto conto = session.get(Conto.class, id);
            return conto.getSaldo();
        }

    }

    public Conto selezionaDaId(int id) {

        try (Session session = sessionFactory.openSession()) {
            return session.get(Conto.class, id);
        }

    }

    public void modificaPin(Conto conto) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(conto);
            session.getTransaction().commit();
        }
        catch (Exception eccezione) {
            eccezione.printStackTrace();
        }
    }

    public void salvaConto(Conto conto) {

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(conto);
            session.getTransaction().commit();
        }
        catch (Exception eccezione) {
            eccezione.printStackTrace();
        }
    }

    public void eliminaConto(int id) {

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Conto conto = session.get(Conto.class, id);
            session.delete(conto);
            session.getTransaction().commit();
        }
        catch (Exception eccezione) {
            eccezione.printStackTrace();
        }
    }

}
