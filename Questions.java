public class Questions {
    public static String[] ask = new String[5]; //Stores all the questions
    public static boolean[] answers = new boolean[5]; //Stores answers to each question
    public static boolean[] noRepeat = new boolean[5]; //Marked true if a question has been asked to prevent repeats
    public static String[] explanation = new String[5]; //Explains why each answer is wrong
    
    /**
     * Adds questions to the array
     * Pre: none
     * Post: filled ask array
     */
    public void makeQuestion() {
        ask[0] = "In base 4, 2+2=10.";
        ask[1] = "When life gives you lemons, make lemonade.";
        ask[2] = "The cake is a lie.";
        ask[3] = "Moon dust is perfectly harmless.";
        ask[4] = "The Borealis is real.";
        
        answers[0] = true;
        answers[1] = false;
        answers[2] = true;
        answers[3] = false;
        answers[4] = true;
        
        explanation[0] = "Counting in base 4 goes 0, 1, 2, 3, 10, 11, etc.";
        explanation[1] = "Cave Johnson states in Portal 2 'When life gives you lemons, don't make lemonade'.";
        explanation[2] = "I can't believe you got this one wrong.";
        explanation[3] = "Moon dust is extremely toxic, Cave Johnson died after exposure to it.";
        explanation[4] = "The Borealis was found to be real in Half-Life 2: Episode 2.";
    }

}
