import java.util.Arrays;
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
            System.out.println(solve(operands, numbers, 0, operands.size()));
            //double paranthesis_operands = sort_PEMDAS();
            //System.out.println("Answer: " + calculate(paranthesis_operands));
        }
        else 
            System.out.println("Not a valid Java floating decimal literal");
            //-3e-2+3*(5-2)
    }

    private static void solve2(LinkedList<Character> ops, LinkedList<Double> nums, int start) {
        
    }


    private static double solve(LinkedList<Character> ops, LinkedList<Double> nums, int start, int end) {
        System.out.println(nums + "intial");
        System.out.println(ops);
        System.out.println(start);
        int newStart=start;
        for (int i = start; i < ops.size(); i++) {
            if (ops.get(i) == '(') {
                newStart = i;
                //ops.remove(i);
                //nums.add(i, solve(ops, nums, i));
                System.out.println(nums + " 1done recurse");
                System.out.println(ops);
                //Object[] ops_array = ops.toArray();
                //Arrays.copyOfRange(ops_array, i+1, ops.size());
            }
            if (ops.get(i) == ')'){
                //ops.remove(newStart);  //rid
                ops.remove(i);
                System.out.println("fk my life" + i);
                nums.add(solve(ops, nums, newStart, i));
                //ops.removeFirstOccurrence('(');
                System.out.println(nums + "par thing");
            }
            
            
        }
        System.out.println("start" + start);
        System.out.println("end" + end);
        System.out.println(nums);
        System.out.println(ops);
        for (int i = start; i < end; i++) {
            if (ops.get(i) == '*' || ops.get(i) == '/') {
                char sign = ops.get(i);
                if (sign == '*') {
                    //ops.remove(i);
                    System.out.println("XXDDD"+i);
                    if (nums.size() == 2) {
                        return nums.remove(0) * nums.remove(0);
                    }
                    //System.out.println("XXDDD"+nums);
                    /*else if (i == nums.size()) {
                        nums.add(i-2, nums.remove(i-1) * nums.remove(i-2));
                    } 
                    else*/
                        if (start == 0)
                            nums.add(i, nums.remove(i) * nums.remove(i));
                        else
                            //ops.remove(i);
                            nums.add(i-1, nums.remove(i-1) * nums.remove(i-1));
                }
                else if (sign == '/') {
                    //ops.remove(i);
                    if (nums.size() == 2) {
                        return nums.remove(0) / nums.remove(0);
                    }
                    /*if (i == nums.size()) {
                        nums.add(i-2, nums.remove(i-1) / nums.remove(i-2));
                    }
                    if (i == nums.size()-2) {
                        nums.add(i-2, nums.remove(i-1) / nums.remove(i-2));
                    }
                    else*/ 
                        if (start == 0)
                            nums.add(i, nums.remove(i) / nums.remove(i));
                        else
                            nums.add(i-1, nums.remove(i-1) / nums.remove(i-1));
                }
                //start--;
            }
        }
        for (int i = start; i < end; i++) {
            if (ops.get(i) == '+' || ops.get(i) == '-') {
                char sign = ops.get(i);
                if (sign == '+') {
                    System.out.println("PLUSS");
                    if (nums.size() == 2) {
                        return nums.remove(0) + nums.remove(0);
                    }
                    System.out.println("this is i" + i);
                    /*if (i == end-1) {
                        System.out.println("this is i" + end);
                        System.out.println("this is i" + nums);
                        //double num1 = nums.remove(i-1);
                        //double num2 = nums.remove(i-1);
                        //nums.add(i-1, num1+num2);
                        nums.add(i-1, nums.remove(i-1) + nums.remove(i-1));
                        System.out.println("this is i" + nums);
                        
                    }
                    else */
                        //System.out.println("here");
                        if (start == 0)
                            nums.add(i, nums.remove(i) + nums.remove(i));
                        else
                            nums.add(i-1, nums.remove(i-1) + nums.remove(i-1));
                }
                else if (sign == '-') {
                    if (nums.size() == 2) {
                        return nums.remove(0) - nums.remove(0);
                    }
                    System.out.println("sub "+i);
                    /*if (i == nums.size()) {
                        System.out.println("sub "+i);
                        System.out.println(ops);
                        System.out.println(nums);
                        nums.add(i-2, nums.remove(i-1) - nums.remove(i-2));
                    }
                    else */
                        if (start == 0)
                            nums.add(i, nums.remove(i) - nums.remove(i));
                        else
                            nums.add(i-1, nums.remove(i-1) - nums.remove(i-1));
                }
                //start--;
            }
        }
        System.out.println("fk");
        System.out.println(ops);
        System.out.println(nums);
        return nums.removeLast();
        /*
        if (start == 0) {
            for (int i = start; i < ops.size(); i++) {
                System.out.println(nums + "calc");
                System.out.println(ops.size());
                char sign = ops.get(i);
                if (nums.size() == 2) {
                    if (sign == '*') {
                        return nums.remove(0) * nums.remove(0);
                    }
                    else if (sign == '/') {
                        return nums.remove(0) / nums.remove(0);            
                    }
                    else if (sign == '+') {
                        return nums.remove(0) + nums.remove(0);                //System.out.println(nums + "add");
                    }
                    else if (sign == '-') {
                        return nums.remove(0) - nums.remove(0);            
                    }
                }
                if (sign == '*') {
                    nums.add(i, nums.remove(i) * nums.remove(i));
                }
                else if (sign == '/') {
                    nums.add(i, nums.remove(i) / nums.remove(i));
                }
                else if (sign == '+') {
                    nums.add(i, nums.remove(i) + nums.remove(i));
                    //System.out.println(nums + "add");
                }
                else if (sign == '-') {
                    nums.add(i, nums.remove(i) - nums.remove(i));
                }
            }
        }
        return nums.removeLast();
        */
        /*
        //int size = ops.size();
        if (start != 0) {
        for (int i = start; i < ops.size(); i++) {
            System.out.println(nums + "calc");
            System.out.println(ops.size());
            if (nums.size() == 1) {
                return nums.getFirst();  //cant return must add
            }
            if (ops.get(i) == ')') {
                System.out.println("end par");
                ops.remove(i);
                return nums.get(i);
            }
            char sign = ops.get(i);
            if (sign == '*') {
                nums.add(i, nums.remove(i) * nums.remove(i));
            }
            else if (sign == '/') {
                nums.add(i, nums.remove(i) / nums.remove(i));
            }
            else if (sign == '+') {
                nums.add(i, nums.remove(i) + nums.remove(i));
                //System.out.println(nums + "add");
            }
            else if (sign == '-') {
                nums.add(i, nums.remove(i) - nums.remove(i));
            }
        }
        }
        for (int i = start; i < ops.size(); i++) {
            System.out.println(nums + "calc");
            System.out.println(ops.size());
            char sign = ops.get(i);
            if (nums.size() == 2) {
                if (sign == '*') {
                    return nums.remove(0) * nums.remove(0);
                }
                else if (sign == '/') {
                    return nums.remove(0) / nums.remove(0);            
                }
                else if (sign == '+') {
                    return nums.remove(0) + nums.remove(0);                //System.out.println(nums + "add");
                }
                else if (sign == '-') {
                    return nums.remove(0) - nums.remove(0);            
                }
            }
            if (ops.get(i) == ')') {
                System.out.println("end par");
                ops.remove(i);
                return nums.get(i);
            }
            if (sign == '*') {
                nums.add(nums.remove(i) * nums.remove(i));
            }
            else if (sign == '/') {
                nums.add(nums.remove(i) / nums.remove(i));            
            }
            else if (sign == '+') {
                nums.add(nums.remove(i) + nums.remove(i));                //System.out.println(nums + "add");
            }
            else if (sign == '-') {
                nums.add(nums.remove(i) - nums.remove(i));            
            }
        }
        System.out.println(nums + "done recu");
        System.out.println(ops);
        return nums.getFirst();
        */


    }

    /*private static LinkedList splice(LinkedList list, int start, int end) {
        LinkedList<Character> operands = new LinkedList<Character>();
    } */


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
                continue;
            }
            else if (num == ')') {
                numbers.add(parseString(newNum));
                newNum = "";    //this fixed bug wow
                continue;
            }

            if (num == '*') {
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
        //numbers.add(operandParanthesis_count);  //make sure to add this back
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
                operands.add(sign);
            else if (sign == ')')
                operands.add(sign);
            if (sign == '*')
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
                System.out.println("here");
                if (i + 1 == str.length()-1) {
                    num = str.charAt(i+1);
                    if (num != '0' && num != '1' && num != '2' && num != '3' && num != '4' && num != '5' && num != '6' && num != '7' && num != '8' 
                    && num != '9' && num != '.' ) {
                        return false;
                    }
                    return true;
                }
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