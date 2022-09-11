package com.signature;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double price=0.0;
        Scanner sc = new Scanner(System.in);
        System.out.println("\"Welcome to Bills Burgers\"");
        menuCard();
        System.out.println("Press y to place order and n to exit.");
        char ch=sc.nextLine().charAt(0);
        while(ch=='y' || ch=='Y') {
            for (int i = 1; i <= 3; i++) {
                Hamburger hamburger = burgerType(i);
                System.out.println("Press " + i + " to order " + hamburger.getName() + "\t\tRs." + hamburger.getPrice());
            }
            int x = sc.nextInt();
            Hamburger hamburger = burgerType(x);
            System.out.println("Please enter quantity.");
            int quantity = sc.nextInt();
            double amount=quantity * hamburger.getPrice();
            price += amount;
            System.out.println("Name- " + hamburger.getName() + "\t\tQuantity- " + quantity + "\t\tRate- Rs." + hamburger.getPrice() + "\t\tAmount- Rs." + String.format("%.2f",amount));
            if (x == 2) {
                price = price + quantity * (hamburger.chips() + hamburger.drink());
                System.out.println("Extra\nName- Chips\t\tPrice- Rs.50\nName- Drink\t\tPrice- Rs.40");
            }
            else {
                System.out.println("Extra Toppings:");
                System.out.println("Press 0 to add nothing.");
                System.out.println("Press 1 to add Lettuce\t\tRs." + hamburger.extraToppings("lettuce"));
                System.out.println("Press 2 to add Tomato\t\tRs." + hamburger.extraToppings("tomato"));
                System.out.println("Press 3 to add Carrot\t\tRs." + hamburger.extraToppings("carrot"));
                System.out.println("Press 4 to add Cheese\t\tRs." + hamburger.extraToppings("cheese"));
                if (x != 3)
                    System.out.println("Press 5 to add Meat  \t\tRs." + hamburger.extraToppings("meat"));
                if(x==1) {System.out.println("Press 6 to add Onion\t\tRs."+hamburger.extraToppings("onion"));
                    System.out.println("Press 7 to add Capsicum\t\tRs."+hamburger.extraToppings("capsicum"));}
                int y = sc.nextInt();
                if(y!=0) {
                    if(quantity!=1){System.out.println("To how many burgers would you like to add toppings?");
                        int quantity2 = sc.nextInt();
                        if(quantity2<=quantity)quantity=quantity2;}
                    else quantity=1;
                    switch (y) {
                        case 1:
                            amount=quantity * hamburger.extraToppings("lettuce");
                            price += amount;
                            System.out.println("Name- Lettuce\t\tQuantity-" + quantity + "\tRate- Rs." + hamburger.extraToppings("Lettuce") + "\tAmount- " + amount);
                            break;
                        case 2:
                            amount=quantity * hamburger.extraToppings("tomato");
                            price += amount;
                            System.out.println("Name- Tomato\t\tQuantity-" + quantity + "\tRate- Rs." + hamburger.extraToppings("tomato") + "\tAmount- " + amount);
                            break;
                        case 3:
                            amount=quantity*hamburger.extraToppings("carrot");
                            price += quantity * amount;
                            System.out.println("Name- Carrot\t\tQuantity-" + quantity + "\tRate- Rs." + hamburger.extraToppings("carrot") + "\tAmount- " +amount);
                            break;
                        case 4:
                            amount=quantity*hamburger.extraToppings("cheese");
                            price += quantity * amount;
                            System.out.println("Name- Cheese\t\tQuantity-" + quantity + "\tRate- Rs." + hamburger.extraToppings("cheese") + "\tAmount- " + amount);
                            break;
                        case 5:
                            amount=quantity*hamburger.extraToppings("meat");
                            price += quantity * amount;
                            System.out.println("Name- Meat\t\tQuantity-" + quantity + "\tRate- Rs." + hamburger.extraToppings("meat") + "\tAmount- " + amount);
                            break;
                        case 6:
                            amount=quantity*hamburger.extraToppings("onion");
                            price += quantity * amount;
                            System.out.println("Name- Onion\t\tQuantity-" + quantity + "\tRate- Rs." + hamburger.extraToppings("onion") + "\tAmount- " + amount);
                            break;
                        case 7:
                            amount=quantity*hamburger.extraToppings("capsicum");
                            price += quantity * amount;
                            System.out.println("Name- Capsicum\t\tQuantity-" + quantity + "\tRate- Rs." + hamburger.extraToppings("capsicum") + "\tAmount- " + amount);
                            break;
                    }
                }
            }
            System.out.println("Press y to add continue order.\nPress n to close.\nPress 0 to view menu card.\nPress T to know total.");
            if(sc.hasNextInt()){
                sc.nextInt(); menuCard();
                System.out.println("Press y to add another order and n to close..");ch=sc.next().charAt(0);}
            else{
                sc.nextLine();
                ch=sc.nextLine().charAt(0);
                if(ch=='t' || ch=='T' || ch=='n' || ch=='N'){
                    System.out.println("Total\t\t\tRs."+price);
                    System.out.println("CGST\t\t\t@2.5%\nSGST\t\t\t@2.5%");
                    System.out.println("Net amount\t\tRs."+String.format("%.2f",price*1.05));
                }
            }
        }
    }
    public static void menuCard(){
        for(int i=1;i<=3;i++) {
            Hamburger hamburger = burgerType(i);
            System.out.println(i+". "+hamburger.getName()+"\t\tRs."+hamburger.getPrice());
        }
        Hamburger hamburger=new Hamburger("","",0);
        System.out.println("Extra Toppings:");
        System.out.println("1. Lettuce\t\tRs."+hamburger.extraToppings("lettuce"));
        System.out.println("2. Tomato\t\tRs."+hamburger.extraToppings("tomato"));
        System.out.println("3. Carrot\t\tRs."+hamburger.extraToppings("carrot"));
        System.out.println("4. Cheese\t\tRs."+hamburger.extraToppings("cheese"));
        System.out.println("5. Meat  \t\tRs."+hamburger.extraToppings("meat"));
        System.out.println("6. Onion\t\tRs."+hamburger.extraToppings("onion"));
        System.out.println("7. Capsicum\t\tRs."+hamburger.extraToppings("capsicum"));
    }
    public static Hamburger burgerType(int x){
        switch(x){
            case 1: return new HealthyBurger();
            case 2: return new DeluxeBurger();
            case 3: return new VegBurger();
            default: return null;
        }
    }
}

class Hamburger{
    private String name,breadRollType;
    private double price;
    //private boolean meat;

    public Hamburger(String name, String breadRollType, double price) {
        this.name = name;
        this.breadRollType = breadRollType;
        this.price = price;
        //this.meat = meat;
    }

    public String getName() {
        return name;
    }

    public String getBreadRollType() {
        return breadRollType;
    }

    public double getPrice() {
        return price;
    }

    public double extraToppings(String toppingName){
        if(toppingName.equalsIgnoreCase("lettuce")) return 39.00;
        if(toppingName.equalsIgnoreCase("tomato")) return 29.00;
        if(toppingName.equalsIgnoreCase("carrot")) return 29.00;
        if(toppingName.equalsIgnoreCase("cheese")) return 59.00;
        if(toppingName.equalsIgnoreCase("meat")) return 129.00;
        if(toppingName.equalsIgnoreCase("onion")) return 49.00;
        if(toppingName.equalsIgnoreCase("capsicum")) return 49.00;
        else return 0.00;
    }
    public double chips(){
        return 50;
    }
    public double  drink(){
        return 40;
    }
}

class HealthyBurger extends Hamburger{
    public HealthyBurger() {
        super("Healthy Burger","Bread Roll 1",99.0);
    }
}

class DeluxeBurger extends Hamburger{
    public DeluxeBurger() {
        super("Deluxe Burger","Bread Roll 2",199.0);
    }
    public double chips(){
        return 50;
    }
    public double  drink(){
        return 40;
    }
}

class VegBurger extends Hamburger{
    public VegBurger() {
        super("Veg Burger","Bread Roll 3",149.00);
    }
}
