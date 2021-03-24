package buniness.app;


public class GalaxyMainProgram {
	public static void main(String[] args) {
		String filePath = null;
		if (args.length != 0)
			filePath = args[0];
		try{
			GalaxyInputProgram.processInputFile(filePath);
			GalaxyOutputProgram.outputMainProcess();
		}
		catch(Exception e){
			System.out.println("not find input file ! "+e);
		}
	}
}
