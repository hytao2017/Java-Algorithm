package Algorithm;

import org.omg.CORBA.OBJECT_NOT_EXIST;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collector;

public class Solution extends VersionControl {

    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};

    public static void main(String[] args) {

        Solution solution = new Solution();

        int[][] arr = {{2,1,1},{2,3,1},{3,4,1}};

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(4);
        TreeNode n7 = new TreeNode(4);

        n1.left = n2;
        n1.right = n3;

        n2.right = n4;

        n3.left = n5;


//        System.out.println(solution.chalkReplacer(new int[]{30,76,46,74,34,12,1,82,25,28,63,29,60,76,98,20,40,32,76,26,71},
//                346237330));
//
//        System.out.println(        solution.searchInsert(new int[] {1,3,5,6},2));
//
//        solution.rotate(new int[]{1,2,3,4,5,6,7},3);
//
//        solution.isPerfectSquare(16);
//
//        solution.lengthOfLongestSubstring("pwwkew");
//
//        int[][] image = new int[3][3];
//        image[0] = new int[]{1,1,1};
//        image[1] = new int[]{1,1,0};
//        image[2] = new int[]{1,0,1};
//
//        solution.floodFill(image,1,1,2);

//        System.out.println(solution.findKthLargest(new int[]{3,2,1,5,6,4},2));

        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2,1);
        lruCache.put(1,1);
        lruCache.put(2,3);
        lruCache.put(4,1);
        lruCache.get(1);
        lruCache.get(2);
    }


    public int mySqrt(int x) {

        if (x == 0){
            return 0;
        }

        double x0 = x, C = x;

        while (true){
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs((xi-x0)) < 1e-7){
                break;
            }
            x0 = xi;
        }

        return (int)x0;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return null;
        }

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;

    }


    public int findKthLargest(int[] nums, int k) {
        List<Integer> s1 = new ArrayList<>();
        List<Integer> s2 = new ArrayList<>();
        List<Integer> s3 = new ArrayList<>();

        int x = nums[(int) (Math.random() * nums.length)];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > x){
                s1.add(nums[i]);
            }else if (nums[i] == x){
                s2.add(nums[i]);
            }else {
                s3.add(nums[i]);
            }
        }

        if (s1.size() >= k){
            int[] newNumber = new int[s1.size()];
            for (int i = 0; i < s1.size() ; i++) {
                newNumber[i] = s1.get(i);
            }
            return findKthLargest(newNumber,k);
        }else if (s1.size() + s2.size() >= k){
            return x;
        }else {
            int[] newNumber = new int[s3.size()];
            for (int i = 0; i < s3.size() ; i++) {
                newNumber[i] = s3.get(i);
            }
            return findKthLargest(newNumber,k - (s1.size() + s2.size()));
        }

    }


    public int maxAreaOfIsland(int[][] grid) {

        int max = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1){
                    int result = searchArea(grid,i,j);
                    System.out.println(result);
                    max = Math.max(max,result);
                }
            }
        }

        return max;

    }

    public int searchArea(int[][] map,int x,int y){

        int n = map.length; int m = map[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        map[x][y] = Integer.MAX_VALUE;
        int sum = 1;
        while (queue.size() != 0){
            int[] point = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = point[0] + dx[i];
                int newY = point[1] + dy[i];
                if (newX >= 0 && newX < n && newY >= 0 && newY < m && map[newX][newY] == 1){
                    queue.add(new int[]{newX,newY});
                    map[newX][newY] = Integer.MAX_VALUE;
                    sum++;
                }
            }
        }

        return sum;
    }


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int curColor = image[sr][sc];
        if (curColor == newColor){
            return image;
        }
        image[sr][sc] = newColor;

        int n = image.length; int m = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr,sc});
        while (queue.size() != 0){
            int[] point = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = point[0] + dx[i];
                int newY = point[1] + dy[i];
                if (newX >= 0 && newX < n && newY >= 0 && newY < m && image[newX][newY] == curColor){
                    queue.add(new int[]{newX,newY});
                    image[newX][newY] = newColor;
                }
            }
        }

        return image;
    }

    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if (n > m){
            return false;
        }

        int[] ctn1 = new int[26];
        int[] ctn2 = new int[26];

        for (int i = 0; i < n; i++) {
            ctn1[s1.charAt(i) - 'a']++;
            ctn2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(ctn1,ctn2)){
            return true;
        }

        for (int i = n; i < m; i++) {
            ctn2[s2.charAt(i) - 'a']++;
            ctn2[s2.charAt(i-n) - 'a']--;

            if (Arrays.equals(ctn1,ctn2)){
                return true;
            }
        }

        return false;

    }

    public int lengthOfLongestSubstring(String s) {
        int slow = 0;
        int fast = 0;
        int max = 0;
        HashMap<Character,Integer> map = new HashMap<>();

        while (fast < s.length()){
            char c = s.charAt(fast);
            if (!map.containsKey(c)){
                map.put(c,1);
            }else if (c == s.charAt(slow)){
                slow++;
            }else {
                for (int i = slow; i < fast; i++) {
                    char cc = s.charAt(i);
                    if (cc != c){
                        map.remove(cc);
                    }else {
                        map.remove(cc);
                        slow = i + 1;
                        break;
                    }
                }
            }
            max = Math.max(max,fast - slow + 1);
            fast++;
        }

        return max;

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {


        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null){
            if (n > 0){
                fast = fast.next;
                n--;
            }else {
                slow = slow.next;
                fast = fast.next;
            }
        }

        if (n > 0){
            return head.next;
        }

        slow.next = slow.next.next;

        return head;

    }

    public ListNode middleNode(ListNode head) {

        ListNode p = head;

        int i = 1;
        while (p.next != null){
            p = p.next;
            i++;
        }

        int count = i / 2;
        while (count > 0){
            head = head.next;
            count--;
        }


        return head;

    }

    public int missingNumber(int[] nums) {

        int n = nums.length;
        int rightSum = (1 + n) * n / 2;

        int currentSum = Arrays.stream(nums).sum();

        return rightSum - currentSum;

    }

    public String reverseWords(String s) {
        String[] arr = s.split(" ");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (i != 0){
                builder.append(" ");
            }
            builder.append(reverseString(arr[i].toCharArray()));
        }

        return builder.toString();


    }

    public String reverseString(char[] s) {
        int length = s.length;

        for (int i = 0; i <= length / 2; i++) {
            char c = s[i];
            s[i] = s[length - i - 1];
            s[length - i - 1] = c;
        }


        return  String.valueOf(s);
    }

    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] answer = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            if (!map.containsKey(numbers[i])){
                map.put(target-numbers[i],i);
            }else if (map.containsKey(numbers[i])){
                answer[0] = map.get(numbers[i]) + 1;
                answer[1] = i + 1;
                return answer;
            }else if (numbers[i] > target){
                break;
            }
        }

        return answer;
    }

    public void moveZeroes(int[] nums) {

        if (nums.length == 1){
            return;
        }

        int slow = 0;
        int fast = 0;

        while (fast < nums.length){
            if (nums[fast] != 0){
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
            fast++;

        }


    }

    public boolean isPerfectSquare(int num) {
        double eps = 1e-6;
        double x = num / 2.0;
        while (Math.abs(x * x - num) > eps){
            x = (x + num / x ) / 2.0;
        }

        System.out.println(x);

        int res = (int)x;

        System.out.println(res);

        return res * res == num;

    }


    public void rotate(int[] nums, int k) {
        k = k % nums.length;

        int[] newArr = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int path = (i + k) % nums.length;
            newArr[path] = nums[i];
        }

        System.arraycopy(newArr,0,nums,0, nums.length);

    }

    public int[] sortedSquares(int[] nums) {

        int[] newArr = new int[nums.length];



        int left = 0;
        int right = nums.length - 1;
        int write = nums.length - 1;

        while (left <= right){
            if (Math.abs(nums[left]) >= Math.abs(nums[right])){
                newArr[write] = (int)Math.pow(nums[left],2);
                left++;
                write--;
            }else {
                newArr[write] = (int)Math.pow(nums[right],2);
                right--;
                write--;
            }
        }


        return newArr;
    }

    public int searchInsert(int[] nums, int target) {

        if (target < nums[0]) return 0;

        int low = 0;
        int height = nums.length - 1;
        int mid = 0;

        while (low <= height){
            mid = (low + height) / 2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] < target){
                low = mid + 1;
            }else if (nums[mid] > target){
                height = mid - 1;
            }
        }

        return low + 1;
    }

    public int distributeCandies(int[] candyType) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int n : candyType) {
            if (map.containsKey(n)) {
                int count = map.get(n);
                map.put(n, count + 1);
            } else {
                map.put(n, 1);
            }
        }

        int count = map.keySet().size();

        return Math.min(count,candyType.length / 2);

    }

    public String convertToBase7(int num) {

        StringBuilder builder = new StringBuilder();

        if (num == 0){
            return "0";
        }

        boolean flag = num < 0;

        num = Math.abs(num);

        while (num != 0){
            int temp = num % 7;
            builder.append(temp);
            num = num / 7;
        }

        if (flag) builder.append("-");

        return builder.reverse().toString();
    }


    public int balancedStringSplit(String s) {
        char c = '-';
        int count = 0;
        int nextCount = 0;
        int sum = 0;

        for (int i = 0; i < s.length() ; i++) {

            if (c == '-'){
                c = s.charAt(i);
                count = 1;
                continue;
            }


            if (s.charAt(i) == c){
                count++;
            }else {
                nextCount++;
                if (count == nextCount){
                    sum++;
                    count = 0;
                    nextCount = 0;
                    c = '-';
                }
            }

        }


        return sum;





    }

    public int chalkReplacer(int[] chalk, int k) {


        long sum = 0;
        for (int value : chalk) {
            sum = sum + value;
        }

        System.out.println(sum);
        long total = k % sum;

        for (int j = 0; j < chalk.length; j++) {
            if (chalk[j] <= total){
                total = total - chalk[j];
            }else {
                return j;
            }
        }

        return 0;

    }


    public int search(int[] nums, int target) {
        int mid;
        int low = 0;
        int height = nums.length - 1;

        while (low <= height){
            mid = (low + height) / 2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] > target){
                height = mid - 1;
            }else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }
        return nums;
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        final int INF = Integer.MIN_VALUE / 2;

        int n = graph.length;
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i],INF);
        }

        for (int i = 0; i < n ; i++) {
            int[] item = graph[i];
            for (int j = 0; j < item.length; j++) {
                map[i][item[j]] = 1;
            }
        }

        return null;



    }


    public int numRescueBoats(int[] people, int limit) {

        Arrays.sort(people);

        int start = 0; int end = people.length - 1;
        int count = 0;

        while (start <= end){
           if (people[start] + people[end] <= limit){
               start++;
           }
           end--;
           count++;
        }

        return count;

    }



    public String reverseVowels(String s) {

        char[] chars = s.toCharArray();

        int length = chars.length;
        int start = 0;
        int end = length - 1;

        while (start < end){
            while (start < length && !isVowel(chars[start])){
                start++;
            }

            while (end > 0 && !isVowel(chars[end])){
                end--;
            }

            if (start < end){
                char t = chars[start];
                chars[start] = chars[end];
                chars[end] = t;
                start++;
                end--;
            }

        }


        return new String(chars);

    }


    public boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }


    public String reverseStr(String s, int k) {

        char[] chars = s.toCharArray();
        int length = chars.length;

        int start = 0;
        int count = 0;


        if (length < k){
            reverse(chars,0,length - 1);
            return new String(chars);
        }


        for (int i = 0; i < length ; i++) {

            if (i == 79){
                System.out.println(1);
            }

            count++;

            if ((count - start) == 2 * k){
                reverse(chars,start,start + k - 1);
                start = i+1;
            }else if (count == length){
                if ((count - start) < 2 * k && (count - start) >= k ){
                    reverse(chars,start,start + k - 1);
                }else if ((count - start) < k){
                    reverse(chars,start,length - 1);
                }
            }
        }

        return new String(chars);
    }

    public void reverse(char[] chars,int start,int end){

        int length = start + (end - start) / 2;

        for (int i = start; i <= length; i++) {
            char temp = chars[i];
            chars[i] = chars[end - i + start];
            chars[end - i + start] = temp;
        }
    }

    public int compress(char[] chars) {

        char c = chars[0];
        int count = 1;

        char[] chars1 = new char[2000];
        int result = 0;

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != c){
                result =  insertChar(chars1,result,count,c);
                c = chars[i];
                count = 1;
            }else {
                count++;
            }

        }


        result = insertChar(chars1,result,count,c);

        for (int i = 0; i < result; i++) {
            chars[i] = chars1[i];
        }

        return result;

    }


    public boolean escapeGhosts(int[][] ghosts, int[] target) {

        int length = Math.abs(target[0]) + Math.abs(target[1]);

        for (int[] ghost : ghosts) {
            int l = Math.abs(target[0] - ghost[0]) + Math.abs(target[1] - ghost[1]);
            if (l < length) {
                return false;
            }
        }

        return true;
    }


    public int insertChar(char[] chars,int result,int count,char c){
        chars[result] = c;
        result++;
        if (count > 1){
            int start = result;
            while (count != 0){
                int t = count % 10;
                chars[result] = (char) (t + '0');
                result++;
                count = count / 10;
            }
            int end = result - 1;

            int length = start + (end - start) / 2;
            for (int i = start; i <= length; i++) {
                char t = chars[i];
                chars[i] = chars[end - i + start];
                chars[end - i + start] = t;
            }

        }

        return result;
    }



//    public String countOfAtoms(String formula) {
//
//        HashMap<String,Integer> map = new HashMap<>();
//        StringBuilder builder;
//        int length = formula.length();
//        int i = 0;
//
//        while (i < length){
//            char c = formula.charAt(i);
//            if (c >= 65 && c<= 90){
//                builder = new StringBuilder(c);
//                i++;
//                while (formula.charAt(i) >= 97 && formula.charAt(i) <= 122){
//                    builder.append(formula.charAt(i));
//                    i++;
//                }
//
//                while (formula.charAt(i) >= 65 && formula.charAt(i) <= 90){
//                    builder.append(formula.charAt(i));
//                    i++;
//                }
//
//                if (Character.isDigit(formula.charAt(i))){
//
//                }
//
//            }
//        }
//
//    }




    public boolean isSymmetric(TreeNode root) {

        if (root == null){
            return true;
        }

        TreeNode[] q = new TreeNode[100];

        int front = -1;
        int rear = -1;
        int last = 0;
        int level = 0;
        q[++rear] = root;

        while (front < rear){

            TreeNode node = q[++front];

            q[++rear] = node.left;
            q[++rear] = node.right;


            if (front == last){
                level++;
                if (!checkSymmetric(q, front+1, rear, level)) {
                    return false;
                }
                last = rear;
            }

        }

        return true;
    }

    public boolean checkSymmetric(TreeNode[] arr,int start,int end,int level){

//        int count = 0;
//
//        for (int i = 0; i < start; i++) {
//            if (arr[i] != null){
//                count++;
//            }
//        }
//
//
//        if (count != Math.pow(2,level) - 1){
//            return false;
//        }

        if (start < end){
            int length = start +  ((end - start) / 2 );

            for (int i = start; i <= length; i++) {
                if (arr[i].val != arr[end - i + start].val){
                    return false;
                }
            }
        }


        return true;
    }

    public boolean isHappy(int n) {

        HashMap<Integer,Integer> map = new HashMap<>();

        int t = n;

        while (true){

            double sum = 0;

            while (t != 0){
                sum = sum + Math.pow(t % 10,2);
                t = t / 10;
            }

            if (sum == 1){
                return true;
            }

            t = (int)sum;
            System.out.println(t);

            if (map.containsKey(t)){
                return false;
            }else {
                map.put(t,t);
            }
        }

    }

    public boolean isUnique(String astr) {

        int[] arr = new int[26];

        for (int i = 0; i < astr.length(); i++) {
            int t = astr.charAt(i) - 'a';
            arr[t]++;
            if (arr[t] > 1){
                return false;
            }
        }

        return true;
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < mat.length; i++) {
            int[] temp = mat[i];

            int count = 0;
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] == 1){
                    count++;
                }else {
                    break;
                }
            }

            map.put(i,count);
        }

        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet()); //将map的entryset放入list集合
        //对list进行排序，并通过Comparator传入自定义的排序规则

        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });


        int i = 0;
        int[] arr= new int[k];

        list.subList(0,k);

        for (int j = 0; j < k; j++) {
            arr[j] = list.get(j).getKey();
        }

        return arr;

    }


    public int networkDelayTime(int[][] times, int n, int k) {
        final int FNI = Integer.MAX_VALUE / 2;
        int[][] map = new int[n][n];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i],FNI);
        }

        for (int i = 0; i < times.length; i++) {
            int[] item = times[i];
            int u = item[0];
            int v = item[1];
            int w = item[2];
            map[u-1][v-1] = w;
        }

        int[] dist = new int[n];
        int[] visit = new int[n];

        Arrays.fill(dist,FNI);
        dist[k-1] = 0;

        for (int i = 0; i <n ; i++) {
            int x = -1;
            for (int y = 0; y < n; y++) {
                if ((visit[y] == 0) && (x == -1 || dist[y] < dist[x])){
                    x = y;
                }
            }
            visit[x] = 1;
            for (int y = 0; y < n; y++) {
                dist[y] = Math.min(dist[y],dist[x] + map[x][y]);
            }

        }

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == FNI ? -1 : ans;

    }


    public int findUnsortedSubarray(int[] nums) {

        boolean flag = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i-1]){
                flag = false;
                break;
            }
        }

        if (flag){
            return 0;
        }

        int[] numSorted = new int[nums.length];
        System.arraycopy(nums,0,numSorted,0,nums.length);
        Arrays.sort(numSorted);

        int left = 0;
        while (nums[left] == numSorted[left]){
            left++;
        }

        int right = nums.length - 1;
        while (nums[right] == numSorted[right]){
            right--;
        }

        return right-left+1;

    }

    public int getMaximumGenerated(int n) {
        if (n == 0){
            return 0;
        }

        if (n == 1){
            return 1;
        }

        int[] arr = new int[101];
        arr[0] = 0;
        arr[1] = 1;
        int max = arr[1];

        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0){
                arr[i] = arr[i / 2];
            }else {
                arr[i] = arr[(i + 1) / 2] + arr[(i - 1) / 2];
            }
            max = Math.max(max,arr[i]);
        }

        return max;
    }


    public int[] constructArr(int[] a) {

        if (a.length == 0){
            return a;
        }

        int[] arr1 = new int[a.length];
        arr1[0] = a[0];

        for (int i = 1; i < a.length; i++) {
            arr1[i] = arr1[i-1] * a[i];
        }

        int[] arr2 = new int[a.length];
        arr2[a.length - 1] = a[a.length - 1];

        for (int i = a.length - 2; i >= 0; i--) {
            arr2[i] = arr2[i + 1] * a[i];
        }

        int[] arr = new int[a.length];
        arr[0] = arr2[1];
        arr[a.length - 1] = arr1[a.length - 2];

        for (int i = 1; i < a.length - 1; i++) {
            arr[i] = arr1[i-1] * arr2[i+1];
        }

        return arr;

    }


}
