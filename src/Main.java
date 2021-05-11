import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = input.readLine().split(" ");
        int locations = Integer.parseInt(tokens[0]);
        int connections = Integer.parseInt(tokens[1]);

        Legionellosis legionellosis = new Legionellosis(locations);

        for (int i = 0; i < connections; i++) {
            tokens = input.readLine().split(" ");
            int l1 = Integer.parseInt(tokens[0]);
            int l2 = Integer.parseInt(tokens[1]);
            legionellosis.addConnection(l1, l2);
        }

        int sickPeople = Integer.parseInt(input.readLine());
        legionellosis.setSickPeople(sickPeople);

        for (int i = 0; i < sickPeople; i++) {
            tokens = input.readLine().split(" ");
            int h = Integer.parseInt(tokens[0]);
            int d = Integer.parseInt(tokens[1]);
            legionellosis.addInterview(h, d);
        }

        input.close();

        System.out.println(legionellosis.solution());
    }
}
