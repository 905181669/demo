package com.example.suanfa.tanxin;

/**
 * @author: luozijian
 * @date: 3/16/21 17:52:14
 * @description:
 */
public class FlowerPlace {
    public static void main(String[] args) {

        int[] arr = {0,0,1,0,0};
        System.out.println(canPlaceFlowers(arr, 1));

    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {

        for(int i = 0; i < flowerbed.length; i++){

            if(n == 0){
                break;
            }
            int cur = i;
            if(flowerbed[i] == 0){
                flowerbed[i] = 1;
                n--;
            }

            for(int j = 0, next = j+1; j < flowerbed.length && next < flowerbed.length; j++){
                if(flowerbed[j] == 1 && flowerbed[next] == 1){
                    flowerbed[cur] = 0;
                    n++;
                    break;
                }
                next++;
            }

        }

        return n == 0;



    }
}
