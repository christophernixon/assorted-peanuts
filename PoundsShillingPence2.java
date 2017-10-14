/*  SELF ASSESSMENT
   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 10:  10 
   2. Did I use easy-to-understand, meaningful CONSTANT names?
       Mark out of 5:  5
    3. Did I format the CONSTANT names properly (in UPPERCASE)?
       Mark out of 5:  5
   4. Did I use easy-to-understand meaningful variable names?
       Mark out of 10:  10
   5. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 10:  10
   6. Did I indent the code appropriately?
       Mark out of 10:  10
   7. Did I read the input correctly from the user using (an) appropriate question(s)?
       Mark out of 10:  8 - I believe that the question I used is sufficient, but not very user friendly. 
   8. Did I compute the answer correctly for all cases?
       Mark out of 20: 20     
   9. Did I output the correct answer in the correct format (as shown in the examples)?
       Mark out of 10: 10 - Although I did get the program to print the answer in the correct format, I had to use an extra 
        					line of code to correct the decimal places and I suspect there could be a better way to do this,
        					perhaps using slightly different computations.
   10. How well did I complete this self-assessment?
       Mark out of 10:  9, I find it difficult to accurately asses my own work without being either too critical or too lenient.
   Total Mark out of 100 (Add all the previous marks): 97  
*/
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class PoundsShillingPence2 
{
	final static int NEWPENCECONVERSIONCONSTANT = 67;
	final static int OLDSHILLINGCONVERSIONCONSTANT = 20;
	final static int OLDPENCECONVERSIONCONSTANT = 12;
	final static double NEWPOUNDCONVERSIONCONSTANT = 0.01;
	
	public static void main(String[] args) 
	{
		//Input
		String input = JOptionPane.showInputDialog("Please enter the amount you have in the format 'x y z' refering to pounds shillings and pence respectively; ");
		Scanner ScannerForInput = new Scanner(input);
		int oldPounds = ScannerForInput.nextInt();
		int oldShillings = ScannerForInput.nextInt();
		int oldPence = ScannerForInput.nextInt();
		ScannerForInput.close();
		//Computation
		double amountOfShillings = oldPounds*OLDSHILLINGCONVERSIONCONSTANT;
		double amountOfPence = oldShillings*OLDPENCECONVERSIONCONSTANT;
		double poundsIntoPence = amountOfShillings*OLDPENCECONVERSIONCONSTANT;
		double totalOldPence = oldPence+poundsIntoPence+amountOfPence;
		double amountOfNewPence = totalOldPence*NEWPENCECONVERSIONCONSTANT;
		double finalOutput = amountOfNewPence*NEWPOUNDCONVERSIONCONSTANT;
		//Output
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		JOptionPane.showMessageDialog(null, + oldPounds + " old pound, " + oldShillings + " old shilling and " + oldPence + " old pence = £" + (numberFormat.format(finalOutput)));

	}

}
