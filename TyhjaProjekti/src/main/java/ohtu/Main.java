package ohtu;

import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class Main {
    public static void main(String[] args) throws IOException {
        String studentNr = "014417838";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2016.herokuapp.com/students/"+studentNr+"/submissions";

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);

        InputStream stream =  method.getResponseBodyAsStream();

        String bodyText = IOUtils.toString(stream);

        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );
        //System.out.println();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        printStats(subs);
    }

    private static void printStats(Submission[] subs) {
        int totalExercises = 0;
        int totalHours = 0;

        System.out.println("Opiskelijanumero: " + subs[0].getStudent_number());
        for (Submission submission : subs) {
            totalExercises += submission.getDoneCount();
            totalHours += submission.getHours();

            System.out.println("    viikko " + submission.getWeek() + ": \n" +
                                "       tehtyjä tehtäviä yhteensä: " + submission.getDoneCount() + "\n" +
                                "       aikaa kului " + submission.getHours() + " tuntia \n" +
                                "       tehdyt tehtävät:" + submission.getDoneExercises() + "\n");


        }
        System.out.println("yhteensä: " + totalExercises + " tehtävää, " + totalHours + " tuntia");
    }
}
