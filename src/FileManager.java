import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileManager {
    public void readFile(String filePath) throws IOException {
        //FileReader odczyt pliku znak po znaku, dlatego opakowany w strumień BufferedReader
        FileReader fileReader = new FileReader(filePath);
        //BufferedReader - odczyt pliku linia po linii, udostępnia readLine
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {

            String textLine = bufferedReader.readLine();
            do {
                System.out.println(textLine);
                textLine = bufferedReader.readLine();
            } while (textLine != null);
        } finally {
            bufferedReader.close();
        }
    }


    public void writeFile(String filePath, List<String> mountList)
            throws IOException {

        FileWriter fileWriter = new FileWriter(filePath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try {
            for (String line : mountList) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } finally {
            bufferedWriter.close();
        }
    }
}