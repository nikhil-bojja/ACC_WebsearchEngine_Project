package project;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

class keyComparatorInt implements Comparator<Object>{
	@Override
    public int compare(Object o1, Object o2) {
    	@SuppressWarnings("unchecked")
		Map.Entry<?, Integer> m1 = (Map.Entry<String, Integer>) o1;
    	@SuppressWarnings("unchecked")
		Map.Entry<?, Integer> m2 = (Map.Entry<String, Integer>) o2;
		if(m1.getValue() < m2.getValue()) return -1;
		if(m1.getValue() == m2.getValue()) return 0;
		else return 1;
    }
}

public class PageRanking {
	public static void rankFiles(Hashtable<String, Integer> fileName, int appearences) {
		ArrayList<Map.Entry<?, Integer>> my_list = new ArrayList<>(fileName.entrySet());
		Collections.sort(my_list, new keyComparatorInt());
		Collections.reverse(my_list);
		if (appearences != 0) {
			System.out.println("\nSites in ranking order:");
			for(int i = 0; i < 5; i++) {
				System.out.println("(" + i + ") " + my_list.get(i) + " times ");
			}
		} 
		
	}

}