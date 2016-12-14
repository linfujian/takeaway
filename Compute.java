package jsoup;

import java.util.ArrayList;
import java.util.List;

public class Compute {
	public static void main(String[]args){
		String text= "3*5+1-9/3+3-2";
		
		List<List> li = extract(text);
		int result = compute(li.get(0), li.get(1));
		System.out.println(result);
	}
	
	public static List extract(String list){
		List listRes = new ArrayList<>();
		for(int k=0;k<list.length();k++){
			listRes.add(list.charAt(k));
		}
		
		List nums= new ArrayList();
		List operator= new ArrayList();
		for(int i=0;i<listRes.size();i+=2){
			nums.add(Integer.parseInt((listRes.get(i)).toString()));
		}
		for(int j=1;j<listRes.size();j+=2){
			operator.add(listRes.get(j).toString());
		}
		List result = new ArrayList();
		result.add(nums);
		result.add(operator);
		return result;
		
	}
	
	public static int compute(List<Integer> list1, List<String> list2){
		for(int i=0;i<list2.size();i++){
			if(list2.get(i).equals("*")||list2.get(i).equals("/")){
				int Result = 0;
				int Num1 = list1.get(i);
				int Num2 = list1.get(i+1);
				if(list2.get(i).equals("*")){
					Result = Num1*Num2;
				}
				if(list2.get(i).equals("/")){
					Result = Num1/Num2;
				}
				
				list1.remove(i);
				list1.remove(i);
				list1.add(i, Result);
				list2.remove(i);
				if(i!=0){
					i--;
				}else{i=-1;}
			}
		}
		
		for(int i=0;i<list2.size();i++){
			if(list2.get(i).equals("+")||list2.get(i).equals("-")){
				int Result = 0;
				int Num1 = list1.get(i);
				int Num2 = list1.get(i+1);
				if(list2.get(i).equals("+")){
					Result = Num1+Num2;
				}
				if(list2.get(i).equals("-")){
					Result = Num1-Num2;
				}
				list1.remove(i);
				list1.remove(i);
				list1.add(i, Result);
				list2.remove(i);
				if(i!=0){
					i--;
				}else{i=-1;}
			}
		}
		
		return list1.get(0);
		
	}
}


