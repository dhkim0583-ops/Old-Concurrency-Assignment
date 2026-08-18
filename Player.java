import java.util.concurrent.atomic.AtomicInteger;
/**
 * Lab10: Concurrency
 * A simple Player class which may cause race conditions in multi-thread program.
 * @author Muyuan Li
 * @version 02/28/2018
 *
 */
public class Player {
    private int x;	//x position of the player
    private AtomicInteger y;	//y position of the player
    private int hp;		//health point of the player
    public static Player readyPlayerOne = new Player(0, 0, 5000);

    public Player(int x, int y, int hp){
        this.x = x;
        this.y = new AtomicInteger(y);
        this.hp = hp;
    }

    public synchronized void printPlayer(){
        System.out.printf("x position:\t%d\ny position:\t%d\nhealth point:\t%d\n", x, y.get(), hp);
    }

    public synchronized void moveLeft() {
        x --;
    }
    public synchronized void moveRight(){
        x ++;
    }

    public void moveUp(){
        y.incrementAndGet();
    }
    public void moveDown(){
        y.decrementAndGet();
    }

    public void loseHealth(){
        synchronized(readyPlayerOne) {
            hp --;
        }
    }
    public void gainHealth(){
        synchronized(readyPlayerOne) {
            hp ++;
        }
    }

}
