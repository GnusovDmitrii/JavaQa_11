public class Radio {
    private int currentStation;
    private int currentVolume;
    private int numberOfStations;

    // Конструктор по умолчанию (10 станций)
    public Radio() {
        this.numberOfStations = 10;
        this.currentStation = 0;
        this.currentVolume = 50;
    }

    // Конструктор с заданным количеством станций
    public Radio(int numberOfStations) {
        if (numberOfStations <= 0) {
            this.numberOfStations = 10; // по умолчанию, если передано некорректное значение
        } else {
            this.numberOfStations = numberOfStations;
        }
        this.currentStation = 0;
        this.currentVolume = 50;
    }

    public int getCurrentStation() {
        return currentStation;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public int getNumberOfStations() {
        return numberOfStations;
    }

    public void setCurrentStation(int station) {
        if (station >= 0 && station < numberOfStations) {
            currentStation = station;
        }
    }

    public void next() {
        if (currentStation == numberOfStations - 1) {
            currentStation = 0;
        } else {
            currentStation++;
        }
    }

    public void prev() {
        if (currentStation == 0) {
            currentStation = numberOfStations - 1;
        } else {
            currentStation--;
        }
    }

    public void increaseVolume() {
        if (currentVolume < 100) {
            currentVolume++;
        }
    }

    public void decreaseVolume() {
        if (currentVolume > 0) {
            currentVolume--;
        }
    }
}