package interview.philosopherquestion;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 哲学家问题，每一个都有自己的筷子，同时需要获取别人的筷子吃饭：
 *  1.获得筷子，吃饭1秒，然后赶紧放下筷子，思考3秒。
 *  2.否则思考。
 */
public class PhilosopherByMe extends Thread{
    //定义五个筷子：1表示可以使用，可以取筷子eating，0表示不可以使用,正在eating。。。
    private final static int[] ticks = {1, 1, 1, 1, 1};
    private final int id;//不可变，线程安全
    private int otherId;//只会在同步块中修改，线程安全

    /**
     * 每一个哲学家对应一个筷子
     * @param id
     */
    PhilosopherByMe(int id){
        this.id = id;
    }

    public void eating(){
        //锁住所有对象实例
        synchronized (PhilosopherByMe.class){
            if(ticks[id] == 1){
                for(int i = 0; i < 5; ++i){
                    if(i != id){
                        //可以获得另外的筷子，eating！！！
                        if (ticks[i] == 1){
                            otherId = i;
                            ticks[i] = 0;
                            ticks[id] = 0;
                            //拿到铐子后，吃饭一秒。保持占有筷子，不能被其他sb拿走
                            System.out.println("哲学家" + id + "正在拿着" + id + " 和 " + otherId +" 号筷子吃饭！！！");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //吃完饭，溜了溜了。
                            return;
                        }
                    }
                }
            }
        }
    }

    public void thinking(){
        synchronized (PhilosopherByMe.class){
            //放下自己的筷子和其他人的筷子
            if (ticks[id] == 0 && ticks[otherId] == 0){
                ticks[id] = 1;
                ticks[otherId] = 1;
            }
        }

        //放下筷子后，思考3秒
        System.out.println("哲学家" + id + "正在思考");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            eating();
            thinking();
        }
    }

    public static void main(String[] args) {
        //5个哲学家，5个筷子
        ExecutorService executors = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; ++i){
            executors.submit(new PhilosopherByMe(i));
        }
    }
}
