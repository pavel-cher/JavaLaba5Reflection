package Reflection.Ref;

import java.io.IOException;

import somePack.SomeBean;

public class App {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException, NullPointerException {
        try {
            SomeBean sb = (new Injector()).inject(new SomeBean());

            sb.foo();
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
            System.out.println("Exception");
        };
        }
    }
