package ca.customer.services;

import java.util.*;

/**
 * Created by Vivek on 25-02-2017.
 */
public class Test {
    private static int counter=0;

    public static void main(String[] args){
      /*  int[]nums={1,4,2,4};
        int[] maxes={3,5};
        int ans[]=counts(nums,maxes);
        for(int i:ans){
            System.out.println(i);
        }*/


        //setproblem();

        int[]sales={1,2,3,3};
        int index=balancedSum(sales);
        System.out.println(index);
    }

    static int[] counts(int[] nums, int[] maxes) {
        int[] ans=new int[maxes.length];
        int position=0;
        for(int i:maxes){

            int counter=0;
            for(int j: nums){
                if(i>=j){
                    counter++;
                }
            }
            ans[position++]=counter;
        }
        return ans;
    }
    static int balancedSum(int[] sales) {
        int i=0;
        for(int s=1;s<sales.length;s++){
            int leftSum=sum(sales,0,s-1);
            int rightSum=sum(sales,s+1,sales.length-1);
            if(leftSum==rightSum){
                i=s;
                break;
            }
        }
        return i;
    }

    /**
     *
     * @param sales
     * @param sIndex: Starting Index
     * @param eIndex: Ending Index
     * @return
     */
    private static int sum(int[] sales, int sIndex, int eIndex) {
       int sum=0;
        for(int i=sIndex;i<=eIndex;i++){
            sum=sum+sales[i];
        }
        return sum;
    }


    public static void setproblem(String arg){
        Set set=new HashSet();
        set.add(1);
        set.add(2);
        set.add(9);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);
        set.add(4);
        set.add(1);
        set.add(5);
        set.add(7);


        List list = new ArrayList(set);
        Collections.sort(list);

        for(Object i:list){
            System.out.println(i);
        }






    }
}
