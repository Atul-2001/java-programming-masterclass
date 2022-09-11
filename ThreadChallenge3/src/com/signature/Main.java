package com.signature;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Tutor tutor = new Tutor(lock);
        Student student = new Student(tutor, lock);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tutor.studyTime();
            }
        });

        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                student.handInAssignment();
            }
        });

        tutorThread.start();
        studentThread.start();
    }
}

class Tutor {
    private Student student;
    private ReentrantLock lock;

    Tutor (ReentrantLock lock) {
        this.lock = lock;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

//    public void studyTime() {
//        lock.lock();
//        try {
//            System.out.println("Tutor has arrived");
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                System.out.println(e.getMessage());
//            }
//            student.startStudy();
//            System.out.println("Tutor is studying with student");
//        } finally {
//            lock.unlock();
//        }
//    }

     public synchronized void studyTime() {
//        synchronized (this) {
            System.out.println("Tutor has arrived");
//            synchronized (student) {
                try {
                    this.wait(100);
                } catch (InterruptedException e) {

                }
                student.startStudy();
                System.out.println("Tutor is studying with student");
//            }
//        }
    }


    public void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}

class Student {

    private Tutor tutor;
    private ReentrantLock lock;

    Student(Tutor tutor, ReentrantLock lock) {
        this.tutor = tutor;
        this.lock = lock;
    }

    public void startStudy() {
        // study
        System.out.println("Student is studying");
    }

//    public void handInAssignment() {
//        lock.lock();
//        try {
//            tutor.getProgressReport();
//            System.out.println("Student handed in assignment");
//        } finally {
//            lock.unlock();
//        }
//    }

    public void handInAssignment() {
        synchronized (tutor) {
            tutor.getProgressReport();
            synchronized (this) {
                System.out.println("Student handed in assignment");
            }
        }
    }
}
