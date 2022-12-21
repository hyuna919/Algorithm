private static int binarySearch(int input) {
    int left = 0;
    int right = list.size()-1;
    int mid = (left + right)/2;

    int value;
    while(left <= right) {
        mid = (left + right)/2;
        value = list.get(mid);

        if(value > input) right = mid - 1;
        else left = mid + 1;
    }

    return mid;
}
