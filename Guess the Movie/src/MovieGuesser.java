import java.util.Scanner;
import java.io.File;

public class MovieGuesser {

    public static String getMovie(int totalMovies, Scanner someFile){
        int randNum = (int) (Math.random() * totalMovies);
        for(int i =0 ; i < randNum; i++){
            someFile.nextLine();
        }
        return someFile.nextLine();
    }

    public static String buildBlank(String movie){
        String blank = "";
        int movieLen = movie.length();
        for (int i = 0; i < movieLen; i++) {
            char letter = movie.charAt(i); // current character evaluated is assigned to letter
            if (letter == ' ') { // if letter is a " ",
                blank += " "; // add a space to blank String
            } else { // for everything else
                blank += "_"; // add an "_"
            }
        }
        return blank;
    }

    public static void main(String[] args) throws Exception { //Throw exception if file DNE
        //takes "Movies.txt" as input
        File file = new File("Movies.txt"); // File object as file for Movies.txt
        Scanner fileInput = new Scanner(file); // Scanner object to accept file as input
        Scanner input = new Scanner(System.in); // Scanner object as user input
        String movie = getMovie(25, fileInput);
        String blank = buildBlank(movie);

        int wrong = 0; // number of wrong letters user guessed
        int tries = 0; // number tries it took the user to guess the movie
        String wrongLetters = " wrong letters: "; // display the wrong letters user has guessed

        //User keeps guessing until blank matches the chosen movie
        while (!(blank.equals(movie))) {
            System.out.println("You are guessing:" + blank);
            System.out.println("You have guessed " + wrong + wrongLetters);
            System.out.print("Guess a letter:");
            String guess = input.nextLine();
            String[] b = blank.split("");
            String[] m = movie.split("");
            if (movie.contains(guess)) {
                for (int i = 0; i < movie.length(); i++) {
                    if(guess.equals(m[i])){
                        b[i]=m[i];
                    } else{
                        if(m[i].equals(" ")){
                            continue;
                        }
                    }
                }
                blank = String.join("", b);
                movie = String.join("",m);
            } else {
                wrong++;
                wrongLetters += guess + " ";
            }
            tries++;
            System.out.println("You guessed " + movie + " correctly in " + tries + " tries."); // Once movie is guessed, print ending message
        }
    }
}