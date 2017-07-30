#include<iostream>
using namespace std;

void merge_sort(int a[], int p, int r);
void merge(int a[], int p, int q, int r);

int main()
{
	int a[] = {2, 4, 5, 7, 1, 2, 3, 6};
	merge_sort(a, 0, 7);
	
	for(int i = 0; i < 8; ++i)
	{
		cout<<a[i]<<" ";
	}
}

void merge_sort(int a[], int p, int r)
{
	int q = 0;
	if(p < r)
	{
		q = (p + r) / 2;
		merge_sort(a, p, q);
		merge_sort(a, q + 1, r);
		merge(a, p, q, r);
	}
}

void merge(int a[], int p, int q, int r)//p, q, r all are Array's index
{
	int l1 = q - p + 1;//有序数组的长度 
	int l2 = r - q;//有序数组的长度
	
	//申请新数组
	int left[l1]; 
	int right[l2]; 
	
	//为申请的新数组赋值 
	for(int i = 0; i < l1; ++i)
	{
		left[i] = a[p + i];
	}
	for(int j = 0; j < l2; ++j)
	{
		right[j] = a[q + 1 + j];
	}
	
	//merge:the most important area
	int i = 0, j = 0;
	//先完成其中一个数组到新有序数组的转移 
	while(i < l1 && j < l2)
	{
		if(left[i] <= right[j])
		{
			a[p++] = left[i++];
		}else{
			a[p++] = right[j++];
		}
	}
	
	//将有剩下的数组，一次性转移到新数组 
	while(i < l1)
	{
		a[p++] = left[i++];
	}
	
	while(j < l2)
	{
		a[p++] = right[j++];
	}
}
