/** 
 * DatabaseType Class: interface used for dictating types that are databases
 * @author: Tyler Youk 
 */
import java.util.*;

public interface DatabaseType<T> {
  /** 
    * Methods: 
    * Comparator getComparatorByTrait(String trait) where Comparator is an interface in java.util. 
    */
  public Comparator<T> getComparatorByTrait(String trait);
  
  
  
}