import java.util.List;
import java.util.Comparator;
import java.util.LinkedList;


class Food {
	private int food_time;
	private int idx;
	
	Food(int food_time, int idx) {
		this.food_time = food_time;
		this.idx = idx;
	}
	
	int getFoodTime() {
		return food_time;
	}
	
	int getIdx() {
		return idx;
	}
}

class Solution {
	Comparator<Food> compTime = new Comparator<Food>() {
		public int compare(Food f1, Food f2) {
			return f1.getFoodTime() - f2.getFoodTime();
		}
	};
	
	Comparator<Food> compIdx = new Comparator<Food>() {
		public int compare(Food f1, Food f2) {
			return f1.getIdx() - f2.getIdx();
		}
	};
	
	public int solution(int[] food_times, long k) {
		int foodCnt = food_times.length;
		List<Food> foods = new LinkedList<Food>();
		
		for(int idx = 0; idx < foodCnt; idx++)
			foods.add(new Food(food_times[idx], idx+1));
		
		foods.sort(compTime);
		
		int preTime = 0;
		int idx = 0;
		for(Food food : foods) {
			int diff = food.getFoodTime() - preTime;
			if(diff != 0) {
				long spend = diff * foodCnt;
				if(k >= spend) {
					k -= spend;
					preTime = food.getFoodTime();
				}
				else {
					k %= foodCnt;
					foods.subList(idx, food_times.length).sort(compIdx);
					return foods.get(idx + (int)k).getIdx();
				}
			}
			
			idx++;
			foodCnt--;
		}
		
		return -1;
	}
}

public class MukBang {
	public static void main(String[] args) {
		int[] food_times = {3, 1, 2};
		long k = 5;
		
		Solution s = new Solution();
		
		System.out.println(s.solution(food_times, k));
	}
}
