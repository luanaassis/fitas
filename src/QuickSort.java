public class QuickSort {
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
			change(array, (index + 1), end);
		}

		return index + 1;
	}

	public void order(Url[] array) {
		quickSort(array, 0, array.length - 1);
	}
}
