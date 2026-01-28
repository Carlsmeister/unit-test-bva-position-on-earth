package se.mau;

import se.mau.conversion.PositionOnEarth;

import java.util.Arrays;

public class Main {

    static void main() {

        PositionOnEarth pos = new PositionOnEarth();

        System.out.println(pos.convertToDD(37450, 5, 25, PositionOnEarth.coordType.LAT));

        System.out.println(Arrays.toString(pos.convertToD(174, PositionOnEarth.coordType.LON)));

    }
}
