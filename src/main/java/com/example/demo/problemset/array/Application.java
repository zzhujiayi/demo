package com.example.demo.problemset.array;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        int[][] matrix =
                {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                };
        rotate(matrix);
    }

    /**
     * 48. 旋转图像
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        //水平反转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }

        //对角反转
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 49. 字母异位词分组
     *
     * @param strs
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>(strs.length);
        for (String str : strs) {
            int[] counts = new int[26];
            for (char c : str.toCharArray()) {
                counts[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] > 0) {
                    sb.append((char) (i + 'a'));
                    sb.append(counts[i]);
                }
            }

            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        int start = 0;
        int end = nums.length - 1;
        int pos = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                pos = mid;
                break;
            }
        }

        start = pos;
        end = pos;
        while (start > 0 && nums[start - 1] == target) {
            start--;
        }

        while (end < nums.length - 1 && nums[end + 1] == target) {
            end++;
        }

        ans[0] = start;
        ans[1] = end;
        return ans;
    }

    /**
     * 31. 下一个排列
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0) {
            if (nums[i] > nums[i - 1]) {
                int m = i;
                while (m < nums.length - 1) {
                    if (nums[m + 1] > nums[i - 1]) {
                        m++;
                        continue;
                    }

                    break;
                }

                int t = nums[i - 1];
                nums[i - 1] = nums[m];
                nums[m] = t;
                swap(nums, i, nums.length - 1);
                return;
            }

            i--;
        }

        swap(nums, 0, nums.length - 1);
    }

    private static void swap(int[] nums, int start, int end) {
        while (start < end) {
            int t = nums[start];
            nums[start] = nums[end];
            nums[end] = t;

            start++;
            end--;
        }
    }

    /**
     * 448. 找到所有数组中消失的数字
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int[] counts = new int[nums.length + 1];
        for (int n : nums) {
            counts[n]++;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] == 0) {
                ans.add(i);
            }
        }

        return ans;
    }

    /**
     * 283. 移动零
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        int n = nums.length;
        while (right < n) {
            if (nums[right] != 0) {
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;

                left++;
            }

            right++;
        }
    }

    /**
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int first = 0; first < nums.length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            for (int second = first + 1; second < nums.length; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                int fourth = nums.length - 1;
                int sum2 = target - nums[first] - nums[second];
                for (int third = second + 1; third < nums.length; third++) {
                    if (third > second + 1 && nums[third] == nums[third - 1]) {
                        continue;
                    }

                    while (third < fourth && nums[third] + nums[fourth] > sum2) {
                        fourth--;
                    }

                    if (third == fourth) {
                        break;
                    }

                    if (nums[fourth] + nums[third] == sum2) {
                        List<Integer> item = new ArrayList<>();
                        item.add(nums[first]);
                        item.add(nums[second]);
                        item.add(nums[third]);
                        item.add(nums[fourth]);
                        ans.add(item);
                    }
                }
            }
        }

        return ans;
    }

    public static List<List<Integer>> fourSum1(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        fourSum_dfs(ans, new ArrayList<>(), 0, 0, nums, target, 0);
        return ans;
    }

    private static void fourSum_dfs(List<List<Integer>> ans, List<Integer> item, int cur, int startIndex, int[] nums, int target, int total) {
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }

            item.add(nums[i]);
            total += nums[i];
            if (cur < 3) {
                fourSum_dfs(ans, item, cur + 1, i + 1, nums, target, total);
            }

            if (cur == 3) {
                if (total > target) {
                    item.remove(cur);
                    break;
                }

                if (total == target) {
                    List<Integer> copy = new ArrayList<>();
                    for (Integer integer : item) {
                        copy.add(integer);
                    }

                    ans.add(copy);
                }
            }

            total -= nums[i];
            item.remove(cur);
        }
    }

    /**
     * 15. 三数之和
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int first = 0; first < nums.length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            int third = nums.length - 1;
            int target = -nums[first];
            for (int second = first + 1; second < nums.length; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }

                if (second == third) {
                    break;
                }

                if (nums[second] + nums[third] == target) {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[first]);
                    item.add(nums[second]);
                    item.add(nums[third]);
                    ans.add(item);
                }
            }
        }

        return ans;
    }

    /**
     * 371. 两整数之和
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        if (a == 0 || b == 0) {
            return a | b;
        }

        return getSum(a ^ b, (a & b) << 1);
    }

    /**
     * 1734. 解码异或后的排列
     *
     * @param encoded
     * @return
     */
    public static int[] decode(int[] encoded) {
        int total = 0;
        for (int i = 1; i <= encoded.length + 1; i++) {
            total = total ^ i;
        }

        int first = 0;
        for (int i = 1; i < encoded.length; i += 2) {
            first ^= encoded[i];
        }

        int[] ans = new int[encoded.length + 1];
        ans[0] = first ^ total;
        for (int i = 1; i < ans.length; i++) {
            ans[i] = ans[i - 1] ^ encoded[i - 1];
        }

        return ans;
    }

    /**
     * 376. 摆动序列
     * [1,17,5,10,13,15,10,5,16,8]
     *
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }

        int up = 1, down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }

        return Math.max(up, down);
    }

    /**
     * 795. 区间子数组个数
     *
     * @param A
     * @param L
     * @param R
     * @return
     */
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        return numSubarrayBoundedMax_Count(A, R) - numSubarrayBoundedMax_Count(A, L - 1);
    }

    private int numSubarrayBoundedMax_Count(int[] A, int r) {
        int ans = 0, cur = 0;
        for (int n : A) {
            cur = n <= r ? cur + 1 : 0;
            ans += cur;
        }

        return ans;
    }

    /**
     * 1013. 将数组分成和相等的三个部分
     *
     * @param arr
     * @return
     */
    public static boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }

        if (sum % 3 != 0) {
            return false;
        }

        int avg = sum / 3;
        int i = 0, j = arr.length, cur = 0;
        while (i < j) {
            cur += arr[i];
            if (cur == avg) {
                break;
            }

            i++;
        }
        if (cur != avg) {
            return false;
        }

        i++;
        avg *= 2;
        while (i < j - 1) {
            cur += arr[i];
            if (cur == avg) {
                return true;
            }
        }

        return false;
    }

    /**
     * 832. 翻转图像
     * <p>
     * 给定一个二进制矩阵A，我们想先水平翻转图像，然后反转图像并返回结果。
     * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转[1, 1, 0]的结果是[0, 1, 1]。
     * 反转图片的意思是图片中的0全部被1替换，1全部被0替换。例如，反转[0, 1, 1]的结果是[1, 0, 0]
     *
     * @param A
     * @return
     */
    public static int[][] flipAndInvertImage(int[][] A) {
        int t;
        for (int[] item : A) {
            for (int j = 0, k = item.length - 1; j <= k; ) {
                t = item[j];
                item[j] = item[k] ^ 1;
                item[k] = t ^ 1;
                j++;
                k--;
            }
        }

        return A;
    }

    /**
     * 977. 有序数组的平方
     *
     * @param nums
     * @return
     */
    public static int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0, j = nums.length - 1, pos = nums.length - 1; i <= j; ) {
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                ans[pos] = nums[i] * nums[i];
                i++;
            } else {
                ans[pos] = nums[j] * nums[j];
                j--;
            }

            pos--;
        }

        return ans;
    }

    /**
     * 主要元素
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int major = 0, cnt = 0;
        for (int n : nums) {
            if (cnt == 0) {
                major = n;
                cnt++;
            } else {
                if (major == n) {
                    cnt++;
                } else {
                    cnt++;
                }
            }
        }

        if (cnt > 0) {
            int l = 0;
            for (int n : nums) {
                if (n == major) {
                    l++;
                }
            }

            if (l > nums.length / 2) {
                return major;
            }
        }

        return -1;
    }

    /**
     * 75. 分糖果
     *
     * @param candies
     * @return
     */
    public int distributeCandies(int[] candies) {
        HashSet<Integer> set = new HashSet<>();
        for (int n : candies) {
            set.add(n);
        }

        return Math.min(set.size(), (candies.length / 2));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        for (int n : nums) {
            arrayList.add(n);
        }

        backtrack(nums.length, arrayList, ans, used, 0);
        return ans;
    }

    public static void backtrack(int n, ArrayList<Integer> output, List<List<Integer>> res, boolean[] used, int first) {
        if (n == first) {
            res.add(new ArrayList<>(output));
            return;
        }

        for (int i = first; i < n; i++) {
            if (used[i]) {
                continue;
            }

            Collections.swap(output, first, i);
            used[i] = true;
            backtrack(n, output, res, used, first + 1);
            Collections.swap(output, first, i);
        }
    }


    public static List<Boolean> prefixesDivBy5(int[] A) {
        int num = 0;
        List<Boolean> ans = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            num = num * 2 + A[i];
            num %= 5;
            ans.add(num == 0);
        }

        return ans;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        int cur = 0;
        for (int n : nums) {
            if (n == 0) {
                cur = 0;
                ans = Math.max(ans, cur);
            } else {
                cur++;
            }
        }

        ans = Math.max(ans, cur);
        return ans;
    }

    /**
     * 二进制手表，亮灯数 num
     * 小时   8 4 2 1
     * 分钟   32 16 8 4 2 1
     *
     * @param num
     * @return
     */
    public List<String> readBinaryWatch(int num) {
        List<String> ans = new LinkedList<>();
        if (num > 8) {
            return ans;
        }

        int mask;
        for (int i = 0; i < 3; i++) {
            int m = num - i;
            if (m > 5) {
                continue;
            }


        }

        return ans;
    }

    public int missingNumber(int[] nums) {
        int max = nums.length;
        int min = 0;

        int sum = (min + max) * (max + 1) / 2;
        for (int n : nums) {
            sum -= n;
        }

        return sum;
    }

    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1], nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
    }

    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i] + Math.max(i - 2 >= 0 ? ans[i - 2] : 0, i - 3 >= 0 ? ans[i - 3] : 0);
        }

        return Math.max(ans[ans.length - 1], ans[ans.length - 2]);

        //return Math.max(coreRob(nums, 0, nums.length - 1), coreRob(nums, 0, nums.length - 2));
    }

    private static int coreRob(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }

        return Math.max(nums[end] + coreRob(nums, start, end - 2), nums[end] + coreRob(nums, start, end - 3));
    }

    public static int[] productExceptSelf(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }

        int[] left = new int[nums.length];
        left[0] = 1;
        int[] right = new int[nums.length];
        right[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = left[i] * right[i];
        }

        return ans;
    }

    public static int findKthLargest(int[] nums, int k) {
        for (int i = nums.length / 2; i >= 0; i--) {
            downAjust(nums, i, nums.length);
        }

        int last = nums.length - 1;
        while (k > 1) {
            nums[0] = nums[last];
            k--;
            last--;
            downAjust(nums, 0, last);
        }

        return nums[0];
    }

    //3,2,3,1,2,4,5,5,6
    //3,2,1,5,6,4
    private static void downAjust(int[] nums, int parentIndex, int length) {
        int parentVal = nums[parentIndex];
        int childIndex = parentIndex * 2 + 1;

        while (childIndex < length) {
            if ((childIndex + 1) < length && nums[childIndex] < nums[childIndex + 1]) {
                childIndex++;
            }

            if (nums[childIndex] <= parentVal) {
                break;
            }

            nums[parentIndex] = nums[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex * 2 + 1;
        }

        nums[parentIndex] = parentVal;
    }

    public static int[] singleNumbers(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                map.remove(n);
            } else {
                map.put(n, 1);
            }
        }

        int[] ans = new int[2];
        Iterator<Integer> ite = map.keySet().iterator();
        ans[0] = ite.next();
        ans[1] = ite.next();
        return ans;
    }

    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        for (int k = 0; k < n; k++) {
            res[k] = new int[n];
        }

        int m = n - 1;
        int _n = m;
        int i = 0;
        int j = 0;
        int turns = 0;

        int num = 1;
        while (turns <= m / 2 && turns <= _n / 2) {
            res[i][j] = num++;
            if (i == turns && j < _n - turns) {
                j++;
            } else if (j == _n - turns && i < m - turns) {
                i++;
            } else if (i == m - turns && i != turns && j > turns) {
                j--;
            } else if (j == turns && i > (turns + 1) && j != _n - turns) {
                i--;
            } else {
                turns++;
                j++;
            }
        }

        return res;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }

        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int i = 0;
        int j = 0;
        int turns = 0;

        while (turns <= m / 2 && turns <= n / 2) {
            ans.add(matrix[i][j]);
            System.out.println(matrix[i][j]);
            if (i == turns && j < n - turns) {
                j++;
            } else if (j == n - turns && i < m - turns) {
                i++;
            } else if (i == m - turns && i != turns && j > turns) {
                j--;
            } else if (j == turns && i > (turns + 1) && j != n - turns) {
                i--;
            } else {
                turns++;
                j++;
            }
        }

        return ans;
    }
}
