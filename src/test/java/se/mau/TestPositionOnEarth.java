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
    public void convertToDD() {
        double result = pos.convertToDD(30, 15, 50);
        double expected = 30 + 15.0 / 60 + 50.0 / 3600;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void convertToDD_BVA_Min0_Sec0() {
        double result = pos.convertToDD(30, 0, 0);
        assertEquals(30.0, result);
    }

    @Test
    public void convertToDD_BVA_Min59_Sec59() {
        double result = pos.convertToDD(30, 59, 59);
        double expected = 30 + 59.0 / 60 + 59.0 / 3600;
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testConvertToDD_InvalidMin() {
        assertThrows(IllegalArgumentException.class, () -> pos.convertToDD(30, -1, 0));
    }

    @Test
    public void testConvertToDD_InvalidSec() {
        assertThrows(IllegalArgumentException.class, () -> pos.convertToDD(30, 0, -1));
    }

    @Test
    public void testConvertToDD_InvalidMin60() {
        assertThrows(IllegalArgumentException.class, () -> pos.convertToDD(30, 60, 0));
    }

    @Test
    public void testConvertToDD_InvalidSec60() {
        assertThrows(IllegalArgumentException.class, () -> pos.convertToDD(30, 0, 60));
    }

    @Test
    public void convertToD_BVA_0() {
        double[] dms = pos.convertToD(0);
        assertEquals(0, dms[0]);
        assertEquals(0, dms[1]);
        assertEquals(0, dms[2], 0.1);
    }

    @Test
    public void convertToD_BVA_90() {
        double[] dms = pos.convertToD(90);
        assertEquals(90, dms[0]);
        assertEquals(0, dms[1]);
        assertEquals(0, dms[2], 0.1);
    }

    @Test
    public void convertToD_BVA_Neg90() {
        double[] dms = pos.convertToD(-90);
        assertEquals(-90, dms[0]);
        assertEquals(0, dms[1]);
        assertEquals(0, dms[2], 0.1);
    }

    @Test
    public void convertToD_BVA_180() {
        double[] dms = pos.convertToD(180);
        assertEquals(180, dms[0]);
        assertEquals(0, dms[1]);
        assertEquals(0, dms[2], 0.1);
    }

    @Test
    public void convertToD_BVA_Neg180() {
        double[] dms = pos.convertToD(-180);
        assertEquals(-180, dms[0]);
        assertEquals(0, dms[1]);
        assertEquals(0, dms[2], 0.1);
    }

    @Test
    public void convertToD_BVA_Fractional() {
        double[] dms = pos.convertToD(30.263888);
        assertEquals(30, dms[0]);
        assertEquals(15, dms[1]);
        assertEquals(50, dms[2], 0.1);
    }

    @Test
    public void setPos_Lat_BVA_Valid_90_0_0() {
        pos.setPos(PositionOnEarth.coordType.LAT, 90, 0, 0);
        assertEquals(90.0, pos.getPos(PositionOnEarth.coordType.LAT));
    }

    @Test
    public void setPos_Lat_BVA_Valid_Neg90_0_0() {
        pos.setPos(PositionOnEarth.coordType.LAT, -90, 0, 0);
        assertEquals(-90.0, pos.getPos(PositionOnEarth.coordType.LAT));
    }

    @Test
    public void setPos_Lat_BVA_Valid_89_59_59() {
        pos.setPos(PositionOnEarth.coordType.LAT, 89, 59, 59);
        double expected = 89 + 59.0 / 60 + 59.0 / 3600;
        assertEquals(expected, pos.getPos(PositionOnEarth.coordType.LAT), 0.0001);
    }

    @Test
    public void setPos_Lat_BVA_Invalid_91_0_0() {
        pos.setPos(PositionOnEarth.coordType.LAT, 91, 0, 0);
        assertEquals(0.0, pos.getPos(PositionOnEarth.coordType.LAT));
    }

    @Test
    public void setPos_Lat_BVA_Invalid_Neg91_0_0() {
        pos.setPos(PositionOnEarth.coordType.LAT, -91, 0, 0);
        assertEquals(0.0, pos.getPos(PositionOnEarth.coordType.LAT));
    }

    @Test
    public void setPos_Lat_BVA_Invalid_90_0_1() {
        pos.setPos(PositionOnEarth.coordType.LAT, 90, 0, 1);
        assertEquals(0.0, pos.getPos(PositionOnEarth.coordType.LAT));
    }

    @Test
    public void setPos_Lon_BVA_Valid_180_0_0() {
        pos.setPos(PositionOnEarth.coordType.LON, 180, 0, 0);
        assertEquals(180.0, pos.getPos(PositionOnEarth.coordType.LON));
    }

    @Test
    public void setPos_Lon_BVA_Valid_Neg180_0_0() {
        pos.setPos(PositionOnEarth.coordType.LON, -180, 0, 0);
        assertEquals(-180.0, pos.getPos(PositionOnEarth.coordType.LON));
    }

    @Test
    public void setPos_Lon_BVA_Valid_179_59_59() {
        pos.setPos(PositionOnEarth.coordType.LON, 179, 59, 59);
        double expected = 179 + 59.0 / 60 + 59.0 / 3600;
        assertEquals(expected, pos.getPos(PositionOnEarth.coordType.LON), 0.0001);
    }

    @Test
    public void setPos_Lon_BVA_Invalid_181_0_0() {
        pos.setPos(PositionOnEarth.coordType.LON, 181, 0, 0);
        assertEquals(0.0, pos.getPos(PositionOnEarth.coordType.LON));
    }

    @Test
    public void setPos_Lon_BVA_Invalid_Neg181_0_0() {
        pos.setPos(PositionOnEarth.coordType.LON, -181, 0, 0);
        assertEquals(0.0, pos.getPos(PositionOnEarth.coordType.LON));
    }

    @Test
    public void setPos_Lon_BVA_Invalid_180_0_1() {
        pos.setPos(PositionOnEarth.coordType.LON, 180, 0, 1);
        assertEquals(0.0, pos.getPos(PositionOnEarth.coordType.LON));
    }

    @Test
    public void clearPos_Lat() {
        pos.setPos(PositionOnEarth.coordType.LAT, 45, 0, 0);
        pos.clearPos(PositionOnEarth.coordType.LAT);
        assertEquals(0.0, pos.getPos(PositionOnEarth.coordType.LAT));
    }

    @Test
    public void clearPos_Lon() {
        pos.setPos(PositionOnEarth.coordType.LON, 45, 0, 0);
        pos.clearPos(PositionOnEarth.coordType.LON);
        assertEquals(0.0, pos.getPos(PositionOnEarth.coordType.LON));
    }

    @Test
    public void getPos_Lat() {
        pos.setPos(PositionOnEarth.coordType.LAT, 45, 30, 0);
        assertEquals(45.5, pos.getPos(PositionOnEarth.coordType.LAT));
    }

    @Test
    public void getPos_Lon() {
        pos.setPos(PositionOnEarth.coordType.LON, 45, 30, 0);
        assertEquals(45.5, pos.getPos(PositionOnEarth.coordType.LON));
    }

    @Test
    public void testRoundTrip() {
        int deg = 45;
        int min = 30;
        int sec = 45;
        double dd = pos.convertToDD(deg, min, sec);
        double[] dms = pos.convertToD(dd);
        assertEquals(deg, dms[0]);
        assertEquals(min, dms[1]);
        assertEquals(sec, dms[2], 0.1);
    }

    @Test
    public void testRoundTripNegative() {
        int deg = -45;
        int min = 30;
        int sec = 45;
        double dd = pos.convertToDD(deg, min, sec);
        double[] dms = pos.convertToD(dd);
        assertEquals(-44, dms[0]);
        assertEquals(29, dms[1]);
        assertEquals(15, dms[2], 0.1);
    }

    @Test
    public void testAdditionConsistencyLatitude() {
        double dd1 = pos.convertToDD(30, 0, 0);
        double dd2 = pos.convertToDD(30, 0, 0);
        double dd3 = pos.convertToDD(30, 0, 0);
        double sumDD = dd1 + dd2 + dd3;
        double[] dms_sum = pos.convertToD(sumDD);
        assertEquals(90, dms_sum[0]);
        assertEquals(0, dms_sum[1]);
        assertEquals(0, dms_sum[2], 0.1);
    }

    @Test
    public void testAdditionConsistencyLongitude() {
        double dd1 = pos.convertToDD(60, 0, 0);
        double dd2 = pos.convertToDD(60, 0, 0);
        double dd3 = pos.convertToDD(60, 0, 0);
        double sumDD = dd1 + dd2 + dd3;
        double[] dms_sum = pos.convertToD(sumDD);
        assertEquals(180, dms_sum[0]);
        assertEquals(0, dms_sum[1]);
        assertEquals(0, dms_sum[2], 0.1);
    }

    @Test
    public void testConvertToD_InvalidLow() {
        assertThrows(IllegalArgumentException.class, () -> pos.convertToD(-181));
    }

    @Test
    public void testConvertToD_InvalidHigh() {
        assertThrows(IllegalArgumentException.class, () -> pos.convertToD(181));
    }
}
