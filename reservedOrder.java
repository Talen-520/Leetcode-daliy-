public static int[] reservedOrder(int array[]){

    int[] reservedArray = new int[array.length];
    int c = 0;
    for(int i = array.length; i>0;i--){
        reservedArray[c] = array[i-1];
        c++;
        }
    return reservedArray;
    }
