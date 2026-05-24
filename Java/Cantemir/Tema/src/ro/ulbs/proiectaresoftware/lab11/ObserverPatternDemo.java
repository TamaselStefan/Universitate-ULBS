package ro.ulbs.proiectaresoftware.lab11;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel("Tech Explained");

        MediaInterested media1 = new MediaInterested("CNN");
        MediaInterested media2 = new MediaInterested("FoxNews");

        channel.addObserver(media1);
        channel.addObserver(media2);
        channel.uploadVideo("Observer Pattern in Java");

        System.out.println();

        channel.removeObserver(media2);
        channel.uploadVideo("Singleton Pattern in Java");
    }
}