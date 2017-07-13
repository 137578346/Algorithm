/**
 * Created by 编程只服JAVA on 2017.07.13.
 */
var arr = [1,43,23,56,12,12];

function selectSort(Array)
{
    // 临时变量，用于交换
    var temp = 0;
    var minIndex = 0;

    // 要注意一点，当要排序 N 个数，已经经过 N-1 次遍历后，已经是有序数列
    for(var i = 0; i < Array.length - 1; ++i)
    {
        // 用来保存最小值得索引
        minIndex = i;

        // 寻找第i个小的数值
        for(var j = i + 1; j < Array.length; ++j)
        {
            if (Array[j] < Array[minIndex])
            {
                minIndex = j;
            }
        }

        //可以加一个判断minIndex != i,或许可以提升一点效率，不加也可以
        // 将找到的第i个小的数值放在第i个位置上
        if (minIndex != i)
        {
            temp = Array[minIndex];
            Array[minIndex] = Array[i];
            Array[i] = temp;
        }
    }
}

selectSort(arr);
console.info(arr);