#include<iostream>
#include<string.h> 
using namespace std;

void reverse(char *str, int start, int end);


int main()
{
	int flag = 0;
	char str[] = "pig loves dog";
	reverse(str,0,12);
	cout<<str<<endl;
	for(int i = 0; i < strlen(str); ++i)
	{
		if(str[i] == ' ')
		{
			reverse(str, flag, i - 1);
			flag = i + 1;
		}
		
		if(i == strlen(str) - 1)
			reverse(str, flag, i);
	}	
	cout<<str<<endl;
} 

//ÄæĞòstart to endÖ®¼äµÄ×Ö·û 
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
