package ro.ulbs.proiectaresoftware.lab11;

import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String channelName;

    public YouTubeChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void uploadVideo(String title) {
        System.out.println("{" + channelName + "} uploaded a new video: " + title);
        notifyObservers(title);
    }
}