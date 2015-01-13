package kodras;

public class Start {

	public static void main(String[] args) {
		MyCLI cli = new MyCLI(args);
		Connect con = new Connect(cli.getHost(), cli.getDB(), cli.getUser(), cli.getPasswd());
		
	}
}