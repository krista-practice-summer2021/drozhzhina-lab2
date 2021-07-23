import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Number {

    private File fileIn;
    private File fileOut;
    private List<Integer> inputLineList;

    public Number(){
        inputLineList = new ArrayList<>();
    }

    public static void main(String[] args) {
        Number numbers = new Number();
        String pathIn = "src/main/resources/input.txt";
        String pathOut = "src/main/resources/output.txt";
        numbers.setFileIn(new File(pathIn).getAbsoluteFile());
        numbers.setFileOut(new File(pathOut).getAbsoluteFile());
        numbers.readFileLines();
        List<Integer> evenNums = numbers.filterEvenNumbers(numbers.inputLineList);
        List<Integer> evenSquareNums = numbers.squareEvenNumbers(evenNums);
        List<Integer> sortedEvenSquareNums = numbers.sortList(evenSquareNums);
        numbers.writeInFile(sortedEvenSquareNums);
    }

    public void setFileIn(File fileIn) {
        this.fileIn = fileIn;
    }

    public void setFileOut(File fileOut) {
        this.fileOut = fileOut;
    }

    public void readFileLines() {
        try (Scanner in = new Scanner(fileIn)) {
            while (in.hasNextLine()) {
                String str = in.nextLine();
                String[] nums = str.split(" ");
                for (int i = 0; i < nums.length; i++)
                    inputLineList.add(Integer.parseInt(nums[i]));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    public List<Integer> filterEvenNumbers(List<Integer> nums) { //поиск строк с заданным словом
        return nums.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
    }

    public List<Integer> squareEvenNumbers(List<Integer> evenNums){
        for (int i = 0; i < evenNums.size(); i++)
            evenNums.set(i, evenNums.get(i)*evenNums.get(i));
        return evenNums;
    }

    public List<Integer> sortList(List<Integer> nums) {
        List<Integer> sortList = new ArrayList<>(nums);
        Collections.sort(sortList);
        return sortList;
    }

    public void writeInFile(List<Integer> sortedEvenSquareNums) {
        try (FileWriter writer = new FileWriter(fileOut)) {
                try {
                    for (int i = 0; i < sortedEvenSquareNums.size(); i++)
                        writer.write(sortedEvenSquareNums.get(i) + " ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
    }
}
