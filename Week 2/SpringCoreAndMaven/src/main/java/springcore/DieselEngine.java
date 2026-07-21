package springcore;

// another implementation of Engine, used to show we can switch engines easily
public class DieselEngine implements Engine {

    public String startEngine() {
        return "Diesel engine started";
    }

}
