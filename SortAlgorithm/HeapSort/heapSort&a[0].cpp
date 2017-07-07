#include<stdio.h>

#define LEFT(i) (2*i + 1)
#define RIGHT(i) (2*i + 2)
#define SHOULD 0x7fffffff

//Ĭ�ϲ��õݹ� 
void max_heapify(int* a, int index, int length)
{
	int left,right,largest;//��¼�ľ��������±� 
	int temp;
	left = LEFT(index);
	right = RIGHT(index);
		
	//find the largest value among left and right and i	
	if(left < length && a[left] > a[index] )
		largest = left;
	else
		largest = index;
	if(right < length && a[right] > a[largest])
		largest = right;
		
	//exchange index with largest
	if(index != largest)
	{
		temp = a[index];
		a[index] = a[largest];
		a[largest] = temp;
		//recursive call the function,adjust from largest
		max_heapify(a,largest,length);
	}
} 

//����ѭ��max_heapify 
void max_heapify_loop(int *a, int index, int length)
{
	int left,right,largest,temp;
	while(1)
	{
		left = LEFT(index);
		right = RIGHT(index);
		
		//find the largest value among left and right and i	
		if(left < length && a[left] > a[index])
			largest = left;
		else
			largest = index;
			
		if(right < length && a[right] > a[largest])
			largest = right;
		//exchange index with largest
		if(index != largest)
		{
			temp = a[index];
			a[index] = a[largest];
			a[largest] = temp;
			index = largest;
		}else{
			break;
		}		
	}
}

//����һ�����飬����ת��Ϊheap���Ե����� 
void build_max_heap(int* a,int length)
{
	for(int i = length / 2; i >= 0; i--)
		max_heapify_loop(a,i,length);
}

/*
* rescurive
* only accept the max_heap or min_heap	
*/ 
void heapSort(int *a, int length)
{
	//recursive's exit 
	if(length == 1)
		return;
	
	int *maxNum = &a[0];
	//swap maxNum with a[n]
	int temp = *maxNum;
	*maxNum = a[length - 1];
	a[length - 1] = temp;
	
	//discard node n from heap decrementing	heap size
	--length;
	max_heapify_loop(a,0,length);//new root may violate max_heap, So heapify
	
	//recursive
	heapSort(a,length);
} 

//ѭ��ʵ�֣�only accept the max_heap or min_heap
void heapSort_loop(int *a, int length)
{
	while(1)
	{
		int *maxNum = &a[0];
		//swap maxNum with a[n]
		int temp = *maxNum;
		*maxNum = a[length - 1];
		a[length - 1] = temp;
		
		//discard node n from heap decrementing	heap size
		--length;
		max_heapify_loop(a,0,length);//new root may violate max_heap, So heapify
		
		if(length == 1)
			break;
	}
} 

int main()
{
	int a[10] = {5,3,17,10,84,19,6,22,9,35};
	build_max_heap(a, 10);
	heapSort_loop(a, 10);
	for(int i=0;i<10;++i)
		printf("%d ",a[i]);
	return 0;
}
