/**
 * Created by 编程只服JAVA on 2017.07.13.
 */
var arr = [1,43,23,56,12,12];

function bubbleSort(Array)
{
    //for Array[0, high - 1]
    for(var i = 0; i < Array.length - 1; ++i)
    {

        //优化
        var flag = true;
        //for Array[high, i + 1] down to, 因为下面Array[j] < Array[j - 1]，所以j >= i + 1;
        //将小的元素向前冒
        for(var j = Array.length - 1; j >= i + 1; --j)
        {
            if (Array[j] < Array[j - 1])
            {
                var temp = Array[j];
                Array[j] = Array[j - 1];
                Array[j - 1] = temp;
                flag = false;
            }
        }

        if (flag) break;
    }
}

bubbleSort(arr);
console.info(arr);