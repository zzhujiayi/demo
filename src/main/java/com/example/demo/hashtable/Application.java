package com.example.demo.hashtable;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

    }

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (String key : map.keySet()) {
            queue.add(key);

            if (queue.size() > k) {
                queue.poll();
            }
        }

        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        Collections.reverse(result);
        return result;
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Integer key : map.keySet()) {
            queue.add(key);

            if (queue.size() > k) {
                queue.poll();
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        Collections.reverse(result);
        return result;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        //a-z 97~122
        int[] map1 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map1[s.charAt(i) - 97]++;
        }

        for (int i = 0; i < t.length(); i++) {
            map1[t.charAt(i) - 97]--;
            if (map1[t.charAt(i) - 97] < 0) {
                return false;
            }
        }

        return true;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && (i - map.get(nums[i])) <= k) {
                return true;
            }

            map.put(nums[i], i);
        }

        return false;
    }
}
