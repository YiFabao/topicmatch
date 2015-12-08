package so.xunta.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class TreeMapTest {

	public static void main(String[] args) {
		String name="yifabao";
		class SortedKeyValue{
			String key;
			String value;
			public SortedKeyValue(String key, String value) {
				this.key = key;
				this.value = value;
			}
		}
		List<SortedKeyValue> l = new ArrayList();
		l.add(new SortedKeyValue("asdfasdf","1"));
		l.add(new SortedKeyValue("askdjedads","2"));
		l.add(new SortedKeyValue("ffkjskjwfd","3"));
		l.add(new SortedKeyValue("ekdjfkjaef","4"));
		l.add(new SortedKeyValue("skfjiekjsfa","5"));
		l.add(new SortedKeyValue("kejfickjef","6"));
		
		for(SortedKeyValue s:l)
		{
			System.out.println(s.key+"  "+s.value);
		}
		
	}

}
