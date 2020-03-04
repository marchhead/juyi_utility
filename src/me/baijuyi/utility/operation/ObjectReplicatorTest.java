package me.baijuyi.utility.operation;

public class ObjectReplicatorTest {
    public static void main(String[] args) {
        Person p1 = new Person("Curry", "SF");
        Person p2 = new Person("Kobe", "FOREVER");
        System.out.println("-----------------------before copy-----------------------");
        System.out.println(p2);
        System.out.println("------------------------after copy------------------------");
        ObjectReplicator.copyFields(p1, p2);
        System.out.println(p2);

    }

}

class Person {
    private String name;
    private String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}