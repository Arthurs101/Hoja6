
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.InputMismatchException;

import java.util.Map;

import java.util.Scanner;

import java.util.stream.Stream;


/*
Clase main
*/
public class Main {
    public static void main(String[] args) {
        /*
        Leer el archivo de datos
        */
        //String del path usado para testing en mi pc
        //String path = "D:\\Universidad\\SEMESTRE 3\\Algoritmos y Datos\\Hoja6\\ListadoProducto.txt"; 
        HashMap data = new HashMap<String,String>();
        Scanner scan = new Scanner(System.in);
        HashFactory fabric = new HashFactory<String,String>();
        ShoppingCart user; //coleccion del usuario
        System.out.println("Ingrese la ubicacion del archivo ListadoProducto.txt");
        String path = scan.nextLine();
        
        try{
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String line;
            while((line = bf.readLine()) != null){
                String[] linedata = line.split(":");
                if(linedata.length == 2 ){
                data.put(linedata[1].toLowerCase(), linedata[0]);
                } 
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error\t"+path+"\tdoen't exist");
            System.exit(0);
        }
        catch(IOException ie){
            System.out.println("Operacion interrumpida por interferencia");
            System.exit(0);
        }
        /*
        Obtener el tipo de implementacion que quiere
        */
        System.out.println("Ingrese el tipo de implementacion a usar");
        System.out.println("1. Hashtable");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        String option = scan.nextLine();
        if(fabric.getInstance(Integer.valueOf(option)) == null){
            System.out.println("Opcion incorrecta, error");
            System.exit(0);
        }
        user = new ShoppingCart(fabric.getInstance(Integer.valueOf(option)));
        scan = new Scanner(System.in);
        while(true){
            System.out.println("1. Agregar productos al carrito");
            System.out.println("2. Buscar categoria de un producto");
            System.out.println("3. Mostrar detalle del carrito");
            System.out.println("4. Mostrar detalle del carrito ordenado por categoria");
            System.out.println("5. Mostrar inventario de tienda");
            System.out.println("6. Mostrar inventario de tienda por tipo");
            System.out.println("7. Salir");
            try{
                int opt = scan.nextInt();
                switch(opt){
                    case 1 ->{
                        System.out.println("Ingrese el nombre del producto a comprar");
                        Scanner scantemp = new Scanner(System.in);
                        String product = scantemp.nextLine();
                        if(data.containsKey(product.toLowerCase())){//ver si existe el producto
                            user.buy(product.toLowerCase(), (String) data.get(product));
                        }else{
                            System.out.println("Producto inexistente");
                        }
                    }
                    case 2 ->{
                        System.out.println("Ingrese el nombre del producto");
                        Scanner scantemp = new Scanner(System.in);
                        String product = scantemp.nextLine();
                        if(data.containsKey(product.toLowerCase())){//ver si existe el producto
                            System.out.println("su producto buscado es de categoria " + data.get(product.toLowerCase()));
                        }else{
                            System.out.println("Producto inexistente");
                        }
                    }
                    case 3 ->{
                        user.showproducts();
                    }
                    case 4 ->{
                        user.showSorted();
                    }
                    case 5 ->{
                        if(!data.isEmpty()){
                        data.forEach((K,V) -> {System.out.println(V + "||" + K);} );
                        }

                    }
                    case 6 ->{
                        if(!data.isEmpty()){
                        Stream<Map.Entry<String, String>> sorted = data.entrySet().stream().sorted(Map.Entry.comparingByValue());
                        sorted.forEach(System.out::println);
                        }
                    }
                    case 7 ->{
                        System.exit(0);
                    }
                }
            }catch(InputMismatchException IE){
                    System.out.println("Entrada no valida");
            }
            catch(Exception W){
                System.out.println("Error inesperado");
            }
            
        }
        
    }
}
