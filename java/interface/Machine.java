class Machine implements IStartable, ITalkable{
    public void start(){
        System.out.println("Machine Started");
    }
    public void stop(){
        System.out.println("Machine Stopped");
    }
    public void Speek(){
        System.out.println("hello singhal");
    }
}
