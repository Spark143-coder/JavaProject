import arbitraryarithmetic.AInteger;
import arbitraryarithmetic.AFloat;

public class MyInfArith{
    public static void main(String[] args){
        if(args[0].equals("int")){
            AInteger operand_1 = AInteger.parse(args[2]);
            AInteger operand_2 = AInteger.parse(args[3]);
            if(args[1].equals("add")){
                System.out.println(operand_1.add(operand_2).get());
            }
            else if(args[1].equals("sub")){
                System.out.println(operand_1.sub(operand_2).get());
            }
            else if(args[1].equals("mul")){
                System.out.println(operand_1.mul(operand_2).get());
            }
            else if(args[1].equals("div")){
                System.out.println(operand_1.div(operand_2).get());
            }
            else {
                System.out.println("Incorrect way");
                System.out.println("Please run in this manner:");
                System.out.println("java MyInfArith int add/sub/mu/div operand_1 operand_2");
            }
        }
        else if(args[0].equals("float")){
            AFloat operand_1=AFloat.parse(args[2]);
            AFloat operand_2=AFloat.parse(args[3]);
            if(args[1].equals("add")){
                System.out.println(operand_1.add(operand_2).get());
            }
            else if(args[1].equals("sub")){
                System.out.println(operand_1.sub(operand_2).get());
            }
            else if(args[1].equals("mul")){
                System.out.println(operand_1.mul(operand_2).get());
            }
            else if(args[1].equals("div")){
                System.out.println(operand_1.div(operand_2).get());
            }
            else {
                System.out.println("Incorrect way");
                System.out.println("Please run in this manner:");
                System.out.println("java MyInfArith float add/sub/mu/div operand_1 operand_2");
            }
        }
        else {
            System.out.println("Incorrect way");
            System.out.println("Please run in this manner:");
            System.out.println("java MyInfArith int/float add/sub/mu/div operand_1 operand_2");
        }
    }
}
