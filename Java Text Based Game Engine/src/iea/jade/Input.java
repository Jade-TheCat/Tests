/** Some utilities for the TBGE
 * @author Jade Krabbe
 * @version a0.0.1
 */
package iea.jade;

import java.util.Scanner;

public class Input {
    private static Scanner scan = new Scanner(System.in);
    
    public static String getIn() {
        String out = scan.next();
        return out;
    }
}
