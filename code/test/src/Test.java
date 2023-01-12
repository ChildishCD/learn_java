import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(10,1,-20,40,5,-23,0);
//        Collections.sort(list);

//        System.out.println(Arrays.toString(twoSum(nums, target)));
//        System.out.println(map);
    }

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = left + (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        return -1;
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        System.out.println(hashtable.containsKey(target - nums[0]));
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                System.out.println(hashtable.containsKey(target - nums[0]));
                System.out.println(hashtable.containsKey(target - nums[i]));
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
