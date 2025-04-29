package arbitraryarithmetic;

public class AInteger {
    protected String num; //Object variable num
    public AInteger(){ //Default Constructor
        this.num = "0";
    }

    public AInteger(String num){ //Parameterized constructor
        this.num = num;
    }

    public AInteger(AInteger others){ //copy constructor
        this.num = others.get();
    }

    public String get(){ //function that returns an instance of AInteger class
        return this.num;
    }

    public void set(String num){ //Set the num variable to a new one Encapsulation
        this.num = num;
    }

    public static AInteger parse(String num){ //Returns the num variable Encapsulation
        return new AInteger(num);
    }

    public String remove_leading_zero(String num){ //Removes the leading zeroes
        int start=0;
        String num_replaced="";
        for(int i=0;i<num.length();i++){
            if(num.charAt(i)=='-'){
                num_replaced+="-";
                continue;
            }
            if(start==0){
                if(num.charAt(i)!='0'){
                    num_replaced+=num.charAt(i);
                    start=1;
                }
                else if(i==num.length()-1){
                    num_replaced+=num.charAt(i);
                    start=1;
                }
            }
            else{
                num_replaced+=num.charAt(i);
            }
        }
        return num_replaced;
    }

    //Adds two integers of arbitaray length
    public AInteger add(AInteger other){
        String operand_1 = this.get();
        String operand_2 = other.get();
        operand_1=remove_leading_zero(operand_1); //Removing the leading zeroes
        operand_2=remove_leading_zero(operand_2);
        if(operand_1.charAt(0)=='-' && operand_2.charAt(0)!='-'){
            //Subtract magnitude of first integer from magnitude of second integer
            String mod="";
            for(int i=1;i<operand_1.length();i++){
                mod+=operand_1.charAt(i);
            }
            operand_1=mod;
            AInteger this_copy = new AInteger(operand_1);
            AInteger result = other.sub(this_copy);
            return result;
        }
        if(operand_1.charAt(0)!='-' && operand_2.charAt(0)=='-'){
            //Subtract magnitude of second integer from magnitude of first integer
            String mod="";
            for(int i=1;i<operand_2.length();i++){
                mod+=operand_2.charAt(i);
            }
            operand_2=mod;
            AInteger other_copy = new AInteger(operand_2);
            AInteger result = this.sub(other_copy);
            return result;
        }
        int pos=1; //pos: Checker if addition is positive or negative
        if(operand_1.charAt(0)=='-' && operand_2.charAt(0)=='-'){
            //If two integers are negative then take the minus common and add them
            pos=0;
            String mod="";
            for(int i=1;i<operand_1.length();i++){
                mod+=operand_1.charAt(i);
            }
            operand_1=mod;
            mod="";
            for(int i=1;i<operand_2.length();i++){
                mod+=operand_2.charAt(i);
            }
            operand_2=mod;
        }
        int index_1 = operand_1.length()-1;
        int index_2 = operand_2.length()-1;
        String res=""; //res: result
        int carry=0;
        while(index_1 >= 0 || index_2 >=0){
            if(index_1>=0 && index_2 >=0){
                //Doing digit wise addition using ascii values
                int sum = (int) operand_1.charAt(index_1)-48 + (int)operand_2.charAt(index_2)-48+carry;
                carry = sum/10; //updating the carry
                res+= sum%10;
            }
            else if(index_1>=0){
                int sum = (int) operand_1.charAt(index_1)-48+carry;
                carry = sum/10;//updating the carry
                res+=sum%10;
            }
            else if(index_2>=0){
                int sum = (int) operand_2.charAt(index_2)-48 + carry;
                carry = sum/10;
                res+=sum%10;
            }
            index_1--;
            index_2--;
        }
        if(carry!=0)res+=carry;
        String fin;
        if(pos==1)fin=""; //if it is positive
        else fin="-"; // else insert - initially
        for(int i=res.length()-1;i>=0;i--){
            fin+=res.charAt(i);
        }
        String fin_copy="";
        fin_copy = remove_leading_zero(fin); //remoing the leading zeroes
        AInteger result = new AInteger(fin_copy);
        return result;
    }

    public AInteger sub(AInteger other){
        String operand_1 = this.get();
        String operand_2 = other.get();
        operand_1=remove_leading_zero(operand_1); //removing the leading zeroes
        operand_2=remove_leading_zero(operand_2);
        if(operand_1.charAt(0)=='-'&&operand_2.charAt(0)!='-'){
            //Add the magnitude of both integers and add a minus sign at the start
            String mod="";
            for(int i=1;i<operand_1.length();i++){
                mod+=operand_1.charAt(i);
            }
            operand_1=mod;
            AInteger this_copy = new AInteger(operand_1);
            AInteger result = this_copy.add(other);
            result.set("-"+result.get());
            return result;
        }
        if(operand_1.charAt(0)!='-'&&operand_2.charAt(0)=='-'){
            //Add the magnitude of both the integers
            String mod="";
            for(int i=1;i<operand_2.length();i++){
                mod+=operand_2.charAt(i);
            }
            operand_2=mod;
            AInteger other_copy = new AInteger(operand_2);
            AInteger result = this.add(other_copy);
            return result;
        }
        if(operand_1.charAt(0)=='-'&&operand_2.charAt(0)=='-'){
            //Subtract the magnitude of first integer from second integer
            String mod="";
            for(int i=1;i<operand_1.length();i++){
                mod+=operand_1.charAt(i);
            }
            operand_1=mod;
            AInteger this_copy = new AInteger(operand_1);
            mod="";
            for(int i=1;i<operand_2.length();i++){
                mod+=operand_2.charAt(i);
            }
            operand_2=mod;
            AInteger other_copy = new AInteger(operand_2);
            AInteger result = other_copy.sub(this_copy);
            return result;
        }
        int index_1 = operand_1.length()-1;
        int index_2 = operand_2.length()-1;
        if(operand_1.equals(operand_2)){
            AInteger result = new AInteger("0"); //If two integers are equal then answer is 0
            return result;
        }
        int pos=1; // Variable for sign of resulting operation
        //Checking if the first integer is greater than second
        if(index_1 > index_2){
            pos=1;
        }
        else if(index_1 < index_2){
            pos=0;
        }
        else {
            for(int i=0;i<=index_1;i++){
                if(operand_1.charAt(i) > operand_2.charAt(i)){
                    pos=1;
                    break;
                }
                else if(operand_1.charAt(i) < operand_2.charAt(i)){
                    pos=0;
                    break;
                }
            }
        }
        String res=""; //res: result
        int borrow=0; //Borrow variable
        int diff;
        while(index_1>=0 || index_2>=0){
            if(pos==1){
                //If it is postive then subtract magnitude of second integer from first integer
                if(index_1 >=0 && index_2 >=0){
                    diff = (int)operand_1.charAt(index_1) - (int) operand_2.charAt(index_2) + borrow;
                    if(diff >= 0)borrow=0;
                    else {
                        borrow=-1;
                        diff =diff+10;
                    }
                    res+=diff;
                }
                else if(index_1>=0){
                    diff = (int)operand_1.charAt(index_1)-48 + borrow;
                    if(diff >=0)borrow=0;
                    else {
                        borrow=-1;
                        diff+=10;
                    }
                    res+=diff;
                }
            }
            else{
                //If it is negative then subtract magnitude of first integer from second integer and add a minus sign
                if(index_1>=0 && index_2>=0){
                    diff = (int)operand_2.charAt(index_2) - (int)operand_1.charAt(index_1) + borrow;
                    if(diff >=0)borrow=0;
                    else{
                        borrow=-1;
                        diff+=10;
                    }
                    res+=diff;
                }
                else if(index_2>=0){
                    diff = (int)operand_2.charAt(index_2) - 48 + borrow;
                    if(diff>=0)borrow=0;
                    else{
                        borrow=-1;
                        diff+=10;
                    }
                    res+=diff;
                }
            }
            index_1--;
            index_2--;
        }
        String rev=""; //reverse of res string
        for(int i=res.length()-1;i>=0;i--){
            rev+=res.charAt(i);
        }
        String fin;//fin: final
        if(pos==1)fin="";
        else fin="-";
        fin += remove_leading_zero(rev); //adding the updated version of rev
        AInteger result = new AInteger(fin);
        return result;
    }
    
    public AInteger mul(AInteger other){
        String operand_1=this.get();
        String operand_2=other.get();
        operand_1=remove_leading_zero(operand_1);//Removing the leading zeroes
        operand_2=remove_leading_zero(operand_2);
        int pos=1;//Variable for sign of the result
        if(operand_1.charAt(0) == '-'|| operand_2.charAt(0)=='-'){
            if(operand_1.charAt(0) == '-'&& operand_2.charAt(0)=='-'){
                pos=1;
            }
            else pos=0;
            if(operand_1.charAt(0) == '-'){
                //If the operand_1 is negative then consider the magnitude only
                String mod="";
                for(int i=1;i<operand_1.length();i++){
                    mod+=operand_1.charAt(i);
                }
                operand_1=mod;
            }
            if(operand_2.charAt(0)=='-'){
                //If the operand_2 is negative then consider the magnitude only
                String mod="";
                for(int i=1;i<operand_2.length();i++){
                    mod+=operand_2.charAt(i);
                }
                operand_2=mod;
            }
        }
        if(operand_2=="0"||operand_1=="0"){
            //If at least one of them is 0 then answer is zero
            AInteger result = new AInteger();
            return result;
        }
        AInteger this_copy = new AInteger(operand_1);// Creating copy of first operand
        int length2 = operand_2.length();
        AInteger fin = new AInteger();
        for(int i=0;i<length2;i++){
            //I write abc = a*100 + b*10 +c, I used this way to calculate the product
            int digit = (int)operand_2.charAt(i)-48;
            AInteger temp = new AInteger();
            for(int j=0;j<digit;j++)temp = temp.add(this_copy);
            String Zeroes="";
            for(int j=0;j<length2-1-i;j++)Zeroes+="0";
            if(i<length2-1 && digit > 0)temp.set(temp.get()+Zeroes);
            fin=fin.add(temp);
        }
        if(pos==0)fin.set("-"+fin.get()); //Updating the value of fin(final) if product is negative
        String fin_copy = remove_leading_zero(fin.get());
        AInteger result = new AInteger(fin_copy);
        return result;
    }

    public AInteger div(AInteger other){
        String operand_1 = this.get();
        String operand_2 = other.get();
        operand_1=remove_leading_zero(operand_1);//removing the leading zeroes
        operand_2=remove_leading_zero(operand_2);
        int pos=1; //Variable for sign of result
        if(operand_1.charAt(0) == '-'|| operand_2.charAt(0)=='-'){
            if(operand_1.charAt(0) == '-' && operand_2.charAt(0)=='-'){
                pos=1;
            }
            else pos=0;
            if(operand_1.charAt(0) == '-'){
                String mod="";
                for(int i=1;i<operand_1.length();i++){
                    mod+=operand_1.charAt(i);
                }
                operand_1=mod;
            }
            if(operand_2.charAt(0)=='-'){
                String mod="";
                for(int i=1;i<operand_2.length();i++){
                    mod+=operand_2.charAt(i);
                }
                operand_2=mod;
            }
        }
        if(operand_2.equals("0")){
            //Divison by zero error
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        if(operand_1.equals("0")){
            //If the dividend is zero then answer is direct zero unless divisor is zero
            AInteger result = new AInteger();
            return result;
        }
        if(operand_2.equals("1")){
            //If my divisor is 1 then answer is dividend
            AInteger result = new AInteger();
            if(pos==1)result = new AInteger(operand_1);
            else {
                String operand = "-" + operand_1;
                result=new AInteger(operand);
            }
            return result;
        }
        if(operand_1.equals(operand_2)){
            //If both divisor and dividend are equal then answer is 1
            AInteger result = new AInteger();
            if(pos==1)result = new AInteger("1");
            else {
                result = new AInteger("-1");
            }
            return result;
        }
        int length1 = operand_1.length();
        int length2 = operand_2.length();
        if(length2>length1){
            //If divisor is greater than dividend then integer division is 0
            AInteger result = new AInteger();
            return result;
        }
        if(length2==length1){
            int zero=1;
            for(int i=0;i<length1;i++){
                if((int) operand_2.charAt(i) >(int)operand_1.charAt(i)){
                    zero=1;
                    break;
                }
                else if((int) operand_1.charAt(i)> (int) operand_2.charAt(i)){
                    zero=0;
                    break;
                }
            }
            if(zero==1){
                //If divisor is greater than dividend then integer division is 0
                AInteger result = new AInteger();
                return result;
            }
        }
        String comp="";//varaible for component of dividend
        String fin=""; //final answer
        AInteger other_copy = new AInteger(operand_2);
        for(int i=0;i<length1;i++){
            //Implementing long division method
            if(comp.equals("0"))comp=""+operand_1.charAt(i);
            else comp+=operand_1.charAt(i);
            if(length2 > comp.length()){
                fin+="0";
                continue;
            }
            int index=0;//Variable for storing the  digit of quotient
            for(int j=0;j<=10;j++){
                AInteger mul = new AInteger(""+j);
                AInteger temp = other_copy.mul(mul);
                if(temp.get().length() > comp.length()){
                    index=j-1;
                    break;
                }
                if(temp.get().length() <comp.length()){
                    continue;
                }
                int cont=1;//Variable for continuing in the inner loop
                for(int k=0;k<comp.length();k++){
                    if((int) comp.charAt(k) > (int) temp.get().charAt(k)){
                        cont=1;
                        break;
                    }
                    else if((int) comp.charAt(k) < (int) temp.get().charAt(k)){
                        cont=0;
                        break;
                    }
                }
                if(cont == 0){
                    index=j-1;
                    break;
                }
            }
            fin+=index;
            AInteger mul = new AInteger(""+index);
            AInteger prod = other_copy.mul(mul);
            AInteger comp_int = new AInteger(comp);
            comp_int=comp_int.sub(prod);
            comp = comp_int.get();//updating the comp
        }
        String rev;
        if(pos==1){
            rev="";
        }
        else rev="-";
        fin=remove_leading_zero(fin);
        rev+=fin;
        AInteger result = new AInteger(rev);
        return result;
    }
}