package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Nomenal", "Nomenalov", (byte) 23);
        usi.saveUser("Hulio", "Radriges", (byte) 43);
        usi.saveUser("Olia", "Ola", (byte) 25);
        usi.saveUser("Tania", "Hoh", (byte) 27);
        usi.getAllUsers();
        usi.cleanUsersTable();
        usi.dropUsersTable();
    }
}
