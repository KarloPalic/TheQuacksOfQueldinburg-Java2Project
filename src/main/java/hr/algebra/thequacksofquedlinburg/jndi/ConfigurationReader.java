package hr.algebra.thequacksofquedlinburg.jndi;

import javax.naming.Context;
import javax.naming.NamingException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Optional;
import java.util.Properties;

public class ConfigurationReader {
    private static Hashtable<String, String> environment= new Hashtable<>();

    static {

        environment.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.fscontext.RefFSContextFactory");
        environment.put(Context.PROVIDER_URL,"file:C:/config");

    }

    public static Optional<String> getStringValue(String key){
        try (InitialDirContextCloseable context = new InitialDirContextCloseable(environment)){
            String value = searchBindings(context, key);
            return Optional.of(value);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
    public static Optional<Integer> getIntergerValue(String key){
        try (InitialDirContextCloseable context = new InitialDirContextCloseable(environment)){
            String valueString = searchBindings(context, key);
            Integer valueInterger = Integer.parseInt(valueString);

            return Optional.of(valueInterger);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
    private static String searchBindings(Context context, String key) {
        String filename = "the-quacks-of-quedlinburg-configuration.properties";

        String value = "";

        try{
            Object object = context.lookup(filename);
            Properties props = new Properties();
            props.load(new FileReader(object.toString()));

            value = props.getProperty(key);

        }catch (IOException | NamingException e){
            e.printStackTrace();
        }

        return value;
    }
}
