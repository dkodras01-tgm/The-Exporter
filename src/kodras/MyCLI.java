package kodras;

import org.apache.commons.cli2.*;
import org.apache.commons.cli2.builder.*;
import org.apache.commons.cli2.commandline.Parser;
import org.apache.commons.cli2.util.HelpFormatter;

/**
 * 
 * @author Dominik Kodras
 * @version 
 */
public class MyCLI {
	
	/**
	 * Der URL des Servers.
	 * Kurzform: -h
	 */
	private String host = "";
	
	/**
	 * Der Username.
	 * Kurzform: -u
	 */
	private String user = "";
	
	/**
	 * Das Passwort des Users.
	 * Kurzform: -p
	 */
	private String passwd = "";
	
	/**
	 * Datenbank.
	 * Kurzform: -d
	 */
	private String db = "";
	
	/**
	 * Feld nach dem sortiert werden soll
	 * Kurzform: -s
	 */
	private String sort = "";
	
	/**
	 * Sortierrichtung
	 * Kurzform: -r
	 */
	private String sortierrichtung = "";
	
	/**
	 * Bedinnnung in SQL-Syntax
	 * Kurzform: -w
	 */
	private String bedinnung = "";
	
	/**
	 * Trennzeichen
	 * Kurzform: -t
	 */
	private String trennzeichen = "";
	
	/**
	 * Felder die ausgegeben werden sollen
	 * Kurzform: -f
	 */
	private String felder = "";
	
	/**
	 * Name der Ausgabedatei
	 * Kurzform: -o
	 */
	private String ausgabe = "";
	
	/**
	 * Tabellenname
	 * Kurzform: -T
	 */
	private String tabelle = "";
	
	/**
	 * Ueberprueft die uebergebenen Optionen und Argumente
	 * @param args Optionen und Argumente die uebergeben werden
	 */
	public MyCLI(String[] args) {
		DefaultOptionBuilder obuilder = new DefaultOptionBuilder();
		ArgumentBuilder abuilder = new ArgumentBuilder();
		GroupBuilder gbuilder = new GroupBuilder();
		//HelpFormatter helpf = new HelpFormatter();
		
		Option host = obuilder.withShortName("h").withRequired(false)
				.withArgument(abuilder.withName("Hostname des DBMS. Standard: localhost\tNICHT VERPFLICHTEND")
						.withMinimum(1).withMaximum(1).create()).create();
		
		Option user = obuilder.withShortName("u").withRequired(false)
				.withArgument(abuilder.withName("Benutzername. Standard: Benutzername des im Betriebssystem angemeldeten Benutzers\tNICHT VERPFLICHTEND")
						.withMinimum(1).withMaximum(1).create()).create();
		
		Option passwd = obuilder.withShortName("p").withRequired(false)
				.withArgument(abuilder.withName("Passwort. Standard: keins\tNICHT VERPFLICHTEND")
						.withMinimum(1).withMaximum(1).create()).create();
		
		Option db = obuilder.withShortName("d").withRequired(true)
				.withArgument(abuilder.withName("Name der Datenbank\tVERPFLICHTEND")
						.withMinimum(1).withMaximum(1).create()).create();
		
		Option sort = obuilder.withShortName("s").withRequired(false)
				.withArgument(abuilder.withName("Feld, nach dem sortiert werden soll (nur eines moeglich, Standard: keines)\tNICHT VERPFLICHTEND")
						.withMinimum(1).withMaximum(1).create()).create();
		
		Option sortierrichtung = obuilder.withShortName("r").withRequired(false)
				.withArgument(abuilder.withName("Sortierrichtung (ASC/DESC). Standard: ASC\tNICHT VERPFLICHTEND")
						.withMinimum(1).withMaximum(1).create()).create();
		
		Option bedinnung = obuilder.withShortName("w").withRequired(false)
				.withArgument(abuilder.withName("eine Bedingung in SQL-Syntax, die um Filtern der Tabelle verwendet wird. Standard: keine\tNICHT VERPFLICHTEND")
						.withMinimum(1).withMaximum(1).create()).create();
		
		Option trennzeichen = obuilder.withShortName("t").withRequired(false)
				.withArgument(abuilder.withName("Trennzeichen, dass fuer die Ausgabe verwendet werden soll. Standard: ;\tNICHT VERPFLICHTEND")
						.withMinimum(1).withMaximum(1).create()).create();
		
		Option felder = obuilder.withShortName("f").withRequired(true)
				.withArgument(abuilder.withName("Kommagetrennte Liste (ohne Leerzeichen) der Felder, die im Ergebnis enthalten sein sollen. Auch * moeglich.\tVERPFLICHTEND")
						.withMinimum(1).withMaximum(1).create()).create();
		
		Option ausgabe = obuilder.withShortName("o").withRequired(false)
				.withArgument(abuilder.withName("Name der Ausgabedatei. Standard: keine -> Ausgabe auf der Konsole\tNICHT VERPFLICHTEND")
						.withMinimum(1).withMaximum(1).create()).create();
		
//		Option tabelle = obuilder.withShortName("T").withRequired(true)
//				.withArgument(abuilder.withName("Tabellename\tVERPFLICHTEND")
//						.withMinimum(1).withMaximum(1).create()).create();
		//Option tabelle = OptionBuilder.withArgName("Tabellenname").hasArg().withDescription("Tabellename\tVERPFLICHTEND").isRequired(true).create("T");
		Option tabelle = obuilder.withShortName("T").withRequired(true)
				.withDescription("Tabellename\tVERPFLICHTEND").create();
		
		Group options = gbuilder.withName("options").withOption(host).withOption(user).withOption(passwd).withOption(db)
				.withOption(sort).withOption(sortierrichtung).withOption(bedinnung).withOption(trennzeichen).withOption(felder)
				.withOption(ausgabe).withOption(tabelle).create();
		
		Parser parser = new Parser();
		parser.setGroup(options);
	
		
	//[3]
		HelpFormatter hf = new HelpFormatter();
		hf.setShellCommand("Exporter");
		hf.setGroup(options);
		hf.getFullUsageSettings().add(DisplaySetting.DISPLAY_GROUP_NAME);
		hf.getFullUsageSettings().add(DisplaySetting.DISPLAY_GROUP_ARGUMENT);
		hf.getFullUsageSettings().remove(DisplaySetting.DISPLAY_GROUP_EXPANDED);
		hf.getDisplaySettings().remove(DisplaySetting.DISPLAY_GROUP_ARGUMENT);
		hf.getLineUsageSettings().add(DisplaySetting.DISPLAY_PROPERTY_OPTION);
		hf.getLineUsageSettings().add(DisplaySetting.DISPLAY_PARENT_ARGUMENT);
		hf.getLineUsageSettings().add(DisplaySetting.DISPLAY_ARGUMENT_BRACKETED);
	//[3]
	//Apache2, "Helping", aktualisiert: 2013, online verfügbar: http://commons.apache.org/sandbox/commons-cli2/examples/ant.html, zuletzt besucht am: 22.10.2013
		
		
		/*
		 * Hier werden die Optionen und Argumente aus der args-Variable ausgelesen, und mit entsprechenden
		 * Meldungen und Exceptions verwaltet.
		 */
		try {
			CommandLine cl = parser.parse(args);
			
			if(cl.hasOption(host)) {
				try {
					this.host = (String) cl.getValue(host);
				} catch(Exception e) {
				//wenn etwas beim Catsen schief geht wird die Hilfe/Beschreibung ausgegeben und das Programm beendet
					hf.print();
					System.exit(1);
				}
			}
			
			if(cl.hasOption(user)) {
				try {
					this.user = (String) cl.getValue(user);
				} catch(Exception e) {
				//wenn etwas beim Catsen schief geht wird die Hilfe/Beschreibung ausgegeben und das Programm beendet
					hf.print();
					System.exit(1);
				}
			}
			
			if(cl.hasOption(passwd)) {
				try {
					this.passwd = (String) cl.getValue(passwd);
				} catch(Exception e) {
				//wenn etwas beim Catsen schief geht wird die Hilfe/Beschreibung ausgegeben und das Programm beendet
					hf.print();
					System.exit(1);
				}
			}
			
			if(cl.hasOption(db)) {
				try {
					this.db = (String) cl.getValue(db);
				} catch(Exception e) {
				//wenn etwas beim Catsen schief geht wird die Hilfe/Beschreibung ausgegeben und das Programm beendet
					hf.print();
					System.exit(1);
				}
			}
			
			if(cl.hasOption(sort)) {
				try {
					this.sort = (String) cl.getValue(sort);
				} catch(Exception e) {
				//wenn etwas beim Catsen schief geht wird die Hilfe/Beschreibung ausgegeben und das Programm beendet
					hf.print();
					System.exit(1);
				}
			}
			
			if(cl.hasOption(sortierrichtung)) {
				try {
					this.sortierrichtung = (String) cl.getValue(sortierrichtung);
				} catch(Exception e) {
				//wenn etwas beim Catsen schief geht wird die Hilfe/Beschreibung ausgegeben und das Programm beendet
					hf.print();
					System.exit(1);
				}
			}
			
			if(cl.hasOption(db)) {
				try {
					this.db = (String) cl.getValue(db);
				} catch(Exception e) {
				//wenn etwas beim Catsen schief geht wird die Hilfe/Beschreibung ausgegeben und das Programm beendet
					hf.print();
					System.exit(1);
				}
			}
			
			if(cl.hasOption(bedinnung)) {
				try {
					this.bedinnung = (String) cl.getValue(bedinnung);
				} catch(Exception e) {
				//wenn etwas beim Catsen schief geht wird die Hilfe/Beschreibung ausgegeben und das Programm beendet
					hf.print();
					System.exit(1);
				}
			}
			
			if(cl.hasOption(trennzeichen)) {
				try {
					this.trennzeichen = (String) cl.getValue(trennzeichen);
				} catch(Exception e) {
				//wenn etwas beim Catsen schief geht wird die Hilfe/Beschreibung ausgegeben und das Programm beendet
					hf.print();
					System.exit(1);
				}
			}
			
			if(cl.hasOption(felder)) {
				try {
					this.felder = (String) cl.getValue(felder);
				} catch(Exception e) {
				//wenn etwas beim Catsen schief geht wird die Hilfe/Beschreibung ausgegeben und das Programm beendet
					hf.print();
					System.exit(1);
				}
			}
			
			if(cl.hasOption(ausgabe)) {
				try {
					this.ausgabe = (String) cl.getValue(ausgabe);
				} catch(Exception e) {
				//wenn etwas beim Catsen schief geht wird die Hilfe/Beschreibung ausgegeben und das Programm beendet
					hf.print();
					System.exit(1);
				}
			}
			
			if(cl.hasOption(tabelle)) {
				try {
					this.tabelle = (String) cl.getValue(tabelle);
				} catch(Exception e) {
				//wenn etwas beim Catsen schief geht wird die Hilfe/Beschreibung ausgegeben und das Programm beendet
					hf.print();
					System.exit(1);
				}
			}
			
		} catch(OptionException e) {
		//wenn etwas beim Verarbeiten der Optionen und argmente schief geht wird die Hilfe/Beschreibung augegeben und das Programm beendet
			hf.print();
			System.exit(1);
		}
	}

	
	
	//Methode(n)
	
	/**
	 * Gibt die Hostadresse zurueck
	 * @return die Hostadresse
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Gibt den Benutzernamen zurueck
	 * @return der Benutzername
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Gibt das Passwort zurueck
	 * @return das Passwort
	 */
	public String getPasswd() {
		return passwd;
	}
	
	/**
	 * Gibt den Namen der Datenbank zurueck
	 * @return die Datenank
	 */
	public String getDB() {
		return db;
	}

	/**
	 * Gibt den Namen des Sortierfeldes zurueck
	 * @return das Sortierfeld
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * Gibt die Sortierrichtung (ASC/DESC) zurueck
	 * @return die Sortierrichtung
	 */
	public String getSortierrichtung() {
		return sortierrichtung;
	}

	/**
	 * Gibt die Bedinnung (WHERE) zurueck
	 * @return die Bedinnung
	 */
	public String getBedinnung() {
		return bedinnung;
	}

	/**
	 * Gibt das Trennzeichen fuer die Ausgabe zurueck
	 * @return das Trennzeichen
	 */
	public String getTrennzeichen() {
		return trennzeichen;
	}

	/**
	 * Gibt die Felder zurueck die angezeigt werden sollen (auch *)
	 * @return die Felder
	 */
	public String getFelder() {
		return felder;
	}

	/**
	 * Gibt den Namen des Ausgabefiles zurueck
	 * @return der Name des Ausgabefiles 
	 */
	public String getAusgabe() {
		return ausgabe;
	}

	/**
	 * Gibt den Namen der Tabelle zurueck, die verwendet werden soll
	 * @return der Tabellenname
	 */
	public String getTabelle() {
		return tabelle;
	}
}