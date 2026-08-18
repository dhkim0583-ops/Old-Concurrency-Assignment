import java.util.Random;
/**
 * Skeleton Code for task 2
 */
public class SimpleGrader implements Runnable {
    public final static int SEED = 2430;
    public static int[] correctAnswers = generateCorrectAnswers();
    public static int[][] studentAnswers = generateStudentAnswers(correctAnswers);
    /* Do not change variables above (you can still import other packages if necessary) */
    /* add or edit necessary variables here*/
    public static int grade = 0;
    public int index;


    // TODO implement the constructor with necessary parameters
    // hint: When we create threads in main method, each thread only grades its assigned question
    // Therefore, we need a variable to indicate index of the assigned question.
    public SimpleGrader(int index) {
        this.index = index;
    }


    @Override
    /**
     * run() method will be called when a thread starts.
     * in this case, the grading part should be done here
     */
    public synchronized void run() {
        // TODO acquire the correct answer from correctAnswers, and grade all students' answer to that question.
        for (int i = 0; i < 1000; i++) {
            if (studentAnswers[index][i] == correctAnswers[index]) this.grade += 10.0;
            else if (studentAnswers[index][i] == 0) continue;
            else this.grade -= 3;
        }
    }

    public static void main(String[] args){
        // TODO create ten threads, each responsible for one question (tip: make an array of threads could save you lots of work)
        // As the class implements Runnable instead of extends Thread, here is an example line of create a thread:
        // Thread t = new Thread(new SimpleGrader(...)); //the arguments should be corresponding to your constructor
        Thread question0 = new Thread(new SimpleGrader(0));
        Thread question1 = new Thread(new SimpleGrader(1));
        Thread question2 = new Thread(new SimpleGrader(2));
        Thread question3 = new Thread(new SimpleGrader(3));
        Thread question4 = new Thread(new SimpleGrader(4));
        Thread question5 = new Thread(new SimpleGrader(5));
        Thread question6 = new Thread(new SimpleGrader(6));
        Thread question7 = new Thread(new SimpleGrader(7));
        Thread question8 = new Thread(new SimpleGrader(8));
        Thread question9 = new Thread(new SimpleGrader(9));
        // TODO start all threads created
        question0.run();
        //question1.run();
        question2.run();
        question3.run();
        question4.run();
        question5.run();
        question7.run();
        question8.run();
        question9.run();
        // TODO wait for all threads to finish. If there is an InterruptedException, print out "Oops!"
        try {
            question0.join();
            question1.join();
            question2.join();
            question3.join();
            question5.join();
            question6.join();
            //question7.join();
            question8.join();
            question9.join();
        }
        catch (InterruptedException e) {
            System.out.println("Oops!");
        }

        // to get the average grade
        // you can make changes if necessary
        double average = grade / 1000.0;
        System.out.printf("The average score is %.3f\n", average);
    }

    /* Do not change lines below */
    public static int[] generateCorrectAnswers(){
        int[] answers = new int[10];
        Random r = new Random();
        for(int i = 0; i < answers.length; i ++){
            answers[i] = r.nextInt(4) + 1;
        }
        return answers;
    }

    public static int[][] generateStudentAnswers(int[] answers){
        int[][] response = new int[10][1000];
        Random r = new Random();
        for(int i = 0; i < response.length; i ++){
            int answer = answers[i];
            for(int j = 0; j < response[i].length; j++){
                if(r.nextInt(10) < 7){
                    response[i][j] = answer;
                }else if(r.nextBoolean()){
                    response[i][j] = 0;
                }else{
                    response[i][j] = r.nextInt(4) + 1;
                }
            }
        }
        return response;
    }
}
