import java.util.Objects;

public class Employee {
    private String name;
    private int EID;

    public Employee(String name, int EID) {
        this.name = name;
        this.EID = EID;
    }

    public String getName() {
        return name;
    }

    public int getEID() {
        return EID;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(EID);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null || getClass()!= obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return EID == employee.EID && 
                Objects.equals(name, employee.name);
    }
}
