package Modele.Dao;

import Modele.*;
import org.junit.*;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoTest {

    private static Connection connexion;
    private DaoEntreprise daoEntreprise;
    private DaoLogement daoLogement;
    private DaoFacture daoFacture;
    private DaoAssurance daoAssurance;
    private DaoCompteur daoCompteur;
    private DaoBien daoBien;
    private DaoLocataire daoLocataire;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        CictOracleDataSource co = CictOracleDataSource.getInstance();
        co.creerAcces("ktn5057a", "Azerty66+");
        connexion = co.getConnection();
    }

    @Before
    public void setUp() {
    	daoEntreprise = new DaoEntreprise(connexion);
        daoLogement = new DaoLogement(connexion);
        daoFacture = new DaoFacture(connexion);
        daoAssurance = new DaoAssurance(connexion);
        daoCompteur = new DaoCompteur(connexion);
        daoBien = new DaoBien(connexion);
        daoLocataire = new DaoLocataire(connexion);
    }

    @Test
    public void testInsertAndFindAllDaos() throws SQLException {
       
    	// Étape 1 : Créer un Bien obligatoire pour les relations 1,1 
        Bien bien = new Bien("TestBIEN001", "Adresse Test", "Ville Test", "Appartement", 
        		"75001", "2024-2025");
        daoBien.create(bien);
        
        Bien fetchedBien = daoBien.findById("TestBIEN001");
        Assert.assertNotNull(fetchedBien);
        Assert.assertEquals("Adresse Test", fetchedBien.getAdresse());

        // Étape 2 : Insérer un Logement avec relation 1,1 avec Bien
        Logement logement = new Logement("TestLOGEMENT001", 85.0, "15/01/2025", "Appartement", 
        		4, 3, 0, bien);
        daoLogement.create(logement);
        
        Logement fetchedLogement = daoLogement.findById("TestLOGEMENT001");
        Assert.assertNotNull(fetchedLogement);
        Assert.assertEquals(85.0, fetchedLogement.getSurfaceHabitable(), 0);

        // Étape 3 : Insérer un Locataire 
        Locataire locataire = new Locataire("TestLOCATAIRE001", "Jean", "Dupont", "0102030405", 
        		"jean.dupont@test.com", "01/01/1990", 0);
        daoLocataire.create(locataire);
        
        Locataire fetchedLocataire = daoLocataire.findById("TestLOCATAIRE001");
        Assert.assertNotNull(fetchedLocataire);
        Assert.assertEquals("Jean", fetchedLocataire.getNom());
        
        
     // Étape 4 : Ajouter une Entreprise 
       Entreprise entreprise = new Entreprise("00001111222233", "TestNom001", "Rue Du Test", "31100", 
       		"ToulouseTest", "test.test@test.test", "0752066530", "IBAN TEST FR 0000 00000 0000");
       daoEntreprise.create(entreprise);
        Entreprise fetchedEntreprise = daoEntreprise.findById("00001111222233");
        assertEquals("00001111222233", fetchedEntreprise.getSiret());
        assertNotNull(fetchedEntreprise);

        
        // Étape 5 : Insérer une Facture reliée au Logement, Bien et l'Entreprise créé
        Facture facture = new Facture("TestFACTURE001", "01/01/2025", "05/01/2025", "Carte bancaire", 
        		"Devis0001Test", "Description facture", 1200.50, 1200.50, 0, 555.0, fetchedLogement, fetchedBien,  fetchedEntreprise);
        daoFacture.create(facture);
        
        Facture fetchedFacture = daoFacture.findById("TestFACTURE001");
        Assert.assertNotNull(fetchedFacture);
        Assert.assertEquals(1200.50, fetchedFacture.getMontant(), 0);


        // Étape 6 : Insérer une Assurance reliée au Logement, Bien et l'Entreprise créé
        assurance assurance = new assurance("TestASSURANCE001", 300, "15/01/2026",
                555.0, fetchedLogement, fetchedEntreprise, fetchedBien);
        daoAssurance.create(assurance);
        
        assurance fetchedAssurance = daoAssurance.findById("TestASSURANCE001");
        Assert.assertNotNull(fetchedAssurance);
        Assert.assertEquals(300.0, fetchedAssurance.getMontant(), 0);
        
        // Étape 7 : Insérer un Compteur relié au Logement et Bien crée
        Compteur compteur = new Compteur("TestCOMPTEUR001", "Électricité", fetchedBien, fetchedLogement);
        daoCompteur.create(compteur);
        
        Compteur fetchedCompteur = daoCompteur.findById("TestCOMPTEUR001");
        Assert.assertNotNull(fetchedCompteur);
        Assert.assertEquals("TestCOMPTEUR001", fetchedCompteur.getIdCompteur());
        
        /*
        // Étape 8 : Ajouter une Quotite
        Quotite quotite = new Quotite("TestQUOTITE001");
        daoQuotite.create(quotite);
        assertNotNull(daoQuotite.findById("TestQUOTITE001"));

        // Étape 9 : Ajouter un Quotter
        Quotter quotter = new Quotter(50, logement, quotite);
        daoQuotter.create(quotter);
        assertNotNull(daoQuotter.findById("TestLOGEMENT001", "TestQUOTITE001"));

        // Étape 10 : Ajouter un Releve
        Compteur compteur = new Compteur("TestCOMPTEUR001", "Électricité", bien, logement);
        daoCompteur.create(compteur);
        Releve releve = new Releve("15/01/2025", 123.45, compteur);
        daoReleve.create(releve);
        assertNotNull(daoReleve.findById("15/01/2025", "TestCOMPTEUR001"));

        // Étape 11 : Ajouter un ICC
        ICC icc = new ICC("TestICC001", "Description ICC", "01/01/2025", 10.0, compteur);
        daoICC.create(icc);
        assertNotNull(daoICC.findById("TestICC001"));
        
        // Étape 12 : Créer une location associée
    	Louer louer = new Louer("01/02/2025", "01/02/2026", "01/12/2025", 12, 1, 1500.0, 
            200.0, 1500.0, "BailTest002", "ÉtatLieuTest002", 1700.0, fetchedLocataire, fetchedICC, fetchedBien);
    	daoLouer.create(louer)
    	
    	// Vérification de l'insertion de la location
    	Louer fetchedLouer = daoLouer.findById("TestLOCATAIRE002", "TestBIEN002", "01/02/2025");
    	Assert.assertNotNull(fetchedLouer);
		*/
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        if (connexion != null && !connexion.isClosed()) {
            connexion.close();
        }
    }
}
