import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

  private static final String A = "AAAA";
  private static final String C = "CCCC";
  private static final String G = "GGGG";
  private static final String T = "TTTT";

  public static void main(final String[] args) {

    // String dna[] = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" }; original
    String dna[] = { "ATGCGA", "CATTGC", "TTATGT", "AGAATG", "CCCCTA", "TCACTG" };
    int dnaLength = dna.length;

    int diagonalMaxIndex = getDiagIndex(dnaLength);

    char[][] matrix = new char[dnaLength][dnaLength];

    for (int i = 0; i < dna.length; i++) {
      matrix[i] = dna[i].toCharArray();
    }

    List<String> rows = new ArrayList<>(new ArrayList<>(Arrays.asList(dna)));

    String found  = rows.stream().filter(str -> str.contains(G)).findAny().get();
    System.out.println(found);

    List<String> columns = getColumns(matrix)
        .stream()
        .map(Test::toString)
        .collect(Collectors.toList());

    diagonalSearchEngine(matrix, "AAAA".toCharArray());
  }

  private static int getDiagIndex(final int length) {
    return length - (length % 4) - 2;
  }

  private static List<char[]> getColumns(final char[][] matrix) {
    int val = matrix.length - 1;
    return new ArrayList<>(Arrays.asList(reverseArray(matrix)).subList(0, val + 1));
  }

  private static char[][] reverseArray(final char[][] matrix) {
    int matrixLength = matrix.length;
    char[][] reversed = new char[matrixLength][matrixLength];
    for (int i = 0; i < matrixLength; i++) {
      for (int j = 0; j < matrixLength; j++) {
        reversed[i][j] = matrix[j][i];
      }
    }
    return reversed;
  }

  private static String toString(final char[] charArray) {
    return new String(charArray);
  }

  private static void diagonalSearchEngine(final char[][] matrix, final char[] searchStr) {
    boolean strFound;
    int matrixLength = matrix.length;
    int stringLength = searchStr.length;
    int maxBuckets = matrixLength - stringLength;

    for (int row = 0; row < maxBuckets; row++) {
      for (int col = 0; col < maxBuckets; col++) {
        strFound = true; // consider the string found
        for (int k = 0; k < stringLength; k++) {
          if (searchStr[k] != matrix[row + k][col + k]) {
            strFound = false;
            break;
          }
        }
        if (strFound) {
          System.out.println("FOUND*****");
        }
      }
    }
  }
}