#include<iostream>
#include<string>
#include<vector>
using namespace std;

/*
 ��һ���ַ�����ϳ�һ��"�ֵ�����С"���ַ��� 
 1.quickSort +  (compare(str1+str2, str2+str1))
*/

class appendSmallDirSeq{
public:
	string findSmallest(vector<string> &strs)
	{
		int n = strs.size();
		//��vector<string>��������Ŀ������� 
		quickSort(strs, 0, n - 1);
		
		string res;
		for(int i = 0; i < n; ++i)
			res += strs[i]; 
		
		return res;
	}
	
	//�����������¾�Ϊ����������� 
	void quickSort(vector<string> &strs, int low, int high)
	{
		int q;
		if(low < high)
		{
			q = parition(strs, low, high);
			quickSort(strs, low, q-1);
			quickSort(strs, q+1, high); 			
		}
	}
	
	int parition(vector<string> &strs, int low, int high)
	{
		string position = strs[high];
		int i = low - 1;
		
		for(int j = low; j < high; j++)
		{
			if(compare(strs[j], position))
			{
				++i;
				swap(strs,j,i);
			}
		}
		
		//exchange a[i + 1] with posttion's index
		swap(strs, i + 1, high);
		return i+1;
	}
	
	int swap(vector<string> &strs, int low, int high)
	{
		string str(strs[low]);
		strs[low] = strs[high];
		strs[high] = str;
	}
	
	//������ȷ�Ĺؼ�����ȷ�ȽϷ��ϱ�����ַ�����С 
	bool compare(string str1, string str2) 
	{
		string temp1 = str1 + str2;
		string temp2 = str2 + str1;
		
		if(temp1 <= temp2)
			return true;
		else
			return false;
	}
};

int main()
{
	string a("abc"),b("de"),c("cab");
	vector<string> vector;
	vector.push_back(a);
	vector.push_back(b);
	vector.push_back(c);
	
	appendSmallDirSeq append;
	string res = append.findSmallest(vector);
	cout<<res;
	return 0;
}
