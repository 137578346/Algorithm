#include<stdio.h>

#define PARENT(i) (i/2)
#define LEFT(i) (2*i)
#define RIGHT(i) (2*i + 1)
#define SHOULD 0x7fffffff

void max_heapify(int* a, int index, int length); 
void max_heapify_loop(int *a, int index, int length);
void build_max_heap(int* a,int length);
void heapSort(int *a, int length);
void heapSort_loop(int *a, int length);

int main()
{
	int a[] = {SHOULD,5,3,17,10,84,19,6,22,9,35};
	build_max_heap(a, 10);
	heapSort_loop(a, 10);
	for(int i=1;i<11;++i)
		printf("%d ",a[i]);
	return 0;
}

//默认采用递归 
void max_heapify(int* a, int index, int length)
{
	int left,right,largest;//记录的均是数组下标 
	int temp;
	left = LEFT(index);
	right = RIGHT(index);
		
	//find the largest value among left and right and i	
	if(left <= length && a[left] > a[index] )
		largest = left;
	else
		largest = index;
	if(right <= length && a[right] > a[largest])
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

//利用循环max_heapify 
void max_heapify_loop(int *a, int index, int length)
{
	int left,right,largest,temp;
	while(1)
	{
		left = LEFT(index);
		right = RIGHT(index);
		
		//find the largest value among left and right and i	
		if(left <= length && a[left] > a[index])
			largest = left;
		else
			largest = index;
			
		if(right <= length && a[right] > a[largest])
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

//给出一个数组，将其转化为heap，自底向上 
void build_max_heap(int* a,int length)
{
	for(int i = length / 2; i > 0; i--)
		max_heapify_loop(a,i,length);
}

//only accept the max_heap or min_heap
void heapSort(int *a, int length)
{
	//recursive's exit 
	if(length == 1)
		return;
	
	int *maxNum = &a[1];
	//swap a[1] with a[n]
	int temp = *maxNum;
	*maxNum = a[length];
	a[length] = temp;
	
	//discard node n from heap decrementing	heap size
	--length;
	max_heapify_loop(a,1,length);//new root may violate max_heap, So heapify
	
	//recursive
	heapSort(a,length);
} 

//循环实现，only accept the max_heap or min_heap
void heapSort_loop(int *a, int length)
{
	while(1)
	{
		int *maxNum = &a[1];
		//swap maxNum with a[n]
		int temp = *maxNum;
		*maxNum = a[length];
		a[length] = temp;
		
		//discard node n from heap decrementing	heap size
		--length;
		max_heapify_loop(a,1,length);//new root may violate max_heap, So heapify
		
		if(length == 1)
			break;
	}
} 
