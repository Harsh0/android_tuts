import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.sql.rowset.spi.SyncProviderException;

import java.util.ArrayList;

import utility.*;

class Machine {
    protected String name;
    public Machine(){
        this.name = "Machine";
    }
    void start(){
        System.out.println(this.name+" started");
    }
    void stop(){
        System.out.println(this.name+" stopped");
    }
    public String toString(){
        return "I am a machine";
    }
}

class Camera extends Machine{
    public Camera(){
        this.name = "Camera";
    }
    void snap(){
        System.out.println("snap");
    }
    public String toString(){
        return "I am a camera";
    }

}

class Application {
    public static void main(String[] args) throws NullPointerException {
        File file = new File("Application.java");
        Scanner in = new Scanner(file);
        while(in.hasNextLine()){
            String line = in.nextLine();
            System.out.println(line);
        }
        in.close();
        throw new SyncProviderException();
        // ArrayList<Machine> strings = new ArrayList<Machine>();
        // strings.add(new Machine());
        // strings.add(new Machine());
        // showList(strings);

        // ArrayList<Camera> cameraList = new ArrayList<Camera>();
        // cameraList.add(new Camera());
        // cameraList.add(new Camera());
        // showList(cameraList);
    }

    // public static void showList(ArrayList<? super Camera> list){
    //     for(Object value: list){
    //         System.out.println(value);
    //         ((Camera)value).start();   
    //     }
    // }
}

class Faker extends Fish{
    public Faker(){
        this.hell = "jkk";
    }
    String FryMe1(){
        return super.FryMe();
    }
}