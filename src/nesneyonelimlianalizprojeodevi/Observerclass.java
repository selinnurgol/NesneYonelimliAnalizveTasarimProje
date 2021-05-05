package nesneyonelimlianalizprojeodevi;

// Observer tasarım deseninin gerçeklenmesi
public class Observerclass implements IObserver{

	public void inform(String message) 
        {
		System.out.println(message);
	}
}
