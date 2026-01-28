package se.mau.conversion;

public class PositionOnEarth {
    private double latitude;
    private double longitude;

    public enum coordType {
        LAT, LON
    }

    public PositionOnEarth() {
        latitude = 0;
        longitude = 0;
    }

    public double convertToDD(int deg, int min, int sec, coordType type) {

        if (min < 0 || min > 59 || sec < 0 || sec > 59) {
            throw new IllegalArgumentException("Minutes and seconds must be between 0 and 59");
        }

        if (type == coordType.LAT && (deg < -90 || deg > 90)) {
            throw new IllegalArgumentException("Latitude degrees must be between -90 and 90");
        }

        if (type == coordType.LON && (deg < -180 || deg > 180)) {
            throw new IllegalArgumentException("Longitude degrees must be between -180 and 180");
        }

        return deg + min / 60.0 + sec / 3600.0;
    }


    public double[] convertToD(double dd, coordType type) {

        if (type == coordType.LAT && (dd < -90 || dd > 90)) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if (type == coordType.LON && (dd < -180 || dd > 180)) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }

        double[] dms = new double[3];
        dms[0] = (int) dd;
        double remainder = Math.abs(dd - dms[0]);
        dms[1] = (int) (remainder * 60);
        dms[2] = (remainder - (dms[1] / 60.0)) * 3600;
        return dms;
    }

    public void clearPos(coordType type) {
        if (type == coordType.LAT) {
            latitude = 0;
        } else {
            longitude = 0;
        }
    }

    public double getPos(coordType type) {
        if (type == coordType.LAT) {
            return latitude;
        } else {
            return longitude;
        }
    }

    public void setPos(coordType type, int deg, int min, int sec) {
        double dd = convertToDD(deg, min, sec, type);
        if (type == coordType.LAT) {
            latitude = dd;
        } else {
            longitude = dd;
        }
    }
}
