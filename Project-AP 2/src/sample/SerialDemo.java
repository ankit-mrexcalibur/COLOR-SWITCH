package sample;

import javafx.scene.control.ScrollPane;

import java.io.*;

public class SerialDemo {
    private static ScrollPane sp;
    SerialDemo(ScrollPane s){
        this.sp = s;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    save obj = new save(sp);
//    File f = new File("Obj.text");
//    FileOutputStream fos = new FileOutputStream(f);
//    ObjectOutputStream oos = new ObjectOutputStream(fos);
    File f = new File("Obj.text");
    FileOutputStream fos = new FileOutputStream(f);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(obj);
    FileInputStream fis = new FileInputStream(f);
    ObjectInputStream ois = new ObjectInputStream(fis);
    save obj1 = (save) ois.readObject();

    }
}

class save implements Serializable{
    ScrollPane s;
    save(ScrollPane s){
        this.s = s;
    }
}