public class Plant{
    public String name;
    
    public static final int ID = 1;

    String type;

    public Plant(){
        this.name = "default";
        type = "default plant";
        System.out.println("default called");
    }

    public Plant(String name){
        this.name = name;
        type = "plant";
        System.out.println("cool");
    } 

}