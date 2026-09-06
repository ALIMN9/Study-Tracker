public class Subject{
    private String name;
    private String id;

    public Subject (String name,String id){
        this.name=name;
        this.id=id;
    }
    public String getName(){return name;}
    public String getId(){return id;}
    public void setName(String name){this.name=name;}
    public void setID(String id){this.id=id;}

}
