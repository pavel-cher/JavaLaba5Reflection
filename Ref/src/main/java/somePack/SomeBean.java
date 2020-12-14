package somePack;

import Reflection.Ref.AutoInjectable;

public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;
    @AutoInjectable
    private AnotherInterface field2;
    public void foo(){
        field1.doSomething();
        field2.doSomething();
    }
}
