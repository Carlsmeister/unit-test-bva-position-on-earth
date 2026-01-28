package se.mau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.mau.conversion.PositionOnEarth;

import static org.junit.jupiter.api.Assertions.*;

public class TestPositionOnEarth {

    private PositionOnEarth pos;
    private int[] lowerBoundLat = {-91, -90, -89};
    private int[] upperBoundLat = {89, 90, 91};

    private int[] lowerBoundLon = {-181, -180, -179};
    private int[] upperBoundLon = {179, 180, 181};

    private int[] lowerBoundMin = {-1, 0, 1};
    private int[] upperBoundMin = {59, 60, 61};
    private int[] lowerBoundSec = {-1, 0, 1};
    private int[] upperBoundSec = {59, 60, 61};

    @BeforeEach
    public void setUp() {
        pos = new PositionOnEarth();
    }

    @Test
    public void convertToDD_NormalCase_Lat() {
        double result = pos.convertToDD(30, 15, 50, PositionOnEarth.coordType.LAT);
        double expected = 30 + 15.0 / 60 + 50.0 / 3600;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void convertToDD_InvalidDegLow_Lat() {
        assertThrows(IllegalArgumentException.class,
                () -> pos.convertToDD(-91, 0, 0, PositionOnEarth.coordType.LAT));
    }

    @Test
    public void convertToDD_InvalidDegHigh_Lat() {
        assertThrows(IllegalArgumentException.class,
                () -> pos.convertToDD(91, 0, 0, PositionOnEarth.coordType.LAT));
    }

    @Test
    public void convertToDD_invalidDegLow_Lon() {
        assertThrows(IllegalArgumentException.class,
                () -> pos.convertToDD(-181, 0, 0, PositionOnEarth.coordType.LON));
    }

    @Test
    public void convertToDD_invalidDegHigh_Lon() {
        assertThrows(IllegalArgumentException.class,
                () -> pos.convertToDD(181, 0, 0, PositionOnEarth.coordType.LON));
    }

    @Test
    public void convertToDD_BVA_Min0_Sec0_Lat() {
        double result = pos.convertToDD(30, 0, 0, PositionOnEarth.coordType.LAT);
        assertEquals(30.0, result, 0.0001);
    }

    @Test
    public void convertToDD_BVA_Min59_Sec59_Lat() {
        double result = pos.convertToDD(30, 59, 59, PositionOnEarth.coordType.LAT);
        double expected = 30 + 59.0 / 60 + 59.0 / 3600;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void convertToDD_InvalidMinLow() {
        assertThrows(IllegalArgumentException.class,
                () -> pos.convertToDD(30, -1, 0, PositionOnEarth.coordType.LAT));
    }

    @Test
    public void convertToDD_InvalidMinHigh() {
        assertThrows(IllegalArgumentException.class,
                () -> pos.convertToDD(30, 60, 0, PositionOnEarth.coordType.LAT));
    }

    @Test
    public void convertToDD_InvalidSecLow() {
        assertThrows(IllegalArgumentException.class,
                () -> pos.convertToDD(30, 0, -1, PositionOnEarth.coordType.LAT));
    }

    @Test
    public void convertToDD_InvalidSecHigh() {
        assertThrows(IllegalArgumentException.class,
                () -> pos.convertToDD(30, 0, 60, PositionOnEarth.coordType.LAT));
    }

    @Test
    public void convertToD_Lat_BVA_0() {
        double[] dms = pos.convertToD(0, PositionOnEarth.coordType.LAT);
        assertEquals(0, dms[0]);
        assertEquals(0, dms[1]);
        assertEquals(0, dms[2], 0.1);
    }

    @Test
    public void convertToD_Lat_BVA_90() {
        double[] dms = pos.convertToD(90, PositionOnEarth.coordType.LAT);
        assertEquals(90, dms[0]);
        assertEquals(0, dms[1]);
        assertEquals(0, dms[2], 0.1);
    }

    @Test
    public void convertToD_Lat_InvalidLow() {
        assertThrows(IllegalArgumentException.class,
                () -> pos.convertToD(-91, PositionOnEarth.coordType.LAT));
    }

    @Test
    public void convertToD_Lat_InvalidHigh() {
        assertThrows(IllegalArgumentException.class,
                () -> pos.convertToD(91, PositionOnEarth.coordType.LAT));
    }

    @Test
    public void convertToD_Lon_BVA_180() {
        double[] dms = pos.convertToD(180, PositionOnEarth.coordType.LON);
        assertEquals(180, dms[0]);
        assertEquals(0, dms[1]);
        assertEquals(0, dms[2], 0.1);
    }

    @Test
    public void convertToD_Lon_InvalidLow() {
        assertThrows(IllegalArgumentException.class,
                () -> pos.convertToD(-181, PositionOnEarth.coordType.LON));
    }

    @Test
    public void convertToD_Lon_InvalidHigh() {
        assertThrows(IllegalArgumentException.class,
                () -> pos.convertToD(181, PositionOnEarth.coordType.LON));
    }

    @Test
    public void convertToD_Fractional_Lat() {
        double[] dms = pos.convertToD(30.263888, PositionOnEarth.coordType.LAT);
        assertEquals(30, dms[0]);
        assertEquals(15, dms[1]);
        assertEquals(50, dms[2], 0.1);
    }

    @Test
    public void setPos_Lat_Valid() {
        pos.setPos(PositionOnEarth.coordType.LAT, 45, 30, 0);
        assertEquals(45.5, pos.getPos(PositionOnEarth.coordType.LAT));
    }

    @Test
    public void setPos_Lon_Valid() {
        pos.setPos(PositionOnEarth.coordType.LON, 179, 59, 59);
        double expected = 179 + 59.0 / 60 + 59.0 / 3600;
        assertEquals(expected, pos.getPos(PositionOnEarth.coordType.LON), 0.0001);
    }

    @Test
    public void clearPos_Lat() {
        pos.setPos(PositionOnEarth.coordType.LAT, 45, 0, 0);
        pos.clearPos(PositionOnEarth.coordType.LAT);
        assertEquals(0.0, pos.getPos(PositionOnEarth.coordType.LAT));
    }

    @Test
    public void clearPos_Lon() {
        pos.setPos(PositionOnEarth.coordType.LON, 179, 0, 0);
        pos.clearPos(PositionOnEarth.coordType.LON);
        assertEquals(0.0, pos.getPos(PositionOnEarth.coordType.LON), 0.0001);
    }

    @Test
    public void roundTrip_Positive_Lat() {
        double dd = pos.convertToDD(45, 30, 45, PositionOnEarth.coordType.LAT);
        double[] dms = pos.convertToD(dd, PositionOnEarth.coordType.LAT);

        assertEquals(45, dms[0]);
        assertEquals(30, dms[1]);
        assertEquals(45, dms[2], 0.1);
    }

    @Test
    public void additionConsistency_Latitude() {
        double sum =
                pos.convertToDD(30, 0, 0, PositionOnEarth.coordType.LAT) +
                        pos.convertToDD(30, 0, 0, PositionOnEarth.coordType.LAT) +
                        pos.convertToDD(30, 0, 0, PositionOnEarth.coordType.LAT);

        double[] dms = pos.convertToD(sum, PositionOnEarth.coordType.LAT);

        assertEquals(90, dms[0]);
        assertEquals(0, dms[1]);
        assertEquals(0, dms[2], 0.1);
    }
}
