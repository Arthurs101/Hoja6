
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
            cart.put((item.toLowerCase() +" No." + String.valueOf(i)), categoty);
            amount.put(item.toLowerCase(), i+1);
            
        } else {
            cart.put(item.toLowerCase(), categoty );
            amount.put(item.toLowerCase(), 1);
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
    /*
    Ordena por categoria (Ordenado por valor) y luego imprime el listado de productos
    */
    public void showSorted () {
        SortByType bvc = new SortByType(this.cart);
        Map sorted_map = new TreeMap(bvc);
        sorted_map.forEach((K,V) -> {
            if(amount.containsKey(K)){
                System.out.println(K + "\tde categoria\t" + String.valueOf(V) + "\t en carrito " + String.valueOf(amount.get(K)) + " unidades" );
            }
        }
        );
    
    }
}
/*
Clase de ordenamiento por categoria
*/
class SortByType<K,V> implements Comparator<K>{ 
    Map<K, V> base;

    public SortByType(Map<K, V> base) {
        this.base = base;
    }
    
    @Override
    public int compare(K sub1, K sub2) {
        if(String.valueOf(base.get(sub1)).compareTo(String.valueOf(base.get(sub2))) >= 0){
            return 1;
        }else{
            return -1;
        }
    }
}



