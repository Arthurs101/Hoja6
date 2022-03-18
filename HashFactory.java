
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/*
clase factory Que crea la instancia de MAP requerida
*/
public class HashFactory<K,V> {
    public Map getInstance(int type){
    switch(type){
        case 1 ->{
        return new Hashtable<K,V>();
        }
        case 2 ->{
        return new TreeMap<K,V>();
        }
        case 3 ->{
        return new LinkedHashMap<K,V>();
        }
    }
    return null;
    }
}
