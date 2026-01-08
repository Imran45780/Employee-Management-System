package com.HIbernate;
import jdk.swing.interop.SwingInterOpUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.*;
/**
 * Hello world!
 *
 */
public class App 
{
    static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    static Scanner sc =  new Scanner(System.in);
    public static void main( String[] args )
    {
        while(true) {
            System.out.println("EMPOLOYEE MANAGEMENT SYSTEM");
            System.out.println("1 -> Add Employees");
            System.out.println(" 2 -> Get Employees");
            System.out.println("3 -> Update Employees");
            System.out.println(" 4 -> Delete Employees");
            System.out.println("other -> Exit ");

            System.out.println("Enter the choice : ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    AddEmployee();
                    break;
                case 2:
                    getEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                default:
                    System.out.println("THanks for using Employee Mangaement System.");
                    sessionFactory.close();
                    sc.close();
                    return;
            }
        }
    }


    public static void AddEmployee() {
        Session sess  = sessionFactory.openSession();
        Transaction tran = sess.beginTransaction();
        System.out.println("Enter the Employee name, salary, email");
        Employee emp = new Employee(sc.next(), sc.nextInt(), sc.next());
        sess.save(emp);
        tran.commit();
        sess.close();
    }

    public static void getEmployee() {
        Session sess  = sessionFactory.openSession();
        Transaction tran = sess.beginTransaction();
        Employee emp = new Employee();
        System.out.println("Enter the employee id: ");
        int id = sc.nextInt();
        emp = sess.get(Employee.class, id);
        if(emp != null) {
            System.out.println(emp);
        }else {
            System.out.println("employee with id doesnt exist");
        }
        tran.commit();
        sess.close();
    }

    public static void updateEmployee() {
        Session sess  = sessionFactory.openSession();
        Transaction tran = sess.beginTransaction();
        Employee emp = new Employee();
        System.out.println("Enter the id: ");
        int id = sc.nextInt();
        emp = sess.get(Employee.class, id);
        if(emp != null) {
            emp.setName(sc.next());
            emp.setSalary(sc.nextInt());
            emp.setEmail(sc.next());

            sess.persist(emp);

            System.out.println("Employee data updated...........");
        }else{
            System.out.println("Employee data doesnt exist............");
        }

        tran.commit();
        sess.close();
    }

    public static void deleteEmployee() {
        Session sess  = sessionFactory.openSession();
        Transaction tran = sess.beginTransaction();
        Employee emp = new Employee();
        System.out.println("Enter the employee id: ");
        int id = sc.nextInt();
        emp = sess.get(Employee.class, id);
        if(emp != null) {
            sess.remove(emp);
            System.out.println("Employee data deleted---------------");
        }else {
            System.out.println("employee with id doesnt exist");
        }
        tran.commit();
        sess.close();
    }
}
