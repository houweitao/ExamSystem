package hou.just4fun.ExamSystem.work;

import java.util.ArrayList;
import java.util.List;

import hou.just4fun.ExamSystem.model.Number;

/**
 * @author houweitao
 * @date 2016年3月20日下午5:00:11
 */

public class MakeSentence {
	public static void main(String[] args) {
		MakeSentence ms = new MakeSentence();
		System.out.println(ms.make());
		
		System.out.println(ms.make(10, 10));
	}

	public List<String> make(int num,int limit){
		List<String> ret=new ArrayList<>();
		for(int i=0;i<num;i++)
			ret.add(make(limit));
		
		return ret;
	}
	
	public String make(int limit) {
		String str = "";
		double random = Math.random();
		int operatorNum = 0;
		if (random < 0.33)
			operatorNum = 1;
		else if (random < 0.66)
			operatorNum = 2;
		else
			operatorNum = 3;

		for (int i = 0; i <= operatorNum; i++) {
			str = str + new Number(limit, true)+" ";
			if (i != operatorNum)
				str = str + getOperator()+" ";
		}

		return str;
	}

	public String make() {
		return make(10);
	}

	char getOperator() {
		double random = Math.random();
//		System.out.println(random);
		if (random < 0.25)
			return '+';
		else if (random < 0.5)
			return '-';
		else if (random < 0.75)
			return '*';
		else
			return '/';
	}
}
