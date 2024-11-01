import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.Period;

/**
 * Kelas utama untuk menjalankan program yang meminta nama, jenis kelamin, dan tanggal lahir pengguna,
 * kemudian menampilkan informasi tersebut bersama dengan usia pengguna.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Metode utama yang mengelola alur program.
     * 
     * @param args argumen baris perintah yang tidak digunakan dalam program ini
     */
    public static void main(String[] args) {
        while (true) {
            try {
                String name = getInput("Nama: ");
                char gender = getGenderInput();
                LocalDate birthDate = getBirthDateInput();
                
                Period age = Period.between(birthDate, LocalDate.now());

                displayBirthDate(name, gender, age);
                
                break; 
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Menampilkan informasi nama, jenis kelamin, dan umur pengguna.
     * 
     * @param name   Nama pengguna
     * @param gender Jenis kelamin pengguna ('L' untuk laki-laki, 'P' untuk perempuan)
     * @param age    Umur pengguna dalam format Period (tahun dan bulan)
     */
    private static void displayBirthDate(String name, char gender, Period age) {
        System.out.println("Nama: " + name);
        System.out.println("Jenis Kelamin: " + (gender == 'L' ? "Laki-Laki" : "Perempuan"));
        System.out.println("Umur Anda: " + age.getYears() + " tahun " + age.getMonths() + " bulan");
    }

    /**
     * Mendapatkan input dari pengguna dengan menampilkan prompt.
     * 
     * @param prompt Pesan yang akan ditampilkan ke pengguna
     * @return String input dari pengguna
     */
    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Meminta input jenis kelamin dari pengguna ('L' untuk laki-laki, 'P' untuk perempuan).
     * 
     * @return karakter yang mewakili jenis kelamin pengguna ('L' atau 'P')
     * @throws IllegalArgumentException jika input tidak valid
     */
    private static char getGenderInput() {
        while (true) {
            String genderInput = getInput("Jenis Kelamin (L/P): ");
            genderInput = genderInput.toUpperCase();
            if (genderInput.length() == 1 && (genderInput.charAt(0) == 'L' || genderInput.charAt(0) == 'P')) {
                return genderInput.charAt(0);
            }
            System.out.println("Please enter either 'L' or 'P'");
        }
    }

    /**
     * Meminta input tanggal lahir dari pengguna dalam format yyyy-mm-dd.
     * 
     * @return LocalDate yang mewakili tanggal lahir pengguna
     * @throws DateTimeParseException jika format tanggal tidak valid
     */
    private static LocalDate getBirthDateInput() {
        while (true) {
            try {
                String dateInput = getInput("Tanggal Lahir (yyyy-mm-dd): ");
                return LocalDate.parse(dateInput, DateTimeFormatter.ISO_DATE);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter the date in the correct format (yyyy-mm-dd)");
            }
        }
    }
}
