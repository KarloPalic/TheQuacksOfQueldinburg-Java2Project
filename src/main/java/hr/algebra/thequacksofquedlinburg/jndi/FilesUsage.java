package hr.algebra.thequacksofquedlinburg.jndi;

import javax.naming.*;
import java.io.*;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Scanner;

public class FilesUsage {
    public static void main(String[] args) {

        Hashtable<String,String> environment = new Hashtable<>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.fscontext.RefFSContextFactory");
        environment.put(Context.PROVIDER_URL,"file:C:/config");

        try (InitialDirContextCloseable context = new InitialDirContextCloseable(environment)){

            listBindings(context, "", 1, 3);
            searchBindings(context);

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    private static void listBindings(Context context, String path, int level, int limit) throws NamingException {
        if(level > limit) {
            return;
        }
        NamingEnumeration<Binding> listBindings = context.listBindings(path);
        while (listBindings.hasMoreElements()) {
            Binding binding = listBindings.next();
            System.out.printf("%" + level + "s", " ");
            System.out.println(binding.getName());
        }
    }

    private static void searchBindings(Context context) {
        System.out.print("Insert filename: ");
        String filename = readFilename(System.in);

        try{
            Object object = context.lookup(filename);

            Properties props = new Properties();
            props.load(new FileReader(object.toString()));

            System.out.println("The property server.port = " + props.getProperty("server.port"));
            System.out.println("The property client.port = " + props.getProperty("client.port"));
            System.out.println("The property hostname = " + props.getProperty("hostname"));
            System.out.println("The property random.port.hint = " + props.getProperty("random.port.hint"));
            System.out.println("The property rmi.port = " + props.getProperty("rmi.port"));

        }catch (IOException | NamingException e){
            e.printStackTrace();
        }


    }

    private static String readFilename(InputStream in) {
        try (Scanner scanner = new Scanner(in)) {
            return scanner.next();
        }
    }
}
