package com.signature;

import java.util.*;

public class Theatre {

    private final String name;
    private final int seatsPerRows;
    public final List<Seat> seats = new ArrayList<>();

    static final Comparator<Seat> PRICE_ORDER;

    static {
        PRICE_ORDER = (seat1, seat2) -> {
            if (seat1.getPrice() < seat2.getPrice()) {
                return -1;
            } else if (seat1.getPrice() > seat2.getPrice()) {
                return 1;
            } else {
                return 0;
            }
        };
    }
    public Theatre(String name, int rows, int seatsPerRows) {
        this.name = name;
        this.seatsPerRows = seatsPerRows;

        int lastRow = 'A' + (rows-1);

        for (char row = 'A'; row <= lastRow; row++) {
            for (int seat = 1; seat <= seatsPerRows; seat++) {
                double price = 12.00;

                if((row < 'D') && (seat >=4 && seat <=9)) {
                    price = 14.00;
                } else if((row > 'F') || (seat < 4 || seat > 9)) {
                    price = 7.00;
                }

                String newSeat = row + String.format("%02d",seat);
                seats.add(new Seat(newSeat, price));
            }
        }
    }

    public String getName() {
        return name;
    }

    public Collection<Seat> getSeats() {
        return seats;
    }

    public class Seat implements Comparable<Seat> {
        private final String seatNumber;
        private double price;
        private boolean booked;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
            this.booked = false;
        }

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
            this.price = price = 0.0;
            this.booked = false;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }

        public boolean reserve() {
            if (!this.booked) {
                this.booked = true;
                System.out.println("Seat " + this.seatNumber + " reserved.");
                return true;
            } else {
                System.out.println("Seat " + this.seatNumber + " is already reserved.");
                return false;
            }
        }

        public boolean cancel() {
            if (this.booked) {
                this.booked = false;
                System.out.println("Reservation of " + this.seatNumber + " is canceled");
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.seatNumber);
        }
    }

    public void showSeats() {
        System.out.print("\t");
        for (int i = 0; i < seats.size(); i++) {
            System.out.print(seats.get(i).getSeatNumber() + " ");
            if ((i+1)%(this.seatsPerRows/2) == 0) {
                System.out.print("\t\t");
            }
            if ((i+1)%this.seatsPerRows == 0) {
                System.out.print("\n\t");
            }
        }
//        for (Seat i : seats) {
//            System.out.println(i.getSeatNumber());
//        }
    }

    public void bookSeat(String seatNumber) {
        Seat resSeat = new Seat(seatNumber, 0.0);
        int res = Collections.binarySearch(seats, resSeat, null);
        
        if (res >= 0) {
            seats.get(res).reserve();
        } else {
            System.out.println("There is no seat " + seatNumber);
        }
    }

    public void cancelSeat(String seatNumber) {
        Seat resSeat = new Seat(seatNumber);
        int res = Collections.binarySearch(seats, resSeat, null);

        if (res >= 0) {
            seats.get(res).cancel();
        } else {
            System.out.println("There is no seat " + seatNumber);
        }
    }
}
