package Service;

import entity.User;

import java.io.*;
import java.util.List;

public class FileService {
    public  void writeFile(List<User> userList){
        try {
            ObjectOutputStream writeFile = new ObjectOutputStream(new FileOutputStream("user.data"));
            writeFile.writeObject(userList);
            writeFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> readFile()  {
        ObjectInputStream readFile = null;
        try {
            readFile = new ObjectInputStream(new FileInputStream("user.data"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return (List<User>) readFile.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
