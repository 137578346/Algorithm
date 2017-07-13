/**
 * Created by 编程只服JAVA on 2017.07.13.
 */
var arr = [1,43,23,56,12];

function inSertSort(Array)
{
    for(var i = 1; i < Array.length; ++i)
    {
        //插入的起手位置
        var key = arr[i];
        //已排好序的部分Array[0, j]
        var j = i - 1;

        //如果插入的起手位置 < 前面排好序的部分
        while (j > -1 && Array[j] > key)
        {
            Array[j + 1] = Array[j];
            --j;
        }

        //找到合适的插入位置，因为j已经跑到了前一个位置，因此需要j + 1
        Array[j + 1] = key;
    }
}

inSertSort(arr);
console.info(arr);