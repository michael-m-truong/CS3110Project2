import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class CalculatorPt2 {

    static HashSet<Character> num_alphabet = new HashSet<Character>();
    static Stack<Double> num_stack = new Stack<Double>();
    static Stack<Character> op_stack = new Stack<Character>();
    public static void main(String[] args) {
        String inputStr = " ";
        Scanner input = new Scanner(System.in);
        num_alphabet.add('0');
        num_alphabet.add('1');
        num_alphabet.add('2');
        num_alphabet.add('3');
        num_alphabet.add('4');
        num_alphabet.add('5');
        num_alphabet.add('6');
        num_alphabet.add('7');
        num_alphabet.add('8');
        num_alphabet.add('9');

        while (inputStr != "q") {
            System.out.print("Enter input string to convert to decimal (press q to quit): ");
            inputStr = input.nextLine();
            if (inputStr.equals("q")) {
                break;
            }

            if (startState(inputStr)) {    
                //findSubStr(inputStr);
                System.out.println(num_stack);
                num_stack.clear();
                op_stack.clear();
            }
            else  
                System.out.println("Not a valid Java floating decimal literal");
                num_stack.clear();
                op_stack.clear();
        }
        input.close();
    }

    private static boolean startState(String str) {
        if (str.isEmpty())
            return false;
        if (!isParanthesisValid(str))
            return false; 
        char num = str.charAt(0);
        if (num_alphabet.contains(num) || num == '.' || num == '(') {
            //System.out.println("huh");
            return findSubStr(str);
        }
        return false;
    }

    public static boolean isParanthesisValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if(a == '(' || a == '[' || a == '{') stack.push(a);
            else if(stack.empty()) return false;
            else if(a == ')' && stack.pop() != '(') return false;
            else if(a == ']' && stack.pop() != '[') return false;
            else if(a == '}' && stack.pop() != '{') return false;
        }
        return stack.empty();
    }

    private static boolean findSubStr(String str) {
        String newNum = "";
        String subStr = "";
        String op = "";
        for (int i = 0; i < str.length(); i++) { 
            char num = str.charAt(i);
            System.out.println(newNum);
            subStr = underscoreValidation(newNum);
            if (num == '(') {
                op_stack.push(num);
                continue;
            }
            if (num == ')') {
                subStr = underscoreValidation(newNum);
                System.out.println(newNum + "berrrr");
                if (!newNum.isEmpty())
                num_stack.push(parseString(subStr));
                op+=num;
                findNums(op);
                newNum = "";
                
            //System.out.println(op_stack + "end");
            //continue;
            }  
            /*else if (num == ')' && str.charAt(i-1) == ')') {
                while (op_stack.peek() != '(')
                    process();
                //System.out.println(op_stack);
                op_stack.pop();
                //System.out.println(op_stack + "end");
                //continue;
            } */
            else if ((num == '+' || num == '-') && i != 0) {
                if (str.charAt(i-1) != 'E' && str.charAt(i-1) != 'e') {
                    subStr = underscoreValidation(newNum);
                    if (inputValidation(subStr) || newNum.isEmpty()) {
                        System.out.println("no e");
                        if (!newNum.isEmpty())
                        num_stack.push(parseString(subStr));
                        //newNum+=num;
                        op+=num;
                        System.out.println(newNum + "b4 sub");
                        findNums(op);
                        if (num != ')')
                        newNum = "";
                        continue;
                    }
                    else 
                        return false;
                }
                newNum+=num;
                continue;
            }
            else if (!num_alphabet.contains(num) && num != 'E' && num != 'e' && num !='d' &&
            num != 'D' && num != 'f' && num != 'F' && num != '.') {
                    subStr = underscoreValidation(newNum);
                    if (inputValidation(subStr) || newNum.isEmpty()) {
                        //if (i+1 != str.length() && num == ')' && !num_alphabet.contains(str.charAt(i+1)))
                        //num_stack.push(parseString(subStr));
                        //if (num != ')')
                        if (!newNum.isEmpty())
                        num_stack.push(parseString(subStr));
                        //newNum+=num;
                        op+=num;
                        findNums(op);
                        if (num != ')')
                        newNum = "";
                    }
                    else 
                        return false;

            }
            else
                newNum+=num;
        }
        System.out.println("end!");
        System.out.println(newNum);
        System.out.println(num_stack);
        //System.out.println(subStr);
        if (inputValidation(newNum) || newNum.isEmpty()) {
            System.out.println(newNum + "nuew");
            //if ()
            subStr = underscoreValidation(newNum);
            //if (num_stack.size() <= 1)
            if (!newNum.isEmpty())
            num_stack.add(parseString(subStr));
            System.out.println(num_stack);
            System.out.println(op_stack);
            //findNums(newNum);
            while (!op_stack.isEmpty()) {
                System.out.println(num_stack + "here");
                System.out.println(op_stack + "there");
                process();
            }
            while (num_stack.size() > 1) {
                System.out.println(num_stack);
                num_stack.pop();
            }
            return true;
        }
        return false;
    }

    private static void ifStackEmpty_Push(Character op) {
        
        System.out.println(num_stack + "not em");
        System.out.println(op_stack);
        while (!op_stack.isEmpty() && precedence(op) <= precedence(op_stack.peek())) {
            System.out.println("processing");
            process();
        }
        //if (precedence(op) >= precedence(op_stack.peek())) {
            //process();
            op_stack.push(op);
        //} 
    }
    
    private static void process() {
    System.out.println(num_stack + "proc");
    System.out.println(op_stack);
    System.out.println(op_stack.peek() + "top");
    double b = num_stack.pop();
    double a = num_stack.pop();
    //double b = num_stack.pop();
    char operation = op_stack.pop();
    System.out.println(op_stack);
    switch (operation) {
        case '+':
            num_stack.push(a+b);
            break;
        case '-':
            num_stack.push(a-b);
            break;
        case '*':
            num_stack.push(a*b);
            break;
        case '/':
            if (a == 0)
                throw new
                        UnsupportedOperationException("Cannot divide by zero");
            num_stack.push(a/b);
    }
    }
    
    private static int precedence(char op) {
    switch (op){
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
            return 2;
        case '^':
            return 3;
    }
    return -1;
    }
    
    private static boolean findNums(String str) {
    //LinkedList<Double> numbers = new LinkedList<Double>();
    boolean inParanthesis = false;
    String newNum = "";
    int startParanthesis_count = 0;
    int endParanthesis_count = 0;
    double operandParanthesis_count = 0;
    //newNum+=num
    //need to do pendas
    System.out.println("here");
        char num = str.charAt(str.length()-1);
        System.out.println(num + "huh");
        if (num == '(') {
            op_stack.push('(');
            //continue;
        }
        else if (num == ')') {
            while (op_stack.peek() != '(')
                process();
            //System.out.println(op_stack);
            op_stack.pop();
            //System.out.println(op_stack + "end");
            //continue;
        }
    
        else if (num == '*') {
            ifStackEmpty_Push('*');
            //continue;
        }
        else if (num == '/') {
            ifStackEmpty_Push('/');
            //continue;
        }
        else if (num == '+') {
            //numbers.add(parseString(newNum));
            //num_stack.push(parseString(newNum));
            //newNum = "";
            ifStackEmpty_Push('+');
            //continue;
        }
        else if (num == '-') {
            //if (newNum.equals("")) {
              //  op_stack.push('-');
                //continue;
            //}
            //if (i != 0 && str.charAt(i-1) != 'e') {
                //numbers.add(parseString(newNum));
                //num_stack.push(parseString(newNum));
                //newNum = "";
                ifStackEmpty_Push('-');
                //continue;
            //}
            //else
                //newNum+=num;
        }
        else {
            //newNum+=num;
            System.out.println("adding num" + num);
            //if (i == str.length()-1)
                //numbers.add(parseString(newNum));
                //num_stack.push(parseString(newNum));
                //System.out.println(num_stack);
               // System.out.println(op_stack);
                
    }
    //numbers.add(operandParanthesis_count);  //make sure to add this back
    /*while (!op_stack.isEmpty()) {
        System.out.println(num_stack + "here");
        System.out.println(op_stack + "there");
        process();
    }*/  //add this back later
    return true;
    }


    private static boolean underscoreValidation_(int i, String str) {   //helper function
        if (str.charAt(i) == '_') {
            return underscoreValidation_(i+1, str);
        } 
        else if (str.charAt(i) != '0' && str.charAt(i) != '1' && str.charAt(i) != '2' && str.charAt(i) != '3' && 
            str.charAt(i) != '4' && str.charAt(i) != '5' && str.charAt(i) != '6' && str.charAt(i) != '7' &&
            str.charAt(i) != '8' && str.charAt(i) != '9') {
                return false;
        }
        return true;
    }

    private static String underscoreValidation(String str) {   //STATE1
        String newStr = "";
        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);
            if (num != '_')         
                newStr+=num;
            if (num == '_') {       
                if (i == 0) {
                    newStr+=num;
                }
                else if (i == str.length()-1) {
                    newStr+=num;
                }
                else if (i != str.length()-1) {   //if '_' is not b/w 2 digits, reject
                        if (str.charAt(i-1) != '0' && str.charAt(i-1) != '1' && str.charAt(i-1) != '2' && str.charAt(i-1) != '3' && 
                            str.charAt(i-1) != '4' && str.charAt(i-1) != '5' && str.charAt(i-1) != '6' && str.charAt(i-1) != '7' &&
                            str.charAt(i-1) != '8' && str.charAt(i-1) != '9') {
                            if (str.charAt(i-1) == '_') {
                                continue;
                            }
                            else
                                newStr+=num;
                            
                        }
                        else if (!underscoreValidation_(i+1, str)) {
                            newStr+=num;
                        }

                    
                }

            }
        }
        return newStr;
    }

    private static boolean inputValidation(String str) {     //STATE2; if return false -> bad state
                                                            //if return true -> go to STATE3
        int e_count = 0;
        int dot_count = 0;
        if (str.length() == 0) {  //reject empty string
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);
            if (i == 0 && num == '.' && i == str.length()-1) {    //reject if str = '.'
                return false;
            }
            if (i == 0 && (num == 'E' || num == 'e'))  //reject if first char of string is e,E
                return false;
            if ((i == 0 && num == '-') || ( i==0 && num == '+') || ( i==0 && num == 'd')
            || ( i==0 && num == 'D') || ( i==0 && num == 'f') || ( i==0 && num == 'F')) { //reject if first char of string is the following chars
                return false;
            }
            if ((num == '+' || num == '-')) {            //reject if there is no 'e,E' before '+,-'
                if (i == str.length()-1) {
                    return false;
                }
                if (i != 0) {
                    if (str.charAt(i-1) != 'e' && str.charAt(i-1) != 'E') {
                        return false;
                    }
                }
            }

            if (i != str.length()-1) {                 
                char numNext = str.charAt(i+1);      //reject if operators are consecutive (for proj2)
                if ((num  == '-' && numNext == '-') || (num  == '+' && numNext == '+') || (num  == '.' && numNext == '.')
                || (num  == '/' && numNext == '/') || (num  == '*' && numNext == '*')) {
                    return false;
                }
            }
            if (num == 'e' || num == 'E') {          
                e_count++;
                if (i == str.length()-1)           //reject if last char of str is 'E,e'
                    return false;
                if (dot_count > 1 || e_count > 1)   //reject if more than 1 'e,E' or '.' in num
                    return false;
            }
            else if (num == '.') {
                if (e_count > dot_count)            //reject if num is an integer; only accepts floats
                    return false;
                dot_count++;
                if (dot_count > 1 || e_count > 1)     //reject if more than 1 'e,E' or '.' in num
                    return false;
            }
            else if (i == str.length()-1) {         
                if (num != '0' && num != '1' && num != '2' && num != '3' && num != '4' && num != '5' && num != '6' && num != '7' 
                && num != '8' && num != '9' && num != '.' && num != '+' && num != '-') {
                    if (num == 'f' || num == 'F' || num == 'd' || num == 'D') {  //accept suffixes
                        return true;
                    }
                    else
                        return false;
                }
            }                                         
            //reject other alphabet
            else if (num != '0' && num != '1' && num != '2' && num != '3' && num != '4' && num != '5' && num != '6' && num != '7' && num != '8' 
                && num != '9' && num != '.' && num != '+' && num != '-') 
                return false;
            if (i == str.length()-1) {
                if (dot_count == 0 && e_count == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static double parseString(String str) {    //STATE3 -> accept state
        double result = 0;
        boolean lessThanOne = false;
        boolean powerOn = false;
        double powerOf10 = 0;
        boolean powerof10_negative = false;
        int decimalPlace = 0;
        int placesOnLeft = 0;
        int powerCount = 0;
        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);
            if (num == '.' || num == 'e' || num == 'E') 
                break;
            else if (num == 'f' || num == 'F' || num == 'd' || num == 'D') 
                break;
            else
                placesOnLeft++;
        }
    
        boolean reached_E = false;
        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);
            if (num == 'E' || num == 'e') {
                reached_E = true;
            }
            else if (reached_E) {
                if (num == '-' || num == '+' || num == 'f' || num == 'F' || num == 'd' || num == 'D') {
                    continue;
                }
                else 
                    powerCount++;
            }
        }
        powerCount--;
        placesOnLeft--;

        for (int i = 0; i < str.length(); i++) {
            if (lessThanOne)
                decimalPlace++;

            char num = str.charAt(i);
            if (num == 'E' || num == 'e') {
                powerOn = true;
                if (str.charAt(i+1) == '-') 
                    powerof10_negative = true;
            }
            else if (num == '.') {
                lessThanOne = true;
            }

            /*Multiply by 10^n for e side of decimal */
            else if (num == '0' && powerOn)
                powerOf10 += Math.pow(10, powerCount--)*0;
            else if (num == '1' && powerOn)
                powerOf10 += Math.pow(10, powerCount--)*1;
            else if (num == '2' && powerOn)
                powerOf10 += Math.pow(10, powerCount--)*2;
            else if (num == '3' && powerOn)
                powerOf10 += Math.pow(10, powerCount--)*3;
            else if (num == '4' && powerOn)
                powerOf10 += Math.pow(10, powerCount--)*4;
            else if (num == '5' && powerOn)
                powerOf10 += Math.pow(10, powerCount--)*5;
            else if (num == '6' && powerOn)
                powerOf10 += Math.pow(10, powerCount--)*6;
            else if (num == '7' && powerOn)
                powerOf10 += Math.pow(10, powerCount--)*7;
            else if (num == '8' && powerOn)
                powerOf10 += Math.pow(10, powerCount--)*8;
            else if (num == '9' && powerOn)
                powerOf10 += Math.pow(10, powerCount--)*9;


            /*Multply by 10^(-n) if number on right side of decimal */
            else if (num == '0' && lessThanOne)
                result += Math.pow(10, -decimalPlace)*0;
            else if (num == '1' && lessThanOne)
                result += Math.pow(10, -decimalPlace)*1;
            else if (num == '2' && lessThanOne) 
                result += Math.pow(10, -decimalPlace)*2;
            else if (num == '3' && lessThanOne)
                result += Math.pow(10, -decimalPlace)*3;
            else if (num == '4' && lessThanOne)
                result += Math.pow(10, -decimalPlace)*4;
            else if (num == '5' && lessThanOne)
                result += Math.pow(10, -decimalPlace)*5;
            else if (num == '6' && lessThanOne)
                result += Math.pow(10, -decimalPlace)*6;
            else if (num == '7' && lessThanOne)
                result += Math.pow(10, -decimalPlace)*7;
            else if (num == '8' && lessThanOne)
                result += Math.pow(10, -decimalPlace)*8;
            else if (num == '9' && lessThanOne)
                result += Math.pow(10, -decimalPlace)*9;



            else if (num == '0')
                result += Math.pow(10, placesOnLeft--)*0;
            else if (num == '1')
                result += Math.pow(10, placesOnLeft--)*1;
            else if (num == '2')
                result += Math.pow(10, placesOnLeft--)*2;
            else if (num == '3')
                result += Math.pow(10, placesOnLeft--)*3;
            else if (num == '4')
                result += Math.pow(10, placesOnLeft--)*4;
            else if (num == '5')
                result += Math.pow(10, placesOnLeft--)*5;
            else if (num == '6')
                result += Math.pow(10, placesOnLeft--)*6;
            else if (num == '7')
                result += Math.pow(10, placesOnLeft--)*7;
            else if (num == '8')
                result += Math.pow(10, placesOnLeft--)*8;
            else if (num == '9')
                result += Math.pow(10, placesOnLeft--)*9;
            
        }
    
        if (powerof10_negative) 
            result = result * Math.pow(10, -powerOf10);
        else
            result = result * Math.pow(10, powerOf10);
        
        return result; 
    }

} 

