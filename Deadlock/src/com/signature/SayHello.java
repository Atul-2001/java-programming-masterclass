package com.signature;

public class SayHello {
    public static void main(String[] args) {
        PolitePerson ram = new PolitePerson("Ram");
        PolitePerson krishna = new PolitePerson("Krishna");

        new Thread(new Runnable() {
            @Override
            public void run() {
                ram.sayHello(krishna);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                krishna.sayHello(ram);
            }
        }).start();

//        ram.sayHello(krishna);
//        krishna.sayHello(ram);
    }

    static class PolitePerson {
        private final String name;

        public PolitePerson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void sayHello(PolitePerson person) {
            System.out.format("%s: %s" + " has said hello to me!%n", this.name, person.getName());
            person.sayHelloBack(this);
        }

        public synchronized void sayHelloBack(PolitePerson person) {
            System.out.format("%s: %s has said hello back to me!%n", this.name, person.getName());
        }
    }
}
