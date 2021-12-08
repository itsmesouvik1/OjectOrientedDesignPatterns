import java.io.Serializable;

public final class Singleton implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	// A private static variable to store the instance
	private static Singleton INSTANCE;

	// private constructor so that it is not accessed outside.
	private Singleton() {
	}

	// A static global method which is responsible of giving the instance to the
	// other classes
	// The method is synchronized as to make it thread safe but this is a costly
	// affair as synchronization is applied at the class level
//	public static synchronized Singleton getInstance() {
//		// lazy loading the instance
//		if(INSTANCE == null) INSTANCE = new Singleton();
//		return INSTANCE;
//	}

	public static Singleton getInstance() {
		// lazy loading the instance
		// applying the double locking mechanism for performance improvement
		if (INSTANCE == null) {
			synchronized (Singleton.class) {
				if (INSTANCE == null)
					INSTANCE = new Singleton();
			}
		}
		return INSTANCE;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
//		Since we are implementing singleton design pattern we are not going to return the clone object instead we would throw an CloneNotSupportedException object
//		return super.clone();
		throw new CloneNotSupportedException("Singleton class cannot be cloneable");
	}

// 	This method is added to prevent the single design pattern breakage during the serialization process or in distributed system
	public Object readResolve() {
		return getInstance(); 
	}
}
