package jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * 
 * 找出字符串中出现最多的字母和次数
 * author linfujian
 * */
public class FindMax {
	public static void main(String[] args){
		String chars = "aabbccdda";
		String result = FindUtil.findMax(chars);
		System.out.println(result);
	}
}


class FindUtil {
	public static String findMax(String str){
		char[] chars = str.toCharArray();
		HashMap<Character, Integer> map = new HashMap<>();
		for(char i : chars){
			if(map.get(i)==null){
				map.put(i, 1);
			} else {
				int num =map.get(i);
				num++;
				map.put(i, num);
			}
		}
		LinkedList<Object[]> list =new LinkedList<Object[]>();
		for(Iterator<Character> iterator = map.keySet().iterator();iterator.hasNext();){
			Object[] arr = new Object[2];
			char charac = iterator.next();
			int num = map.get(charac);
			arr[0] = charac;
			arr[1] = num;
			list.add(arr);
		}

		int first = (Integer)list.get(0)[1];
		for(int j=1;j<list.size();j++){
			int second = (Integer)list.get(j)[1];
			if(first < second){
				Object[] bb = list.get(j);
				list.remove(0);
				list.add(0, bb);
			}
		}
		String ss = "出现次数最多的字母是：" + list.get(0)[0].toString() + ",出现次数是：" + list.get(0)[1].toString();
		return ss;
	}
}
