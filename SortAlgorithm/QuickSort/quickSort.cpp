#include<iostream>
using namespace std;

int partition(int *A, int low, int high);
void swap(int *a, int low, int high);
void quickSort(int *A, int low, int high);
void print(int *A, int low, int high);

int main()
{
	int A[] = {2, 6, 3, 10, 45, 12, 5, 6, 5, 6};
	quickSort(A, 0, 9);
	print(A,0,9);
}

//end:A中最后一个元素的index，非length of A 
int partition(int *A, int low, int high)
{
	//select the last element as position, position is not index
	int position = A[high];
	//在数组最开始的前面建立空间 
	int i = low - 1;
	
	for(int j = low; j < high; j++)
	{
		if(A[j] <= position)
		{
			++i;
			swap(A, j, i); 
		}
	}
	
	swap(A, i+1, high);
	return i+1;
}

void quickSort(int *A, int low, int high)
{
	int q;
	if(low < high)
	{
		q = partition(A,low,high);
		quickSort(A, low, q - 1);
		quickSort(A, q + 1, high);
	}
}

void swap(int *a, int low, int high)
{
	int temp = a[low];
	a[low] = a[high];
	a[high] = temp;
}

void print(int *A, int low, int high)
{
	for(int i = low; i <= high; i++)
		cout<<A[i]<<"、"; 
}
