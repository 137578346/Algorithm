#include<iostream>
#include<string.h>
using namespace std;

void reverse(char *str, int start, int end); 

int main()
{
	char str[] = "ABCDE";
	int k = 2;//�������ƶ�λ��
	
	reverse(str,0,2);
	reverse(str,3,4);
	reverse(str,0,4);
	cout<<str; 
} 

//����start to end֮����ַ� 
void reverse(char *str, int start, int end)
{
	if(start > end) return;
	if(start < 0 || end >= strlen(str)) return;
	
	//exchange all elements between start to end
	while(start < end)
	{
		char temp = str[start];
		str[start] = str[end];
		str[end] = temp;
		
		++start;
		--end;
	}
}
