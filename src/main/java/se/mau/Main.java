package se.mau;

import se.mau.conversion.PositionOnEarth;

import java.util.Arrays;

public class Main {

    static void main() {

        PositionOnEarth pos = new PositionOnEarth();

        System.out.println(pos.convertToDD(370, 5, 25));

        System.out.println(Arrays.toString(pos.convertToD(359.2638888888889)));

    }
}
