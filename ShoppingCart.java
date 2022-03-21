
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
            amount.put(item.toLowerCase(), amount.get(item)+1);
            
        } else {
            cart.put(item.toLowerCase(), categoty );
            amount.put(item.toLowerCase(), 1);
        }
    }
    
    /*
    Metodo que mestra la descripcion y cantidad de cada producto en su coleccion usando regrex 
    */
    public void showproducts(){
        if(!amount.isEmpty()){
        amount.forEach( (K,V) -> {
            System.out.println(K + "\t" + String.valueOf(cart.get(K)) + "\t en carrito " + String.valueOf(V) + " unidades" );
        } 
        );
        }else{
            System.out.println("No articles");
        }
    }
    /*
    Ordena por categoria (Ordenado por valor) y luego imprime el listado de productos
    */
    public void showSorted () {
        if(!amount.isEmpty()){
        Stream<Map.Entry<String, String>> sorted = cart.entrySet().stream().sorted(Map.Entry.comparingByValue());
        LinkedHashMap<String,String> resultMAP = sorted.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        resultMAP.forEach((K,V) -> {
            if(amount.containsKey(K)){
                System.out.println(K + "\tde categoria\t" + String.valueOf(V) + "\t en carrito " + String.valueOf(amount.get(K)) + " unidades" );
            }
        }
        );
    
    
    }else{
        System.out.println("No articles");
    }
    }
}


/*
          _____                    _____             _____                    _____                    _____                   _______         
         /\    \                  /\    \           /\    \                  /\    \                  /\    \                 /::\    \        
        /::\    \                /::\    \         /::\    \                /::\____\                /::\    \               /::::\    \       
       /::::\    \              /::::\    \        \:::\    \              /:::/    /               /::::\    \             /::::::\    \      
      /::::::\    \            /::::::\    \        \:::\    \            /:::/    /               /::::::\    \           /::::::::\    \     
     /:::/\:::\    \          /:::/\:::\    \        \:::\    \          /:::/    /               /:::/\:::\    \         /:::/~~\:::\    \    
    /:::/__\:::\    \        /:::/__\:::\    \        \:::\    \        /:::/    /               /:::/__\:::\    \       /:::/    \:::\    \   
   /::::\   \:::\    \      /::::\   \:::\    \       /::::\    \      /:::/    /               /::::\   \:::\    \     /:::/    / \:::\    \  
  /::::::\   \:::\    \    /::::::\   \:::\    \     /::::::\    \    /:::/    /      _____    /::::::\   \:::\    \   /:::/____/   \:::\____\ 
 /:::/\:::\   \:::\    \  /:::/\:::\   \:::\____\   /:::/\:::\    \  /:::/____/      /\    \  /:::/\:::\   \:::\____\ |:::|    |     |:::|    |
/:::/  \:::\   \:::\____\/:::/  \:::\   \:::|    | /:::/  \:::\____\|:::|    /      /::\____\/:::/  \:::\   \:::|    ||:::|____|     |:::|    |
\::/    \:::\  /:::/    /\::/   |::::\  /:::|____|/:::/    \::/    /|:::|____\     /:::/    /\::/   |::::\  /:::|____| \:::\    \   /:::/    / 
 \/____/ \:::\/:::/    /  \/____|:::::\/:::/    //:::/    / \/____/  \:::\    \   /:::/    /  \/____|:::::\/:::/    /   \:::\    \ /:::/    /  
          \::::::/    /         |:::::::::/    //:::/    /            \:::\    \ /:::/    /         |:::::::::/    /     \:::\    /:::/    /   
           \::::/    /          |::|\::::/    //:::/    /              \:::\    /:::/    /          |::|\::::/    /       \:::\__/:::/    /    
           /:::/    /           |::| \::/____/ \::/    /                \:::\__/:::/    /           |::| \::/____/         \::::::::/    /     
          /:::/    /            |::|  ~|        \/____/                  \::::::::/    /            |::|  ~|                \::::::/    /      
         /:::/    /             |::|   |                                  \::::::/    /             |::|   |                 \::::/    /       
        /:::/    /              \::|   |                                   \::::/    /              \::|   |                  \::/____/        
        \::/    /                \:|   |                                    \::/____/                \:|   |                   ~~              
         \/____/                  \|___|                                     ~~                       \|___|                                   
                                                                                                                                               
*/

