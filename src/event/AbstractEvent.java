package event;

public abstract class AbstractEvent implements IEvent {
    @Override
    public void doEvent() {
        System.out.println("this is base class's event");
        doSomething();
        getResource();
    }

    protected abstract void doSomething();

    protected abstract void getResource();
}
