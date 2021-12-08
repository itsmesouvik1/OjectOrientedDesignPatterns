import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;

//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

public class SingletonMain {

//	public static void main(String[] args) {
//		final int SIZE = 5;
//		ExecutorService executorService = null;
//		try {
//			executorService = Executors.newFixedThreadPool(SIZE);
//			for (int i = 0; i < SIZE * 5; i++)
//				executorService.execute(() -> 
//					System.out.println("Current Thread name is " + Thread.currentThread().getName()
//							+ " hash code of singleton class is " + Singleton.getInstance().hashCode())
//				);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (executorService != null)
//				executorService.shutdown();
//		}
//		
////		Output of the above code
////		Current Thread name is pool-1-thread-3 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-1 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-4 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-5 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-2 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-5 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-4 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-1 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-3 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-1 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-4 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-5 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-2 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-5 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-4 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-1 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-3 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-1 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-4 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-5 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-2 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-5 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-4 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-1 hash code of singleton class is 1231566676
////		Current Thread name is pool-1-thread-3 hash code of singleton class is 1231566676
//	}

//	Breaking Singleton using Cloneable
//	This method throws exception
//	public static void main(String[] args) throws CloneNotSupportedException {
//		Singleton s1 = Singleton.getInstance();
//		Singleton s2 = (Singleton) s1.clone();
//		
//		System.out.println("hash code of singleton class is " + s1.hashCode());
//		System.out.println("hash code of singleton class is " + s2.hashCode());
////		Since the class is implementing the Cloneable Interface so a new object instance is given on the clone method which is breaking the Singleton design pattern
////		output of the above code is
////		hash code of singleton class is 1586600255
////		hash code of singleton class is 359023572
//		
////		After we throw the CloneNotSupportedException the following output we would see
////		Exception in thread "main" java.lang.CloneNotSupportedException: Singleton class cannot be cloneable
////		at Singleton.clone(Singleton.java:35)
////		at SingletonMain.main(SingletonMain.java:53)
//	}

//	Breaking Singleton using Reflection
//	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//
//		Singleton s1 = Singleton.getInstance();
//		Singleton s2 = null;
//
////    using Reflection we can change the behaviour of a class at Runtime.
//
//		Constructor<?>[] constructorList = Singleton.class.getDeclaredConstructors();
//		
////    the loop will run once as singleton class has 1 constuctor
//		for (Constructor<?> constructor : constructorList) {
//			// even though the constructor is private we are setting its accessibility to true
//			constructor.setAccessible(true);
//			Object ob = constructor.newInstance();
//			s2 = (Singleton) ob;
//		}
//		
//		System.out.println("hash code of singleton class is " + s1.hashCode());
//		System.out.println("hash code of singleton class is " + s2.hashCode());
//		
////		Output of the above code is
////		hash code of singleton class is 474675244
////		hash code of singleton class is 305808283
//	}

//	public static void main(String[] args) {
//		
////		ENUM's have global context. JVM ensures that enum's have single instance throughout the life cycle
////		Using the enum the lazy initialization is not ensured but Singleton cannot be broken using reflection and the other mechanisms
//		SingletonEnum s1 = SingletonEnum.GETINSTANCE;
//		SingletonEnum s2 = SingletonEnum.GETINSTANCE;
//		
//		System.out.println("hash code of singleton class is " + s1.hashCode());
//		System.out.println("hash code of singleton class is " + s2.hashCode());
//		
//		System.out.println(s1.DoSomeBusinessLogic());
//		
////	Output of the above code is
////		hash code of singleton class is 1586600255
////		hash code of singleton class is 1586600255
////		Did some business processing
//	}

//	Breaking Singleton using Serializable or in distributed system but added the readResolve method
	public static void main(String[] args) throws IOException {

		ObjectOutput objectOutput = null;
		ObjectInput objectInput = null;
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = null;
//		Serialization Process
		try {
			objectOutput = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
			objectOutput.writeObject(s1);
			objectOutput.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (objectOutput != null) {
				objectOutput.close();
			}
		}
		
//		Deserialization Process
		try {
			objectInput = new ObjectInputStream(new FileInputStream("singleton.ser"));		
			s2 = (Singleton) objectInput.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (objectInput != null) {
				objectInput.close();
			}
		}
		
		System.out.println("hash code of singleton class is " + s1.hashCode());
		System.out.println("hash code of singleton class is " + s2.hashCode());
		
//		Output of the above code is
//		hash code of singleton class is 466002798
//		hash code of singleton class is 591137559
	}
}
