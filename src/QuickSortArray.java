public class QuickSortArray {
    private int index;
    private Url[] urlList;
    private boolean isFull;
    private boolean isEmpty;

    public QuickSortArray(int memorySize) {
        this.index = 0;
        this.isFull = false;
        this.isEmpty = true;
        this.urlList = new Url[memorySize];
    }

    public void add(Url data) {
        if (!this.isFull) {
            this.isEmpty = false;
            this.urlList[index] = data;
            this.index++;
            if (index == urlList.length) {
                this.isFull = true;
            }
        }
    }

    public boolean isFull() {
        return this.isFull;
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }

    public Url[] getUrls() {
        order();
        return this.urlList;
    }

    private void change(Url[] array, int item1, int item2) {
        Url aux = array[item1];
        array[item1] = array[item2];
        array[item2] = aux;
    }

    private void quickSort(Url[] array, int start, int end) {
        if (start < end) {
            int mid = calculateMid(array, start, end);
            quickSort(array, start, (mid - 1));
            quickSort(array, (mid + 1), end);
        }
    }

    private int calculateMid(Url[] array, int start, int end) {
        Url pivot = array[end];
        int index = start - 1;

        for (int j = start; j <= end - 1; j++) {
            boolean isAlphabetical = array[j].getUrl().compareTo(pivot.getUrl()) < 0;
            boolean isBigger = array[j].getNumberOfViews() > pivot.getNumberOfViews();
            boolean isEqual = array[j].getNumberOfViews() == pivot.getNumberOfViews();

            if (isBigger) {
                index++;
                change(array, index, j);
            } else if (isEqual && isAlphabetical) {
                index++;
                change(array, index, j);
            }
        }
        
        change(array, (index + 1), end);
        
        return index + 1;
    }

    private void resizeArray() {
        Url[] aux = new Url[index];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = this.urlList[i];
        }
        this.urlList = aux;
    }

    private void order() {
        if (!isFull() && !isEmpty())
            resizeArray();
        quickSort(this.urlList, 0, this.urlList.length - 1);
    }
}
