package Reflection.Ref;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;


public class Injector {
    private static Properties prop= new Properties (  );

    /**
     * 
     * Этот метод работает с объектом
     * Мы создаем объект класса Field, чтобы получить все объявленные поля класса
     * Затем с помощью функции getAnnotation() мы выясняем, какие поля содержат аннотации (AutoInjectable)
     * С помощью функции setAccessible() мы делаем возможной работу с этими полями
     * Затем с помощью функции getType мы получаем "class className", преобразуем его в строку и разделяем на две части
     * Затем мы выбираем имя класса
     * затем с помощью функции getProperty мы извлекаем свойство с нужным именем класса
     * затем мы помещаем нужное значение в нужное поле 
     */
    public <T> T inject(T object) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, NullPointerException {
        loading ();
        Field[] field = object.getClass ().getDeclaredFields ();
        for (Field f:field
        ) {
            if(f.getAnnotation ( AutoInjectable.class )!=null){
                f.setAccessible ( true );
                String fieldClassname=f.getType ().toString ().split ( " " )[1];
                String injectedClassName = prop.getProperty ( fieldClassname );
                try {
                    Object value = Class.forName(injectedClassName).newInstance();
                    f.set (object, value);
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Exception");
                };

            }
        }
        return object;
    }

    /**
     * Загружаем из нужного файла все свойства
     */
    private void loading() throws IOException {
        FileInputStream inputStream= new FileInputStream ( "src/main/resources/data.properties" );
        prop.load ( inputStream );

    }
}