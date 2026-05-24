package ro.ulbs.proiectaresoftware.lab11;

public class MediaInterested implements Observer {
    private String name;

    public MediaInterested(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("[" + name + "] received update: " + message);
    }
}