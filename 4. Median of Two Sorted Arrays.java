class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //create a new array to store sum of two sorted array
        //sort new array
        //if array length %2 = 1 return newArray[length/2];
        //else return newArray[length/2-1] + newArray[length/2 + 1] / 2
        int size =nums1.length + nums2.length;
        double medium = 0.00000;
        int[] newArray = new int[size];
        System.arraycopy(nums1,0,newArray,0,nums1.length);
        System.arraycopy(nums2,0,newArray,nums1.length,nums2.length);
        Arrays.sort(newArray);
        if(size % 2 ==1){return newArray[size/2];}
        if(size % 2 ==0){
            
            medium = ((newArray[(size /2)-1] + newArray[size/2])+0.0)/2 ;
            return medium;}
        
        return 0;
        
      }
    }
