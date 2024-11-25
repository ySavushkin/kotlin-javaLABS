package org.example;

import java.sql.SQLException;

//Створити базу даних "Транспорт" із таблицями "Транспортні засоби"
//та "Маршрути". Реалізувати CRUD-операції для таблиці
//"Транспортні засоби".

public class Main {
    public static void main(String[] args) {
        TransportDAO dao = new TransportDAO();
        RoutesDAO routesDao = new RoutesDAO();

        try {
            dao.addTransport("Bus", "Mercedes", 50);
            dao.addTransport("Truck", "Volvo", 10);
            dao.addTransport("Sedan", "KIA", 6);
            dao.addTransport("Coupe", "Porsche", 2);

            System.out.println("Transport list:");
            for (String transport : dao.getAllTransport()) {
                System.out.println(transport);
            }

            dao.updateTransport(13, "Minibus", "Ford", 30);
            System.out.println("\nAfter update:");
            for (String transport : dao.getAllTransport()) {
                System.out.println(transport);
            }

            dao.deleteTransport(11);
            System.out.println("\nAfter deletion:");
            for (String transport : dao.getAllTransport()) {
                System.out.println(transport);
            }

            routesDao.addRoute("to airport", 25.5);
            routesDao.addRoute("to railway station", 15.0);

            System.out.println("\nAll Routes:");
            for (String route : routesDao.getAllRoutes()) {
                System.out.println(route);
            }

            System.out.println("\nRoute by ID:");
            System.out.println(routesDao.getRouteById(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
