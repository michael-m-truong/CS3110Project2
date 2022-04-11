import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class CalculatorPt2 {

    static LinkedList<Double> numbers = new LinkedList<Double>();
    static LinkedList<Character> operands = new LinkedList<Character>();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter input string to convert to decimal: ");

        String inputStr = input.nextLine();
        inputStr = removeUnderscore(inputStr);
        

        if (inputValidation(inputStr)) {
            System.out.println(findOperands(inputStr));
            System.out.println(findNums(inputStr));
            double paranthesis_operands = sort_PEMDAS();
            System.out.println("Answer: " + calculate(paranthesis_operands));
        }
        else 
            System.out.println("Not a valid Java floating decimal literal");
            //-3e-2+3*(5-2)
    }

    private static double calculate(double paranthesis_operands) {
        double paranthesis_operands_copy = paranthesis_operands;
        while (paranthesis_operands != 0) {
            char sign = operands.removeFirst();
            if (sign == '*') {
                numbers.addLast(numbers.removeFirst() * numbers.removeFirst());
                paranthesis_operands--;
            }
            else if (sign == '/') {
                numbers.addLast(numbers.removeFirst() / numbers.removeFirst());
                paranthesis_operands--;
            }
            else if (sign == '+') {
                numbers.addLast(numbers.removeFirst() + numbers.removeFirst());
                paranthesis_operands--;
            }
            else if (sign == '-') {
                numbers.addLast(numbers.removeFirst() - numbers.removeFirst());
                paranthesis_operands--;
            }
        }
        while (paranthesis_operands_copy != 0) {
            numbers.addFirst(numbers.removeLast());
            paranthesis_operands_copy--;
        }

        while (operands.size() != 0) {
            System.out.println(numbers);
            System.out.println("------------");
            System.out.println(operands);
            char sign = operands.removeFirst();
            if (sign == '*') {
                numbers.addFirst(numbers.removeFirst() * numbers.removeFirst());
            }
            else if (sign == '/') {
                numbers.addFirst(numbers.removeFirst() / numbers.removeFirst());
            }
            else if (sign == '+') {
                numbers.addFirst(numbers.removeFirst() + numbers.removeFirst());
            }
            else if (sign == '-') {
                numbers.addFirst(numbers.removeFirst() - numbers.removeFirst());
            }
        }
        return numbers.removeFirst();
    }

    private static double sort_PEMDAS() {
        double paranthesis_operands = numbers.removeLast();
        //int swaps = 0;
        int sizeDiff = numbers.size() - operands.size();
        for (int i = (int) paranthesis_operands; i < operands.size(); i++) {
            if (operands.get(i) == '*' || operands.get(i) == '/') {
                int index0 = i;
                int indexPrevious = i-1;
                while (index0 != paranthesis_operands && operands.get(i-1) != '*' && operands.get(i-1) != '/') {
                    System.out.println(operands);
                    //swaps+=1;
                    swapOrder_operands(indexPrevious, index0);
                    swapOrder_nums(indexPrevious+sizeDiff, index0+sizeDiff);
                    index0--;
                    indexPrevious = index0 - 1;
                }
            }
        }
        System.out.println(operands);
        System.out.println(numbers);
        return paranthesis_operands;

    }

    private static void swapOrder_nums(int index0, int index1) {
        double ele0 = numbers.get(index0);
        double ele1 = numbers.get(index1);
        numbers.set(index0, ele1);
        numbers.set(index1, ele0);
    }

    private static void swapOrder_operands(int index0, int index1) {
        char ele0 = operands.get(index0);
        char ele1 = operands.get(index1);
        operands.set(index0, ele1);
        operands.set(index1, ele0);
    }
    
    private static LinkedList findNums(String str) {
        //LinkedList<Double> numbers = new LinkedList<Double>();
        boolean inParanthesis = false;
        String newNum = "";
        int startParanthesis_count = 0;
        int endParanthesis_count = 0;
        double operandParanthesis_count = 0;
        //newNum+=num
        //need to do pendas
        for (int i = 0; i < str.length(); i++) {
            System.out.println(numbers);
            char num = str.charAt(i);
            if (num == '(') {
                startParanthesis_count+=1;
                continue;
            }
            else if (num == ')') {
                endParanthesis_count+=1;
                numbers.addFirst(parseString(newNum));
                newNum = "";
                double ele0 = numbers.get(0);
                double ele1 = numbers.get(1);
                numbers.set(0, ele1);
                numbers.set(1, ele0);
                continue;
            }
            if (startParanthesis_count > endParanthesis_count)
                inParanthesis = true;
            else 
                inParanthesis = false;
            if (inParanthesis) {
                if (num == '*') {
                    if (newNum.equals("")) 
                        continue;
                    numbers.addFirst(parseString(newNum));
                    newNum = "";
                    operandParanthesis_count++;
                    continue;
                }
                else if (num == '/') {
                    if (newNum.equals("")) 
                        continue;
                    numbers.addFirst(parseString(newNum));
                    newNum = "";
                    operandParanthesis_count++;
                    continue;
                }
                else if (num == '+') {
                    if (newNum.equals("")) 
                        continue;
                    numbers.addFirst(parseString(newNum));
                    newNum = "";
                    operandParanthesis_count++;
                    continue;
                }
                else if (num == '-') {
                    if (i != 0 && str.charAt(i-1) != 'e') {
                        if (newNum.equals("")) 
                            continue;
                        numbers.addFirst(parseString(newNum));
                        newNum = "";
                        operandParanthesis_count++;
                        continue;
                    }
                    else 
                        newNum+=num;
                }
                else 
                    newNum+=num;
            }
            else if (num == '*') {
                if (newNum.equals("")) 
                    continue;
                numbers.add(parseString(newNum));
                newNum = "";
                continue;
            }
            else if (num == '/') {
                if (newNum.equals("")) 
                    continue;
                numbers.add(parseString(newNum));
                newNum = "";
                continue;
            }
            else if (num == '+') {
                if (newNum.equals("")) 
                    continue;
                numbers.add(parseString(newNum));
                newNum = "";
                continue;
            }
            else if (num == '-') {
                if (newNum.equals("")) 
                    continue;
                if (i != 0 && str.charAt(i-1) != 'e') {
                    numbers.add(parseString(newNum));
                    newNum = "";
                    continue;
                }
                else
                    newNum+=num;
            }
            else 
                //System.out.println("adding num");
                newNum+=num;
                if (i == str.length()-1)
                    numbers.add(parseString(newNum));
                    
        }
        numbers.add(operandParanthesis_count);
        return numbers;
    }

    private static LinkedList findOperands(String str) {
        //LinkedList<Character> operands = new LinkedList<Character>();
        boolean inParanthesis = false;
        int startParanthesis_count = 0;
        int endParanthesis_count = 0;
        for (int i = 0; i < str.length(); i++) {
            char sign = str.charAt(i);
            if (sign == '(') 
                startParanthesis_count+=1;
            else if (sign == ')')
                endParanthesis_count+=1;
            if (startParanthesis_count > endParanthesis_count)
                inParanthesis = true;
            else 
                inParanthesis = false;
            if (inParanthesis) {
                if (sign == '*')
                    operands.addFirst(sign);
                else if (sign == '/')
                    operands.addFirst(sign);
                else if (sign == '+')
                    operands.addFirst(sign);
                else if (sign == '-') {
                    if (i != 0 && str.charAt(i-1) != 'e') {
                        operands.addFirst(sign);
                    }
                }
            }
            else if (sign == '*')
                operands.add(sign);
            else if (sign == '/')
                operands.add(sign);
            else if (sign == '+')
                operands.add(sign);
            else if (sign == '-') {
                if (i != 0 && str.charAt(i-1) != 'e') {
                    operands.add(sign);
                }
            }
        }
        return operands;
    }  

    private static String removeUnderscore(String str) {
        String newStr = "";
        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);
            if (num != '_')
                newStr+=num;
        }
        return newStr;
    }

    private static boolean inputValidation(String str) {
        int e_count = 0;
        int dot_count = 0;
        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);
            if (i != str.length()-1) {
                char numNext = str.charAt(i+1);
                if ((num  == '-' && numNext == '-') || (num  == '+' && numNext == '+') || (num  == '.' && numNext == '.')
                || (num  == '/' && numNext == '/') || (num  == '*' && numNext == '*')) {
                    return false;
                }
            }
            if (num == 'e') {
                e_count++;
                if (i == str.length()-1) 
                    return false;
                if (dot_count > 1 || e_count > 1) 
                    return false;
            }
            else if (num == '.') {
                dot_count++;
                if (dot_count > 1 || e_count > 1) 
                    return false;
            }
            /*else if (i == str.length()-1) {
                if (num != '0' && num != '1' && num != '2' && num != '3' && num != '4' && num != '5' && num != '6' && num != '7' 
                && num != '8' && num != '9' && num != '.' && num != '+' && num != '-') {
                    if (num == 'f' || num == 'F' || num == 'd' || num == 'D') {
                        return true;
                    }
                    else
                        return false;
                }
            } */
            else if (num != '0' && num != '1' && num != '2' && num != '3' && num != '4' && num != '5' && num != '6' && num != '7' && num != '8' 
                && num != '9' && num != '.' && num != '+' && num != '-' && num != '*' && num != '/' && num != ')' && num != '(') 
                return false;
        }
        return true;
    }

    private static double parseString(String str) {
        double result = 0;
        boolean negativeNumber = false;
        boolean lessThanOne = false;
        boolean powerOn = false;
        double powerOf10 = 0;
        boolean powerof10_negative = false;
        int decimalPlace = 0;
        int powerPlace = 0;
        int placesOnRight = 0;
        int powerCount = 0;
        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);
            if (num == '.' || num == 'e' || num == 'E' || num == '-') 
                break;
            else
                placesOnRight++;
        }
        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);
            if (num == 'e' || num == 'E') {
                powerCount++;
                if (str.charAt(i+1) == '-')
                    powerCount++;
                break;
            }
            else if (num == '.' || num == '-') {
                powerCount++;
                continue;
            }
            else
                powerCount++;
        }
        //System.out.println(powerCount);
        powerCount = str.length() - powerCount;
        powerCount-=1;
        placesOnRight--;
        //System.out.println(powerCount);

        for (int i = 0; i < str.length(); i++) {
            if (lessThanOne)
                decimalPlace++;
            if (powerOn)
                powerPlace++;

            char num = str.charAt(i);
            if (num == '-' && i == 0)
                negativeNumber = true;
            else if (num == 'E' || num == 'e') {
                powerOn = true;
                if (str.charAt(i+1) == '-') 
                    powerof10_negative = true;
            }
            else if (num == '.') {
                lessThanOne = true;
                //result = reverseNum(result);
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
                result += Math.pow(10, placesOnRight--)*0;
            else if (num == '1')
                result += Math.pow(10, placesOnRight--)*1;
            else if (num == '2')
                result += Math.pow(10, placesOnRight--)*2;
            else if (num == '3')
                result += Math.pow(10, placesOnRight--)*3;
            else if (num == '4')
                result += Math.pow(10, placesOnRight--)*4;
            else if (num == '5')
                result += Math.pow(10, placesOnRight--)*5;
            else if (num == '6')
                result += Math.pow(10, placesOnRight--)*6;
            else if (num == '7')
                result += Math.pow(10, placesOnRight--)*7;
            else if (num == '8')
                result += Math.pow(10, placesOnRight--)*8;
            else if (num == '9')
                result += Math.pow(10, placesOnRight--)*9;
            
        }
        //if (!lessThanOne) {
        //    result = reverseNum(result);
        //}

        //powerOf10 = reverseNum(powerOf10);
        if (powerof10_negative) 
            result = result * Math.pow(10, -powerOf10);
        else
            result = result * Math.pow(10, powerOf10);

        if (negativeNumber) {
            result= -1*result;
        }
        
        return result; 
    }

    private static double reverseNum(double num) {
        double reversedNumber = 0;
        while (num >= 1) {
            reversedNumber = Math.floor(reversedNumber * 10 + num % 10);
            num = num / 10;
        }
        return reversedNumber;
    }



} 