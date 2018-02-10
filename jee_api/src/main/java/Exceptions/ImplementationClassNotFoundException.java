package Api;

public class ImplementationClassNotFoundException extends Exception
{
    public ImplementationClassNotFoundException(String interfaceNameSearched) {
        super(String.format("Implementation of the interface \"{0}\" can not be found", interfaceNameSearched));
    }

    public ImplementationClassNotFoundException(String interfaceNameSearched, Throwable throwable) {
        super(String.format("Implementation of the interface \"{0}\" can not be found", interfaceNameSearched), throwable);
    }
}
