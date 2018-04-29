package servicedapartment.database;

public class DataFactory {
	private static DataFactory factory;
	
	protected DataFactory() { }
	
	public static DataFactory getInstance() {
		if(factory == null) factory = new DataFactory();
		return factory;
	}
	
	public static void setDataFactory(DataFactory dtf) {
		factory = dtf;
	}
	
	
}
