package com.lgr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MultipleApplicationTests {

    @Test
    void contextLoads() {
    }
    //递归排序
    public void sort(int[] arr){
        if(arr.length<=1){
            return;
        }
        int mid = arr.length/2;
        sort(arr,0,mid-1);
        sort(arr,mid,arr.length-1);
        merge(arr,0,mid,arr.length-1);
    }
    public void sort(int[] arr,int left,int right){
        if(left>=right){
            return;
        }
        int mid = (left+right)/2;
        sort(arr,left,mid);
        sort(arr,mid+1,right);
        merge(arr,left,mid,right);
    }
    public void merge(int[] arr,int left,int mid,int right){
        int[] temp = new int[right-left+1];
        int i=left;
        int j=mid+1;
        int k=0;
        while(i<=mid&&j<=right){
            if(arr[i]<arr[j]){
                temp[k]=arr[i];
                i++;
            }
        }
    }
    //选择排序
    public void selectSort(int[] arr){
        int temp;
        for(int i=0;i<arr.length-1;i++){
            int min=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min]){
                    min=j;
                }
            }
            temp=arr[i];
            arr[i]=arr[min];
            arr[min]=temp;
        }
    }
}
