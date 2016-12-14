package jsoup;

import java.util.Arrays;

public class OrderNums {
	public static void main(String[] args){
		String str = "3,2,1,2,21312,12,23,12,32,12,5,4,5";
		//System.out.println(OrderUtil.order(str));
		OrderUtil.goodOrder(str);
		
	}
}

class OrderUtil {
	public static String order(String str){
		String[] arr = str.split(",");
		for(int i =0; i<arr.length-1;i++){
			int first = Integer.parseInt(arr[i]);
			for(int j=i+1;j<arr.length;j++){
				int second = Integer.parseInt(arr[j]);
				String aa;
				if(first>second){
					aa = arr[i];
					arr[i] = arr[j];
					arr[j] = aa;
					first = Integer.parseInt(arr[i]); //此时arr[i]发生改变
				}
			}
		}
		String result ="";
		for(String i: arr){
			result +=i +" ";
		}
		
		return result;
	}
	//利用Arrays.sort()函数排序
	public static void goodOrder(String str){
		String[] arr = str.split(",");
		int[] num= new int[arr.length];
		for(int i=0;i<arr.length;i++){
			num[i] = Integer.parseInt(arr[i]);
		}
		Arrays.sort(num);
		for(int i : num){
			System.out.print(i+"  ");
		}
	}
	
}
