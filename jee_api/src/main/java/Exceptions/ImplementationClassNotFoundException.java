package Exceptions;

public class ImplementationClassNotFoundException extends Exception
{
    public ImplementationClassNotFoundException(String interfaceNameSearched) {
        super("Implementation of the interface \""+interfaceNameSearched+"\" can not be found");
    }

    public ImplementationClassNotFoundException(String interfaceNameSearched, Throwable throwable) {
        super("Implementation of the interface \"\"+interfaceNameSearched+\"\" can not be found", throwable);
    }
}
