
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShoppingCart<K> {
    private Map cart;
    private HashMap<String, Integer> amount = new HashMap<>(); //cantidad por producto
    /*
    solicita una implemetacion de la interfaz de Map
    */
    public ShoppingCart(Map cart){
        this.cart = cart;
    }
    /*
    Metodo para agregar elementos al carrito, solicita el nombre del producto y su categoria
    */
    public void buy(String item, String categoty){
    //revisar si no existe en los productos existentes, para evitar colision 
        if(cart.containsKey(item.toLowerCase())){//ya contiene el elemento
            //indice de repeticion de elemento
            int i = 1;
            String temp = item;
            temp += " No." + String.valueOf(i);
            while(cart.containsKey(temp.toLowerCase())){//ver el indice disponible
                i++;
                temp = item + " No." + String.valueOf(i);
            }
            cart.put((item +" No." + String.valueOf(i)), categoty);
            amount.put(item, i+1);
            
        } else {
            cart.put(item, categoty );
            amount.put(item, 1);
        }
    }
    
    /*
    Metodo que mestra la descripcion y cantidad de cada producto en su coleccion usando regrex 
    */
    public void showproducts(){
        amount.forEach( (K,V) -> {
            System.out.println(K + "\t" + String.valueOf(cart.get(K)) + "\t en carrito " + String.valueOf(V) + " unidades" );
        } 
        );
    }
    public void showSorted () {
    
    
    }
}

