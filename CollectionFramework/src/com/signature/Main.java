package com.signature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Theatre theatre = new Theatre("Multiplex",8, 12);
//        theatre.showSeats();
        theatre.bookSeat("A01");
        theatre.bookSeat("H10");
        theatre.bookSeat("H12");
        theatre.bookSeat("M12");
        theatre.bookSeat("J21");
        theatre.bookSeat("H10");
        theatre.cancelSeat("A01");
        theatre.cancelSeat("H21");
        theatre.cancelSeat("A01");
        theatre.cancelSeat("A02");
//
//        List<Theatre.Seat> reverseSeats = new ArrayList<>(theatre.getSeats());
//        Collections.reverse(reverseSeats);
//        printList(reverseSeats);


        List<Theatre.Seat> priceSeats = new ArrayList<>(theatre.getSeats());
        priceSeats.add(theatre.new Seat("B00", 13.00));
        priceSeats.add(theatre.new Seat("A00", 13.00));
        Collections.sort(priceSeats, Theatre.PRICE_ORDER);
        printList(priceSeats);
//
//        List<Theatre.Seat> copySeats = new ArrayList<>(theatre.seats);
//        printList(copySeats);
//
//        copySeats.get(1).reserve();
//        theatre.bookSeat("A02");
//
////        Collections.shuffle(copySeats);
////        printList(copySeats);
////        printList(theatre.seats);
//
//        Theatre.Seat minSeat = Collections.min(copySeats);
//        Theatre.Seat maxSeat = Collections.max(copySeats);
//        System.out.println("Min seat number is " + minSeat.getSeatNumber());
//        System.out.println("Max seat number is " + maxSeat.getSeatNumber());
//
//        sortList(copySeats);
//        System.out.println("Printing sorted seatCopy");
//        printList(copySeats);


//        List<Theatre.Seat> newList = new ArrayList<>(theatre.seats.size());
//        Collections.copy(newList, theatre.seats);
    }
    
    public static void printList(List<? extends Theatre.Seat> list) {
        for (Theatre.Seat seat : list ) {
            System.out.println(seat.getSeatNumber() + " $" + seat.getPrice());
        }
    }

    public static void sortList(List<? extends Theatre.Seat> list) {
        for(int i=0; i<list.size() -1; i++) {
            for(int j=i+1; j<list.size(); j++) {
                if(list.get(i).compareTo(list.get(j)) >0) {
                    Collections.swap(list, i, j);
                }
            }
        }
    }
}
