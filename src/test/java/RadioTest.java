import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class RadioTest {

    private Radio radio;

    @BeforeEach
    public void setUp() {
        radio = new Radio();
    }

    // === Тесты конструкторов ===

    @Test
    public void shouldCreateDefaultRadioWith10Stations() {
        assertEquals(0, radio.getCurrentStation());
        assertEquals(50, radio.getCurrentVolume());
        assertEquals(10, radio.getNumberOfStations());
    }

    @Test
    public void shouldCreateRadioWithCustomStations() {
        Radio customRadio = new Radio(5);
        assertEquals(0, customRadio.getCurrentStation());
        assertEquals(50, customRadio.getCurrentVolume());
        assertEquals(5, customRadio.getNumberOfStations());
    }

    @Test
    public void shouldCreateRadioWith1Station() {
        Radio oneStationRadio = new Radio(1);
        assertEquals(0, oneStationRadio.getCurrentStation());
        assertEquals(1, oneStationRadio.getNumberOfStations());
    }

    @Test
    public void shouldCreateRadioWithManyStations() {
        Radio manyStationsRadio = new Radio(100);
        assertEquals(0, manyStationsRadio.getCurrentStation());
        assertEquals(100, manyStationsRadio.getNumberOfStations());
    }

    @Test
    public void shouldHandleZeroOrNegativeStations() {
        Radio zeroStationsRadio = new Radio(0);
        assertEquals(10, zeroStationsRadio.getNumberOfStations());

        Radio negativeStationsRadio = new Radio(-5);
        assertEquals(10, negativeStationsRadio.getNumberOfStations());
    }

    // === Тесты для setCurrentStation ===

    @Test
    public void shouldSetValidStationForDefaultRadio() {
        radio.setCurrentStation(5);
        assertEquals(5, radio.getCurrentStation());

        radio.setCurrentStation(0);
        assertEquals(0, radio.getCurrentStation());

        radio.setCurrentStation(9);
        assertEquals(9, radio.getCurrentStation());
    }

    @Test
    public void shouldNotSetNegativeStation() {
        radio.setCurrentStation(5);
        radio.setCurrentStation(-1);
        assertEquals(5, radio.getCurrentStation());
    }

    @Test
    public void shouldNotSetStationAboveMaxForDefaultRadio() {
        radio.setCurrentStation(5);
        radio.setCurrentStation(10);
        assertEquals(5, radio.getCurrentStation());

        radio.setCurrentStation(15);
        assertEquals(5, radio.getCurrentStation());
    }

    @Test
    public void shouldSetValidStationForCustomRadio() {
        Radio customRadio = new Radio(7);
        customRadio.setCurrentStation(0);
        assertEquals(0, customRadio.getCurrentStation());

        customRadio.setCurrentStation(3);
        assertEquals(3, customRadio.getCurrentStation());

        customRadio.setCurrentStation(6);
        assertEquals(6, customRadio.getCurrentStation());
    }

    @Test
    public void shouldNotSetInvalidStationForCustomRadio() {
        Radio customRadio = new Radio(7);
        customRadio.setCurrentStation(3);

        customRadio.setCurrentStation(-1);
        assertEquals(3, customRadio.getCurrentStation());

        customRadio.setCurrentStation(7);
        assertEquals(3, customRadio.getCurrentStation());

        customRadio.setCurrentStation(10);
        assertEquals(3, customRadio.getCurrentStation());
    }

    // === Тесты для next() ===

    @Test
    public void shouldSwitchToNextStationFromMiddle() {
        radio.setCurrentStation(5);
        radio.next();
        assertEquals(6, radio.getCurrentStation());
    }

    @Test
    public void shouldSwitchToNextStationFrom8() {
        radio.setCurrentStation(8);
        radio.next();
        assertEquals(9, radio.getCurrentStation());
    }

    @Test
    public void shouldSwitchFromLastToFirstStation() {
        radio.setCurrentStation(9);
        radio.next();
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    public void shouldSwitchToNextForCustomRadio() {
        Radio customRadio = new Radio(5);
        customRadio.setCurrentStation(2);
        customRadio.next();
        assertEquals(3, customRadio.getCurrentStation());
    }

    @Test
    public void shouldSwitchFromLastToFirstForCustomRadio() {
        Radio customRadio = new Radio(5);
        customRadio.setCurrentStation(4);
        customRadio.next();
        assertEquals(0, customRadio.getCurrentStation());
    }

    @Test
    public void shouldHandleNextForSingleStationRadio() {
        Radio singleStationRadio = new Radio(1);
        singleStationRadio.next();
        assertEquals(0, singleStationRadio.getCurrentStation()); // всегда 0
    }

    // === Тесты для prev() ===

    @Test
    public void shouldSwitchToPrevStationFromMiddle() {
        radio.setCurrentStation(5);
        radio.prev();
        assertEquals(4, radio.getCurrentStation());
    }

    @Test
    public void shouldSwitchToPrevStationFrom1() {
        radio.setCurrentStation(1);
        radio.prev();
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    public void shouldSwitchFromFirstToLastStation() {
        radio.setCurrentStation(0);
        radio.prev();
        assertEquals(9, radio.getCurrentStation());
    }

    @Test
    public void shouldSwitchToPrevForCustomRadio() {
        Radio customRadio = new Radio(5);
        customRadio.setCurrentStation(3);
        customRadio.prev();
        assertEquals(2, customRadio.getCurrentStation());
    }

    @Test
    public void shouldSwitchFromFirstToLastForCustomRadio() {
        Radio customRadio = new Radio(5);
        customRadio.setCurrentStation(0);
        customRadio.prev();
        assertEquals(4, customRadio.getCurrentStation());
    }

    @Test
    public void shouldHandlePrevForSingleStationRadio() {
        Radio singleStationRadio = new Radio(1);
        singleStationRadio.prev();
        assertEquals(0, singleStationRadio.getCurrentStation()); // всегда 0
    }

    // === Тесты для increaseVolume() ===

    @Test
    public void shouldIncreaseVolumeFromMiddle() {
        int initialVolume = radio.getCurrentVolume();
        radio.increaseVolume();
        assertEquals(initialVolume + 1, radio.getCurrentVolume());
    }

    @Test
    public void shouldIncreaseVolumeFrom99() {
        // Устанавливаем громкость 99
        Radio testRadio = new Radio();
        while (testRadio.getCurrentVolume() < 99) {
            testRadio.increaseVolume();
        }
        assertEquals(99, testRadio.getCurrentVolume());

        testRadio.increaseVolume();
        assertEquals(100, testRadio.getCurrentVolume());
    }

    @Test
    public void shouldNotIncreaseVolumeAbove100() {
        // Устанавливаем максимальную громкость
        Radio testRadio = new Radio();
        while (testRadio.getCurrentVolume() < 100) {
            testRadio.increaseVolume();
        }
        assertEquals(100, testRadio.getCurrentVolume());

        testRadio.increaseVolume();
        assertEquals(100, testRadio.getCurrentVolume());
    }

    // === Тесты для decreaseVolume() ===

    @Test
    public void shouldDecreaseVolumeFromMiddle() {
        int initialVolume = radio.getCurrentVolume();
        radio.decreaseVolume();
        assertEquals(initialVolume - 1, radio.getCurrentVolume());
    }

    @Test
    public void shouldDecreaseVolumeFrom1() {
        // Устанавливаем громкость 1
        Radio testRadio = new Radio();
        while (testRadio.getCurrentVolume() > 1) {
            testRadio.decreaseVolume();
        }
        assertEquals(1, testRadio.getCurrentVolume());

        testRadio.decreaseVolume();
        assertEquals(0, testRadio.getCurrentVolume());
    }

    @Test
    public void shouldNotDecreaseVolumeBelow0() {
        // Устанавливаем минимальную громкость
        Radio testRadio = new Radio();
        while (testRadio.getCurrentVolume() > 0) {
            testRadio.decreaseVolume();
        }
        assertEquals(0, testRadio.getCurrentVolume());

        testRadio.decreaseVolume();
        assertEquals(0, testRadio.getCurrentVolume());
    }

    // === Комплексные тесты ===

    @Test
    public void testMultipleOperations() {
        radio.setCurrentStation(5);
        assertEquals(5, radio.getCurrentStation());

        radio.next();
        assertEquals(6, radio.getCurrentStation());

        radio.prev();
        assertEquals(5, radio.getCurrentStation());

        radio.setCurrentStation(9);
        assertEquals(9, radio.getCurrentStation());

        radio.next();
        assertEquals(0, radio.getCurrentStation());

        radio.prev();
        assertEquals(9, radio.getCurrentStation());
    }

    @Test
    public void testStationBoundaryConditions() {
        Radio customRadio = new Radio(4);

        // Переход вперед через границу
        customRadio.setCurrentStation(3);
        customRadio.next();
        assertEquals(0, customRadio.getCurrentStation());

        // Переход назад через границу
        customRadio.setCurrentStation(0);
        customRadio.prev();
        assertEquals(3, customRadio.getCurrentStation());
    }

    @Test
    public void testVolumeBoundaryConditions() {
        Radio testRadio = new Radio();

        // Достигаем минимума
        for (int i = 0; i < 100; i++) {
            testRadio.decreaseVolume();
        }
        assertEquals(0, testRadio.getCurrentVolume());

        // Достигаем максимума
        for (int i = 0; i < 150; i++) {
            testRadio.increaseVolume();
        }
        assertEquals(100, testRadio.getCurrentVolume());
    }

    @Test
    public void testConsecutiveOperations() {
        Radio testRadio = new Radio(7);

        // Серия операций next
        testRadio.next(); // 0→1
        testRadio.next(); // 1→2
        testRadio.next(); // 2→3
        assertEquals(3, testRadio.getCurrentStation());

        // Серия операций prev
        testRadio.prev(); // 3→2
        testRadio.prev(); // 2→1
        testRadio.prev(); // 1→0
        testRadio.prev(); // 0→6
        assertEquals(6, testRadio.getCurrentStation());

        // Работа с громкостью
        testRadio.increaseVolume();
        testRadio.increaseVolume();
        assertEquals(52, testRadio.getCurrentVolume());

        testRadio.decreaseVolume();
        assertEquals(51, testRadio.getCurrentVolume());
    }

    @Test
    public void testEdgeCases() {
        // Радио с одной станцией
        Radio singleStation = new Radio(1);
        assertEquals(0, singleStation.getCurrentStation());

        singleStation.next();
        assertEquals(0, singleStation.getCurrentStation());

        singleStation.prev();
        assertEquals(0, singleStation.getCurrentStation());

        singleStation.setCurrentStation(0);
        assertEquals(0, singleStation.getCurrentStation());

        // Попытка установить недопустимую станцию
        singleStation.setCurrentStation(1);
        assertEquals(0, singleStation.getCurrentStation());
    }

    @Test
    public void testDifferentRadiosIndependent() {
        Radio radio1 = new Radio(); // 10 станций
        Radio radio2 = new Radio(5); // 5 станций
        Radio radio3 = new Radio(15); // 15 станций

        radio1.setCurrentStation(5);
        radio2.setCurrentStation(2);
        radio3.setCurrentStation(10);

        assertEquals(5, radio1.getCurrentStation());
        assertEquals(2, radio2.getCurrentStation());
        assertEquals(10, radio3.getCurrentStation());

        radio1.next();
        radio2.next();
        radio3.next();

        assertEquals(6, radio1.getCurrentStation());
        assertEquals(3, radio2.getCurrentStation());
        assertEquals(11, radio3.getCurrentStation());
    }
}